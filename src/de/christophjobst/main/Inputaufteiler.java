/**
 * Programm zur Konvertierung von aus Moodle exportierten Übungsfragen (Moodle-XML)
 * in Elate ComplexTaskDef-XML.
 *
 * @author Christoph Jobst
 * @version 1.0
 *
 */

package de.christophjobst.main;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.christophjobst.converter.CategoryToCategoryConverter;
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
		Date date = new Date();

		// Klausurdatei aufsetzen. 1. Allgemeine Angaben
		ComplexTaskDef complexTaskDef = new ComplexTaskDef();
		CorrectionMode correctionMode = new CorrectionMode();
		Regular regular = new Regular();
		ComplexTaskDef.Config config = new ComplexTaskDef.Config();
		ComplexTaskDef.Revisions.Revision revision = new ComplexTaskDef.Revisions.Revision();
		ComplexTaskDef.Revisions revisions = new ComplexTaskDef.Revisions();

		correctionMode.setRegular(regular);
		config.setKindnessExtensionTime(2);
		config.setCorrectionMode(correctionMode);
		config.setTries(5);
		config.setTasksPerPage(30);
		complexTaskDef.setConfig(config);
		complexTaskDef.setDescription("Eine Testklausur");
		complexTaskDef.setID(rand.getRandomID());
		complexTaskDef.setShowHandlingHintsBeforeStart(false);

		revisions.getRevision().add(revision);
		revision.setAuthor("Christoph Jobst");
		revision.setDate(date.getTime());
		revision.setSerialNumber(1);
		complexTaskDef.setRevisions(revisions);
		complexTaskDef.setTitle("Testklausur");

		// Für Debugging, solange Fragezuordnung zu Category nicht geht
		// complexTaskDef.getCategory().add(
		// CategoryToCategoryConverter.processing(quizsammlung
		// .getQuestion().get(0)));

		// Zuweisungs und Konvertierungsschleife für Category-Blöcke - BETA
		List<String> categoryNameList = new ArrayList<String>();
		List<CategoryManager> categoryManager = new ArrayList<CategoryManager>();
		for (int i = 0; i < quizsammlung.getQuestion().toArray().length; i++) {
			try {
				if (quizsammlung.getQuestion().get(i).getType().toString()
						.equals("category")
						&& (!categoryNameList.contains(quizsammlung
								.getQuestion().get(i).getCategory().getText()
								.toString().substring(9)))) {
					categoryNameList.add(quizsammlung.getQuestion().get(i)
							.getCategory().getText().toString().substring(9));
					categoryManager.add(new CategoryManager(
							CategoryToCategoryConverter.processing(quizsammlung
									.getQuestion().get(i))));
				}

			} catch (Exception e) {
				System.out.println("Problem beim Category-Parser");
			}

		}

		// Internal Debuggingcode
		for (int i = 0; i < categoryManager.toArray().length; i++) {
			System.out.println(categoryManager.get(i).getTitle());
		}

		System.out.println("..................");

		for (String categoryName : categoryNameList) {
			System.out.println(categoryName);
		}

		for (int i = 0; i < categoryManager.toArray().length; i++) {
			System.out.println(i
					+ " "
					+ categoryManager.get(i).getTitle()
							.equals(categoryNameList.get(i)));
		}
		// Internal Debuggingcode Ende

		// Zuweisungs- und Konvertierungsschleife für Fragen
		int belongingCategoryIndex = 0;
		for (int j = 0; j < quizsammlung.getQuestion().toArray().length; j++) {
			try {

				if (quizsammlung.getQuestion().get(j).getType().toString()
						.equals("category")) {

					for (int k = 0; k < categoryManager.toArray().length; k++) {
						if (categoryManager
								.get(k)
								.getTitle()
								.equals(quizsammlung.getQuestion().get(j)
										.getCategory().getText().toString()
										.substring(9))) {
							belongingCategoryIndex = k;
						}
					}

					for (int i = j + 1; i < quizsammlung.getQuestion()
							.toArray().length; i++) {

						if (quizsammlung.getQuestion().get(i).getType()
								.toString().equals("essay")) {

							categoryManager
									.get(belongingCategoryIndex)
									.getTextTaskBlock()
									.getTextSubTaskDefOrChoice()
									.add(EssayToTextConverter
											.processing(quizsammlung
													.getQuestion().get(i)));
							categoryManager.get(belongingCategoryIndex)
									.setHasTextTaskBlock(true);
							System.out.println("Categoryblock nummer "
									+ belongingCategoryIndex
									+ " hat nun "
									+ categoryManager
											.get(belongingCategoryIndex)
											.getTextTaskBlock()
											.getTextSubTaskDefOrChoice()
											.toArray().length + " textfragen");
						}

						if (quizsammlung.getQuestion().get(i).getType()
								.toString().equals("cloze")) {
							categoryManager
									.get(belongingCategoryIndex)
									.getClozeTaskBlock()
									.getClozeSubTaskDefOrChoice()
									.add(ClozeToClozeConverter
											.processing(quizsammlung
													.getQuestion().get(i)));
							categoryManager.get(belongingCategoryIndex)
									.setHasClozeTaskBlock(true);
							System.out.println("Categoryblock nummer "
									+ belongingCategoryIndex
									+ " hat nun "
									+ categoryManager
											.get(belongingCategoryIndex)
											.getTextTaskBlock()
											.getTextSubTaskDefOrChoice()
											.toArray().length + " clozefragen");

						}

						if (quizsammlung.getQuestion().get(i).getType()
								.toString().equals("truefalse")) {
							categoryManager
									.get(belongingCategoryIndex)
									.getMcTaskBlock()
									.getMcSubTaskDefOrChoice()
									.add(TruefalseToMcConverter
											.processing(quizsammlung
													.getQuestion().get(i)));
							categoryManager.get(belongingCategoryIndex)
									.setHasMcTaskBlock(true);
							System.out.println("Categoryblock nummer "
									+ belongingCategoryIndex
									+ " hat nun "
									+ categoryManager
											.get(belongingCategoryIndex)
											.getTextTaskBlock()
											.getTextSubTaskDefOrChoice()
											.toArray().length
									+ " truefalsefragen");

						}

						if (quizsammlung.getQuestion().get(i).getType()
								.toString().equals("multichoice")) {
							categoryManager
									.get(belongingCategoryIndex)
									.getMcTaskBlock()
									.getMcSubTaskDefOrChoice()
									.add(MultichoiceToMcConverter
											.processing(quizsammlung
													.getQuestion().get(i)));
							categoryManager.get(belongingCategoryIndex)
									.setHasMcTaskBlock(true);
							System.out.println("Categoryblock nummer "
									+ belongingCategoryIndex
									+ " hat nun "
									+ categoryManager
											.get(belongingCategoryIndex)
											.getTextTaskBlock()
											.getTextSubTaskDefOrChoice()
											.toArray().length + " mcfragen");

						}

						if (quizsammlung.getQuestion().get(i).getType()
								.toString().equals("shortanswer")) {

							categoryManager
									.get(belongingCategoryIndex)
									.getTextTaskBlock()
									.getTextSubTaskDefOrChoice()
									.add(ShortanswerToTextConverter
											.processing(quizsammlung
													.getQuestion().get(i)));
							categoryManager.get(belongingCategoryIndex)
									.setHasTextTaskBlock(true);

						}

						if (quizsammlung.getQuestion().get(i).getType()
								.toString().equals("matching")) {

							categoryManager
									.get(belongingCategoryIndex)
									.getMappingTaskBlock()
									.getMappingSubTaskDefOrChoice()
									.add(MatchingToMappingConverter
											.processing(quizsammlung
													.getQuestion().get(i)));
							categoryManager.get(belongingCategoryIndex)
									.setHasMappingTaskBlock(true);
						}
						if (quizsammlung.getQuestion().get(i).getType()
								.toString().equals("category")) {
							// TODO Das ruft nach Rekursion
							break;
						}
					}
				}
			} catch (Exception e) {
				System.out.println("PROBLEM BEIM INPUTAUFTEILER");
				e.printStackTrace();
			}
		}

		// // Essay to Text
		// complexTaskDef.getCategory().get(0)
		// .getMcTaskBlockOrClozeTaskBlockOrTextTaskBlock()
		// .add(essayTextTaskBlock);
		// // Shortanswer to Text
		// complexTaskDef.getCategory().get(0)
		// .getMcTaskBlockOrClozeTaskBlockOrTextTaskBlock()
		// .add(shortanswerTextTaskBlock);
		// // Cloze to Cloze
		// complexTaskDef.getCategory().get(0)
		// .getMcTaskBlockOrClozeTaskBlockOrTextTaskBlock()
		// .add(clozeTaskBlock);
		// // Matching to Mapping
		// complexTaskDef.getCategory().get(0)
		// .getMcTaskBlockOrClozeTaskBlockOrTextTaskBlock()
		// .add(mappingTaskBlock);
		// // Truefalse to Mc
		// complexTaskDef.getCategory().get(0)
		// .getMcTaskBlockOrClozeTaskBlockOrTextTaskBlock()
		// .add(truefalseMcTaskBlock);
		// // Multichoice to Mc
		// complexTaskDef.getCategory().get(0)
		// .getMcTaskBlockOrClozeTaskBlockOrTextTaskBlock()
		// .add(mcTaskBlock);

		for (CategoryManager categoryMana : categoryManager) {
			complexTaskDef.getCategory().add(categoryMana.generateCategory());
		}

		return complexTaskDef;
	}

}