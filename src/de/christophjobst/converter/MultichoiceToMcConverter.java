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

public class MultichoiceToMcConverter {

	public static ComplexTaskDef processing(ComplexTaskDef complexTaskDef,
			Quiz quizsammlung) {

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
					.equals("multichoice")) {
				System.out.println("Es ist ein multichoice, Indexnummer: " + i);


				// Allgemeine Angaben pro Frage
				subTask.setTrash(false);
				subTask.setInteractiveFeedback(false);
				subTask.setPreserveOrderOfAnswers(false);
				subTask.setDisplayedAnswers(quizsammlung.getQuestion().get(i)
						.getAnswer().toArray().length);

				subTask.setCategory(quizsammlung.getQuestion().get(i)
						.getSingle().equals("true") ? "singleSelect"
						: "multipleSelect");

				// Spezielle Angaben pro Frage
				subTask.setProblem(quizsammlung.getQuestion().get(i)
						.getQuestiontext().getText().toString());
				subTask.setId(quizsammlung.getQuestion().get(i).getName()
						.getText().toString()
						+ "_" + rand.getRandomID());

				for (int j = 0; j < quizsammlung.getQuestion().get(i)
						.getAnswer().toArray().length; j++) {

					if (!quizsammlung.getQuestion().get(i).getAnswer().get(j)
							.getFraction().equals("0")) {
						correct.setValue(quizsammlung.getQuestion().get(i)
								.getAnswer().get(j).getText());
						correct.setId(rand.getRandomID());
						subTask.getCorrectOrIncorrect().add(correct);
						correct = new McSubTaskDef.Correct();

					} else {
						incorrect.setValue(quizsammlung.getQuestion().get(i)
								.getAnswer().get(j).getText());
						incorrect.setId(rand.getRandomID());
						subTask.getCorrectOrIncorrect().add(incorrect);
						incorrect = new McSubTaskDef.Incorrect();
					}

				}

				mcTaskBlock.getMcSubTaskDefOrChoice().add(subTask);

				subTask = new McSubTaskDef();

				
			}
		}

		complexTaskDef.getCategory().get(0)
				.getMcTaskBlockOrClozeTaskBlockOrTextTaskBlock()
				.add(mcTaskBlock);

		
		
		return complexTaskDef;
	}

}
