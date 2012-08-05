/**
 * Programm zur Konvertierung von aus Moodle exportierten Übungsfragen (Moodle-XML)
 * in Elate ComplexTaskDef-XML.
 *
 * @author Christoph Jobst
 * @version 1.0
 */

package de.christophjobst.converter;

import java.util.*;

import generated.Quiz;
import de.christophjobst.main.RandomIdentifierGenerator;
import de.thorstenberger.taskmodel.complex.complextaskdef.ClozeSubTaskDef.Cloze;
import de.thorstenberger.taskmodel.complex.complextaskdef.ClozeSubTaskDef.Cloze.Gap;
import de.thorstenberger.taskmodel.complex.complextaskdef.ComplexTaskDef;
import de.thorstenberger.taskmodel.complex.complextaskdef.Config;
import de.thorstenberger.taskmodel.complex.complextaskdef.ClozeSubTaskDef;
import de.thorstenberger.taskmodel.complex.complextaskdef.ComplexTaskDef.Category.ClozeTaskBlock;
import de.thorstenberger.taskmodel.complex.complextaskdef.ComplexTaskDef.Category.ClozeTaskBlock.ClozeConfig;

public class ClozeToClozeConverter {

	public static ComplexTaskDef processing(ComplexTaskDef complexTaskDef,
			Quiz quizsammlung) {

		RandomIdentifierGenerator rand = new RandomIdentifierGenerator();

		ClozeSubTaskDef subTask = new ClozeSubTaskDef();
		ClozeTaskBlock clozeTaskBlock = new ClozeTaskBlock();

		Config clozeTaskConfig = new Config();
		clozeTaskConfig.setNoOfSelectedTasks(1);
		clozeTaskConfig.setPointsPerTask(5);// TODO Punkte = Anzahl der Lücken -
											// inkonsistent, da in
											// Frageinstanzen nicht einheitlich
											// viele Lücken
		clozeTaskConfig.setPreserveOrder(false);
		clozeTaskBlock.setConfig(clozeTaskConfig);

		ClozeConfig clozeConfig = new ClozeConfig();
		clozeConfig.setIgnoreCase(true);
		clozeConfig.setNegativePoints(0);
		clozeTaskBlock.setClozeConfig(clozeConfig);

		for (int i = 0; i < quizsammlung.getQuestion().toArray().length; i++) {

			if (quizsammlung.getQuestion().get(i).getType().toString()
					.equals("cloze")) {
				System.out.println("Es ist ein cloze, Indexnummer: " + i);

				// Allgemeine Angaben pro Frage
				subTask.setTrash(false);
				subTask.setInteractiveFeedback(false);
				subTask.setCorrectionHint(" ");
				subTask.setHint(" ");

				// Spezielle Angaben pro Frage
				subTask.setId(quizsammlung.getQuestion().get(i).getName()
						.getText().toString()
						+ "_" + rand.getRandomID());

				subTask.setProblem("Lösen Sie den folgenden Lückentext.");

				// Stringoperationen, um die in die Aufgabenstellung
				// eingebetteten Lösungsphrasen der Lücken zu extrahieren
				List<String> list = new ArrayList<String>();
				list = clozeAufloeser(quizsammlung.getQuestion().get(i)
						.getQuestiontext().getText());

				// Schleife um die Stringfelder den Text und Gap-Elementen
				// zuzuweisen
				Cloze cloze = new Cloze();
				for (int j = 0; j < list.toArray().length; j++) {
					Gap gap = new Gap();

					if (j % 2 == 1) {
						gap.getCorrect().add(list.get(j));
						gap.setIgnoreCase(true);
						cloze.getTextOrGap().add(gap);
					} else {

						cloze.getTextOrGap().add(list.get(j));
					}
				}

				subTask.setCloze(cloze);

				clozeTaskBlock.getClozeSubTaskDefOrChoice().add(subTask);

				list = new ArrayList<String>();
				subTask = new ClozeSubTaskDef();
			}
		}

		complexTaskDef.getCategory().get(0)
				.getMcTaskBlockOrClozeTaskBlockOrTextTaskBlock()
				.add(clozeTaskBlock);

		return complexTaskDef;
	}

	private static List<String> clozeAufloeser(String moodletext) {

		List<String> list = new ArrayList<String>();

		/*
		 * Falls aus Moodle zusätzliche Formate mit Feedback für
		 * Richtige/Falsche/"Halbrichtige" Antworten etc extrahiert werden
		 * sollen (alles in {} im Aufgabentext eingebettett), eine eigene
		 * Datenstruktur dafür anlegen und hinterher ggfs in den CorrectionHint
		 * schreiben
		 */

		int endShortanswerIndex = 0;
		int startShortanswerIndex = 0;
		String shortanswer = new String();
		String text = new String();

		do {
			// Index für den Beginn des nächsten Shortanswer-Bereiches bekommen
			startShortanswerIndex = moodletext.indexOf("SHORTANSWER",
					endShortanswerIndex);
			// System.out.println(startShortanswerIndex);

			// Fragetext bis zur Shortanswer extrahieren
			if (startShortanswerIndex == -1) {
				// Ende des Strings erreicht
				text = moodletext.substring(endShortanswerIndex + 1);
				list.add(text);

			} else {
				text = moodletext.substring(endShortanswerIndex + 1,
						startShortanswerIndex - 3);
				// Die Antwort extrahieren, Ausgangsformat:
				// {1:SHORTANSWER:=Genera}.
				// Indizes: {1:^S^HORTANSWER:=Genera^}^
				shortanswer = moodletext.substring(startShortanswerIndex + 13,
						moodletext.indexOf("}", startShortanswerIndex) - 1);
				endShortanswerIndex = moodletext.indexOf("}",
						startShortanswerIndex);
				list.add(text);
				list.add(shortanswer);
			}

		} while (startShortanswerIndex != -1);

		return list;
	}
}
