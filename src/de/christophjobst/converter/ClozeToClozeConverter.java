/**
 * Programm zur Konvertierung von aus Moodle exportierten Übungsfragen (Moodle-XML)
 * in Elate ComplexTaskDef-XML.
 *
 * @author Christoph Jobst
 * @version 1.0
 */

package de.christophjobst.converter;

import java.util.*;

import generated.Quiz.Question;
import de.christophjobst.main.RandomIdentifierGenerator;
import de.thorstenberger.taskmodel.complex.complextaskdef.ClozeSubTaskDef.Cloze;
import de.thorstenberger.taskmodel.complex.complextaskdef.ClozeSubTaskDef.Cloze.Gap;
import de.thorstenberger.taskmodel.complex.complextaskdef.ClozeSubTaskDef;

public class ClozeToClozeConverter {

	public static ClozeSubTaskDef processing(Question question) {

		RandomIdentifierGenerator rand = new RandomIdentifierGenerator();

		ClozeSubTaskDef subTask = new ClozeSubTaskDef();

		if (question.getType().toString().equals("cloze")) {
			System.out.println("Es ist ein cloze.");

			// Allgemeine Angaben pro Frage
			subTask.setTrash(false);
			subTask.setInteractiveFeedback(false);
			subTask.setCorrectionHint(" ");
			subTask.setHint(" ");

			// Spezielle Angaben pro Frage
			subTask.setId(question.getName().getText().toString() + "_"
					+ rand.getRandomID());

			subTask.setProblem("Lueckentext.");

			// Stringoperationen, um die in die Aufgabenstellung
			// eingebetteten Lösungsphrasen der Lücken zu extrahieren
			List<String> list = new ArrayList<String>();
			list = clozeAufloeser(question.getQuestiontext().getText());

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

			list = new ArrayList<String>();

		}

		return subTask;
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
				if (endShortanswerIndex == 0) {
					text = moodletext.substring(endShortanswerIndex,
							startShortanswerIndex - 3);
				} else {

					text = moodletext.substring(endShortanswerIndex + 1,
							startShortanswerIndex - 3);
				}
				// Die Antwort extrahieren, Ausgangsformat:
				// {1:SHORTANSWER:=Genera}.
				// Indizes: {1:^S^HORTANSWER:=Genera^}^
				shortanswer = moodletext.substring(startShortanswerIndex + 13,
						moodletext.indexOf("}", startShortanswerIndex));
				endShortanswerIndex = moodletext.indexOf("}",
						startShortanswerIndex);

				list.add(text);
				list.add(shortanswer);
			}

		} while (startShortanswerIndex != -1);

		return list;
	}
}
