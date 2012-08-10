/**
 * Programm zur Konvertierung von aus Moodle exportierten �bungsfragen (Moodle-XML)
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
	 * entsprechenden Konvertern zuordnen M�gliche Typen: - Essay - Cloze -
	 * Multichoice - Shortanswer - Mapping - Truefalse
	 */
	public static ComplexTaskDef inputAufteilen(Quiz quizsammlung) {

		RandomIdentifierGenerator rand = new RandomIdentifierGenerator();
		Date date = new Date();

		// Klausurdatei aufsetzen - Allgemeine Angaben
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

		/*
		 * Zuweisungs und Konvertierungsschleife f�r Category-Bl�cke
		 * Funktionsweise: 1. Alle Titel einlesen und in categoryNameList
		 * speichern Dabei Redundanz entfernen 2. Sofern erstmaliges Auftrten
		 * einer neuen Category hinzuf�gen eines neuen Objektex im
		 * categoryManager
		 */
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

		/*
		 * Zuweisungs- und Konvertierungsschleife f�r Fragen Funktionsweise: 1.
		 * Gehe alle Fragen im Moodle-Quiz durch. 2. Ist ein Category dabei? ->
		 * Gehe die Fragen ab da durch und f�ge sie dem entsprechenden Element
		 * (categoryManager) hinzu, bis eine neue Category auftaucht.
		 */
		int belongingCategoryIndex = 0;
		for (int j = 0; j < quizsammlung.getQuestion().toArray().length; j++) {
			try {

				if (quizsammlung.getQuestion().get(j).getType().toString()
						.equals("category")) {

					/*
					 * Abgleich: Ist zu welchem Objekt im categoryManager geh�rt
					 * die aktuell gefundene Category?
					 */
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

					/*
					 * TaskBlock-Erstellungsschleife Die n�chsten Elemente bis
					 * zur n�chten Category konvertieren und hinzuf�gen.
					 */
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
							// Debug:
							// System.out.println("Categoryblock nummer "
							// + belongingCategoryIndex
							// + " hat nun "
							// + categoryManager
							// .get(belongingCategoryIndex)
							// .getTextTaskBlock()
							// .getTextSubTaskDefOrChoice()
							// .toArray().length + " textfragen");
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
							/*
							 * Wird ein Category gefunden, dann verlasse die
							 * TaskBlock-Erstellungsschleife und suche dir die
							 * neue zu f�llende Category aus dem CategoryManager
							 * 
							 * TODO Das ruft nach Rekursion:
							 * 
							 * Wenn es eine Category ist und zu einer
							 * Unterkategorie geh�rt verfahre wie bisher mit den
							 * TaskBl�cken -> f�ge der aktuellen Category eine
							 * Unterkategorie hinzu.
							 * 
							 * Alternative: Category-Bl�cke einzeln erstellen
							 * (wie bisher) und iterativ gesondert einander
							 * zuweisen
							 * 
							 * In beiden F�llen: Stringparser f�r "/" in
							 * Category.Title n�tig, um Unterordner zu
							 * definieren.
							 */
							break;
						}
					}
				}
			} catch (Exception e) {
				System.out.println("PROBLEM BEIM INPUTAUFTEILER");
				e.printStackTrace();
			}
		}

		for (CategoryManager categoryMana : categoryManager) {
			complexTaskDef.getCategory().add(categoryMana.generateCategory());
		}

		return complexTaskDef;
	}

}