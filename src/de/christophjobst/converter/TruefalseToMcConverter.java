/**
 * Programm zur Konvertierung von aus Moodle exportierten Übungsfragen (Moodle-XML)
 * in Elate ComplexTaskDef-XML.
 *
 * @author Christoph Jobst
 * @version 1.0
 */

package de.christophjobst.converter;

import generated.Quiz;
import de.christophjobst.main.RandomIdentifierGenerator;
import de.thorstenberger.taskmodel.complex.complextaskdef.ComplexTaskDef;
import de.thorstenberger.taskmodel.complex.complextaskdef.Config;
import de.thorstenberger.taskmodel.complex.complextaskdef.McSubTaskDef;
import de.thorstenberger.taskmodel.complex.complextaskdef.ComplexTaskDef.Category.McTaskBlock;
import de.thorstenberger.taskmodel.complex.complextaskdef.ComplexTaskDef.Category.McTaskBlock.McConfig;
import de.thorstenberger.taskmodel.complex.complextaskdef.ComplexTaskDef.Category.McTaskBlock.McConfig.Different;

public class TruefalseToMcConverter {

	public static McTaskBlock processing(Quiz quizsammlung) {

		RandomIdentifierGenerator rand = new RandomIdentifierGenerator();

		McSubTaskDef subTask = new McSubTaskDef();
		McTaskBlock mcTaskBlock = new McTaskBlock();

		Config mcTaskConfig = new Config();
		mcTaskConfig.setNoOfSelectedTasks(1);
		mcTaskConfig.setPointsPerTask(1);
		mcTaskConfig.setPreserveOrder(false);
		mcTaskBlock.setConfig(mcTaskConfig);

		McSubTaskDef.Correct correct = new McSubTaskDef.Correct();

		McSubTaskDef.Incorrect incorrect = new McSubTaskDef.Incorrect();

		McConfig mcConfig = new McConfig();
		Different different = new Different();
		different.setCorrectAnswerNegativePoints(0);
		different.setIncorrectAnswerNegativePoints(0);
		mcConfig.setDifferent(different);
		mcTaskBlock.setMcConfig(mcConfig);

		for (int i = 0; i < quizsammlung.getQuestion().toArray().length; i++) {

			if (quizsammlung.getQuestion().get(i).getType().toString()
					.equals("truefalse")) {
				System.out.println("Es ist ein truefalse, Indexnummer: " + i);


				// Allgemeine Angaben pro Frage
				subTask.setTrash(false);
				subTask.setInteractiveFeedback(false);
				subTask.setPreserveOrderOfAnswers(false);
				subTask.setDisplayedAnswers(2);

				subTask.setCategory("singleSelect");

				// Spezielle Angaben pro Frage
				subTask.setProblem(quizsammlung.getQuestion().get(i)
						.getQuestiontext().getText().toString());
				subTask.setTrash(false);
				subTask.setInteractiveFeedback(false);
				subTask.setId(quizsammlung.getQuestion().get(i).getName()
						.getText().toString()
						+ "_" + rand.getRandomID());

				correct.setId(rand.getRandomID());
				incorrect.setId(rand.getRandomID());

				if (quizsammlung.getQuestion().get(i).getAnswer().get(0)
						.getFraction().equals("100")) {
					correct.setValue("Wahr");
					incorrect.setValue("Falsch");
				} else {
					correct.setValue("Falsch");
					incorrect.setValue("Wahr");
				}

				subTask.getCorrectOrIncorrect().add(correct);
				subTask.getCorrectOrIncorrect().add(incorrect);
				correct = new McSubTaskDef.Correct();
				incorrect = new McSubTaskDef.Incorrect();

				mcTaskBlock.getMcSubTaskDefOrChoice().add(subTask);

				subTask = new McSubTaskDef();

			}
		}

		return mcTaskBlock;
	}

}
