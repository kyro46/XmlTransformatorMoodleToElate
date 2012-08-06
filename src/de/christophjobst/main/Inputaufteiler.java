/**
 * Programm zur Konvertierung von aus Moodle exportierten Übungsfragen (Moodle-XML)
 * in Elate ComplexTaskDef-XML.
 *
 * @author Christoph Jobst
 * @version 1.0
 * 
 * TODO für jeden Taskblock die gleiche Config verwenden
 */

package de.christophjobst.main;

import de.christophjobst.converter.ClozeToClozeConverter;
import de.christophjobst.converter.EssayToTextConverter;
import de.christophjobst.converter.MatchingToMappingConverter;
import de.christophjobst.converter.MultichoiceToMcConverter;
import de.christophjobst.converter.ShortanswerToTextConverter;
import de.christophjobst.converter.TruefalseToMcConverter;
import de.thorstenberger.taskmodel.complex.complextaskdef.ComplexTaskDef;
import de.thorstenberger.taskmodel.complex.complextaskdef.Config;
import de.thorstenberger.taskmodel.complex.complextaskdef.ComplexTaskDef.Category.ClozeTaskBlock;
import de.thorstenberger.taskmodel.complex.complextaskdef.ComplexTaskDef.Category.MappingTaskBlock;
import de.thorstenberger.taskmodel.complex.complextaskdef.ComplexTaskDef.Category.McTaskBlock;
import de.thorstenberger.taskmodel.complex.complextaskdef.ComplexTaskDef.Category.TextTaskBlock;
import de.thorstenberger.taskmodel.complex.complextaskdef.ComplexTaskDef.Category.ClozeTaskBlock.ClozeConfig;
import de.thorstenberger.taskmodel.complex.complextaskdef.ComplexTaskDef.Category.MappingTaskBlock.MappingConfig;
import de.thorstenberger.taskmodel.complex.complextaskdef.ComplexTaskDef.Category.McTaskBlock.McConfig;
import de.thorstenberger.taskmodel.complex.complextaskdef.ComplexTaskDef.Category.McTaskBlock.McConfig.Different;
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

		// Klausurdatei aufsetzen. 1. Allgemeine Angaben
		ComplexTaskDef complexTaskDef = new ComplexTaskDef();
		CorrectionMode correctionMode = new CorrectionMode();
		Regular regular = new Regular();
		ComplexTaskDef.Config config = new ComplexTaskDef.Config();
		ComplexTaskDef.Category category = new ComplexTaskDef.Category();
		ComplexTaskDef.Revisions.Revision revision = new ComplexTaskDef.Revisions.Revision();
		ComplexTaskDef.Revisions revisions = new ComplexTaskDef.Revisions();

		correctionMode.setRegular(regular);
		config.setKindnessExtensionTime(2);
		config.setCorrectionMode(correctionMode);
		config.setTasksPerPage(10);
		config.setTries(5);
		config.setTasksPerPage(30);
		complexTaskDef.setConfig(config);
		complexTaskDef.setDescription("Eine Testklausur");
		complexTaskDef.setID(rand.getRandomID());
		complexTaskDef.setShowHandlingHintsBeforeStart(false);
		category.setTitle("Kategorie 1");
		category.setId("Kategorie1_" + rand.getRandomID());
		category.setIgnoreOrderOfBlocks(false);
		category.setMixAllSubTasks(false);
		revisions.getRevision().add(revision);
		revision.setAuthor("Christoph Jobst");
		revision.setDate(24801486);
		revision.setSerialNumber(1);
		complexTaskDef.setRevisions(revisions);
		complexTaskDef.setTitle("Testklausur");
		
		//Kategorie hinzufügen
		//TODO Abhängigkeit von Moodle-XML-Kategorien
		complexTaskDef.getCategory().add(category);

		// Vorbereitung ClozeTaskBlock
		ClozeTaskBlock clozeTaskBlock = new ClozeTaskBlock();
		Config clozeTaskConfig = new Config();
		clozeTaskConfig.setNoOfSelectedTasks(1);
		// TODO Punkte = Anzahl der Lücken - inkonsistent, da in Frageinstanzen
		// nicht einheitlich viele Lücken
		clozeTaskConfig.setPointsPerTask(5);
		clozeTaskConfig.setPreserveOrder(false);
		clozeTaskBlock.setConfig(clozeTaskConfig);
		ClozeConfig clozeConfig = new ClozeConfig();
		clozeConfig.setIgnoreCase(true);
		clozeConfig.setNegativePoints(0);
		clozeTaskBlock.setClozeConfig(clozeConfig);

		// Vorbereitung TextTaskBlock
		TextTaskBlock essayTextTaskBlock = new TextTaskBlock();
		Config texttaskConfig = new Config();
		texttaskConfig.setNoOfSelectedTasks(1);
		texttaskConfig.setPointsPerTask(10);
		texttaskConfig.setPreserveOrder(false);
		essayTextTaskBlock.setConfig(texttaskConfig);

		// Vorbereitung MappingTaskBlock
		MappingTaskBlock mappingTaskBlock = new MappingTaskBlock();
		Config mappingTaskConfig = new Config();
		mappingTaskConfig.setNoOfSelectedTasks(1);
		// TODO Punkte = Anzahl der Matchings - inkonsistent, da in
		// Frageinstanzen nicht einheitlich viele Matchings
		mappingTaskConfig.setPointsPerTask(5);
		mappingTaskConfig.setPreserveOrder(false);
		mappingTaskBlock.setConfig(mappingTaskConfig);
		MappingConfig mappingConfig = new MappingConfig();
		mappingConfig.setNegativePoints(0);
		mappingTaskBlock.setMappingConfig(mappingConfig);

		// Vorbereitung McTaskBlock
		McTaskBlock mcTaskBlock = new McTaskBlock();

		Config mcTaskConfig = new Config();
		mcTaskConfig.setNoOfSelectedTasks(1);
		mcTaskConfig.setPointsPerTask(1);
		mcTaskConfig.setPreserveOrder(false);
		mcTaskBlock.setConfig(mcTaskConfig);

		McConfig mcConfig = new McConfig();
		Different different = new Different();
		different.setCorrectAnswerNegativePoints(0);
		different.setIncorrectAnswerNegativePoints(0);
		mcConfig.setDifferent(different);
		mcTaskBlock.setMcConfig(mcConfig);

		// Vorbereitung ShortanswerTextTaskBlock
		TextTaskBlock shortanswerTextTaskBlock = new TextTaskBlock();

		Config shortanswertTextTaskConfig = new Config();
		shortanswertTextTaskConfig.setNoOfSelectedTasks(1);
		shortanswertTextTaskConfig.setPointsPerTask(10);
		shortanswertTextTaskConfig.setPreserveOrder(false);
		shortanswerTextTaskBlock.setConfig(texttaskConfig);

		// Vorbereitung TruefalseMcTaskBlock
		McTaskBlock truefalseMcTaskBlock = new McTaskBlock();

		Config truefalseMcTaskConfig = new Config();
		truefalseMcTaskConfig.setNoOfSelectedTasks(1);
		truefalseMcTaskConfig.setPointsPerTask(1);
		truefalseMcTaskConfig.setPreserveOrder(false);
		truefalseMcTaskBlock.setConfig(truefalseMcTaskConfig);

		McConfig truefalseMcConfig = new McConfig();
		Different truefalseDifferent = new Different();
		truefalseDifferent.setCorrectAnswerNegativePoints(0);
		truefalseDifferent.setIncorrectAnswerNegativePoints(0);
		truefalseMcConfig.setDifferent(truefalseDifferent);
		truefalseMcTaskBlock.setMcConfig(truefalseMcConfig);

		// Zuweisungs- und Konvertierungsschleife
		for (int i = 0; i < quizsammlung.getQuestion().toArray().length; i++) {
			try {
				if (quizsammlung.getQuestion().get(i).getType().toString()
						.equals("essay")) {
					essayTextTaskBlock.getTextSubTaskDefOrChoice().add(
							EssayToTextConverter.processing(quizsammlung
									.getQuestion().get(i)));

				}

				if (quizsammlung.getQuestion().get(i).getType().toString()
						.equals("cloze")) {
					clozeTaskBlock.getClozeSubTaskDefOrChoice().add(
							ClozeToClozeConverter.processing(quizsammlung
									.getQuestion().get(i)));
				}

				if (quizsammlung.getQuestion().get(i).getType().toString()
						.equals("truefalse")) {
					truefalseMcTaskBlock.getMcSubTaskDefOrChoice().add(
							TruefalseToMcConverter.processing(quizsammlung
									.getQuestion().get(i)));
				}

				if (quizsammlung.getQuestion().get(i).getType().toString()
						.equals("multichoice")) {
					mcTaskBlock.getMcSubTaskDefOrChoice().add(
							MultichoiceToMcConverter.processing(quizsammlung
									.getQuestion().get(i)));

				}

				if (quizsammlung.getQuestion().get(i).getType().toString()
						.equals("shortanswer")) {
					shortanswerTextTaskBlock.getTextSubTaskDefOrChoice().add(
							ShortanswerToTextConverter.processing(quizsammlung
									.getQuestion().get(i)));
				}

				if (quizsammlung.getQuestion().get(i).getType().toString()
						.equals("matching")) {
					mappingTaskBlock.getMappingSubTaskDefOrChoice().add(
							MatchingToMappingConverter.processing(quizsammlung
									.getQuestion().get(i)));

				}

			} catch (Exception e) {
				System.out.println("PROBLEM BEIM INPUTAUFTEILER");
				e.printStackTrace();
			}

		}

		// Essay to Text
		complexTaskDef.getCategory().get(0)
				.getMcTaskBlockOrClozeTaskBlockOrTextTaskBlock()
				.add(essayTextTaskBlock);
		// Shortanswer to Text
		complexTaskDef.getCategory().get(0)
				.getMcTaskBlockOrClozeTaskBlockOrTextTaskBlock()
				.add(shortanswerTextTaskBlock);
		// Cloze to Cloze
		complexTaskDef.getCategory().get(0)
				.getMcTaskBlockOrClozeTaskBlockOrTextTaskBlock()
				.add(clozeTaskBlock);
		// Matching to Mapping
		complexTaskDef.getCategory().get(0)
				.getMcTaskBlockOrClozeTaskBlockOrTextTaskBlock()
				.add(mappingTaskBlock);
		// Truefalse to Mc
		complexTaskDef.getCategory().get(0)
				.getMcTaskBlockOrClozeTaskBlockOrTextTaskBlock()
				.add(truefalseMcTaskBlock);
		// Multichoice to Mc
		complexTaskDef.getCategory().get(0)
				.getMcTaskBlockOrClozeTaskBlockOrTextTaskBlock()
				.add(mcTaskBlock);

		return complexTaskDef;
	}

}
