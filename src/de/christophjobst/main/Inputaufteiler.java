/**
 * Programm zur Konvertierung von aus Moodle exportierten Übungsfragen (Moodle-XML)
 * in Elate ComplexTaskDef-XML.
 *
 * @author Christoph Jobst
 * @version 1.0
 */

package de.christophjobst.main;

import de.christophjobst.converter.ClozeToClozeConverter;
import de.christophjobst.converter.EssayToTextConverter;
import de.christophjobst.converter.MatchingToMappingConverter;
import de.christophjobst.converter.MultichoiceToMcConverter;
import de.christophjobst.converter.ShortanswerToTextConverter;
import de.christophjobst.converter.TruefalseToMcConverter;
import de.thorstenberger.taskmodel.complex.complextaskdef.ComplexTaskDef;
import de.thorstenberger.taskmodel.complex.complextaskdef.ComplexTaskDef.Config.CorrectionMode.Regular;
import de.thorstenberger.taskmodel.complex.complextaskdef.ComplexTaskDef.Config.CorrectionMode;
import generated.*;

public class Inputaufteiler {

	/*
	 * Quiz-Input nach Knoten der Aufgabentypen durchlaufen und den
	 * entsprechenden Konvertern zuordnen Mögliche Typen: - Essay - Cloze -
	 * Multichoice - Shortanswer - Mapping - Truefalse
	 */
	public static ComplexTaskDef inputAufteilen(Quiz quizsammlung) {

		RandomIdentifierGenerator rand = new RandomIdentifierGenerator();

		ComplexTaskDef complexTaskDef = new ComplexTaskDef();
		CorrectionMode correctionMode = new CorrectionMode();
		Regular regular = new Regular();
		correctionMode.setRegular(regular);
		ComplexTaskDef.Config config = new ComplexTaskDef.Config();
		config.setKindnessExtensionTime(2);
		config.setCorrectionMode(correctionMode);
		config.setTasksPerPage(10);
		config.setTries(5);
		config.setTasksPerPage(30);
		complexTaskDef.setConfig(config);
		complexTaskDef.setDescription("Eine Testklausur");
		complexTaskDef.setID(rand.getRandomID());
		complexTaskDef.setShowHandlingHintsBeforeStart(false);
		ComplexTaskDef.Category category = new ComplexTaskDef.Category();
		category.setTitle("Kategorie 1");
		category.setId("Kategorie1_" + rand.getRandomID());
		category.setIgnoreOrderOfBlocks(false);
		category.setMixAllSubTasks(false);
		ComplexTaskDef.Revisions.Revision revision = new ComplexTaskDef.Revisions.Revision();
		ComplexTaskDef.Revisions revisions = new ComplexTaskDef.Revisions();
		revisions.getRevision().add(revision);
		revision.setAuthor("Christoph Jobst");
		revision.setDate(24801486);
		revision.setSerialNumber(1);
		complexTaskDef.setRevisions(revisions);
		complexTaskDef.setTitle("Testklausur");
		complexTaskDef.getCategory().add(category);

		// Umwandlungen
		// 1. Freitextaufgaben
		complexTaskDef.getCategory().get(0)
				.getMcTaskBlockOrClozeTaskBlockOrTextTaskBlock()
				.add(EssayToTextConverter.processing(quizsammlung));

		// 2. Shortanswer-Aufgaben
		complexTaskDef.getCategory().get(0)
				.getMcTaskBlockOrClozeTaskBlockOrTextTaskBlock()
				.add(ShortanswerToTextConverter.processing(quizsammlung));

		// 3. True-false-Aufgaben
		complexTaskDef.getCategory().get(0)
				.getMcTaskBlockOrClozeTaskBlockOrTextTaskBlock()
				.add(TruefalseToMcConverter.processing(quizsammlung));
		// 4. Multichoice-Aufgaben
		complexTaskDef.getCategory().get(0)
				.getMcTaskBlockOrClozeTaskBlockOrTextTaskBlock()
				.add(MultichoiceToMcConverter.processing(quizsammlung));

		// 5. Cloze-Aufgaben
		complexTaskDef.getCategory().get(0)
				.getMcTaskBlockOrClozeTaskBlockOrTextTaskBlock()
				.add(ClozeToClozeConverter.processing(quizsammlung));

		// 6. Matching-Aufgaben
		complexTaskDef.getCategory().get(0)
				.getMcTaskBlockOrClozeTaskBlockOrTextTaskBlock()
				.add(MatchingToMappingConverter.processing(quizsammlung));

		/*
		for (int i = 0; i < quizsammlung.getQuestion().toArray().length; i++) {
			try {
				if (quizsammlung.getQuestion().get(i).getType().toString()
						.equals("essay")) {
					complexTaskDef.getCategory().get(0)
							.getMcTaskBlockOrClozeTaskBlockOrTextTaskBlock()
							.add(EssayToTextConverter.processing(quizsammlung));
				}

				if (quizsammlung.getQuestion().get(i).getType().toString()
						.equals("cloze")) {

					complexTaskDef
							.getCategory()
							.get(0)
							.getMcTaskBlockOrClozeTaskBlockOrTextTaskBlock()
							.add(ClozeToClozeConverter.processing(quizsammlung));
				}

				if (quizsammlung.getQuestion().get(i).getType().toString()
						.equals("truefalse")) {
					complexTaskDef
							.getCategory()
							.get(0)
							.getMcTaskBlockOrClozeTaskBlockOrTextTaskBlock()
							.add(TruefalseToMcConverter
									.processing(quizsammlung));
				}

				if (quizsammlung.getQuestion().get(i).getType().toString()
						.equals("multichoice")) {
					complexTaskDef
							.getCategory()
							.get(0)
							.getMcTaskBlockOrClozeTaskBlockOrTextTaskBlock()
							.add(MultichoiceToMcConverter
									.processing(quizsammlung));

				}

				if (quizsammlung.getQuestion().get(i).getType().toString()
						.equals("shortanswer")) {
					complexTaskDef
							.getCategory()
							.get(0)
							.getMcTaskBlockOrClozeTaskBlockOrTextTaskBlock()
							.add(ShortanswerToTextConverter
									.processing(quizsammlung));
				}

				if (quizsammlung.getQuestion().get(i).getType().toString()
						.equals("matching")) {
					complexTaskDef
							.getCategory()
							.get(0)
							.getMcTaskBlockOrClozeTaskBlockOrTextTaskBlock()
							.add(MatchingToMappingConverter
									.processing(quizsammlung));
				}
			} catch (Exception e) {
				System.out.println("PROBLEM BEIM INPUTAUFTEILER");
				e.printStackTrace();
			}

		}
		*/
		return complexTaskDef;
	}

}
