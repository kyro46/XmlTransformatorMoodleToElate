/**
 * Programm zur Konvertierung von aus Moodle exportierten Übungsfragen (Moodle-XML)
 * in Elate ComplexTaskDef-XML.
 *
 * @author Christoph Jobst
 * @version 1.0
 */

package de.christophjobst.converter;

import generated.Quiz.Question;
import de.christophjobst.main.RandomIdentifierGenerator;
import de.thorstenberger.taskmodel.complex.complextaskdef.McSubTaskDef;

public class MultichoiceToMcConverter {

	public static McSubTaskDef processing(
			Question question) {

		RandomIdentifierGenerator rand = new RandomIdentifierGenerator();

		McSubTaskDef subTask = new McSubTaskDef();

		McSubTaskDef.Correct correct = new McSubTaskDef.Correct();
		McSubTaskDef.Incorrect incorrect = new McSubTaskDef.Incorrect();


			if (question.getType().toString()
					.equals("multichoice")) {
				System.out.println("Es ist ein multichoice.");


				// Allgemeine Angaben pro Frage
				subTask.setTrash(false);
				subTask.setInteractiveFeedback(false);
				subTask.setPreserveOrderOfAnswers(false);
				subTask.setDisplayedAnswers(question
						.getAnswer().toArray().length);

				subTask.setCategory(question
						.getSingle().equals("true") ? "singleSelect"
						: "multipleSelect");

				// Spezielle Angaben pro Frage
				subTask.setProblem(question
						.getQuestiontext().getText().toString());
				subTask.setHint(question.getName().getText().toString());
				subTask.setId(question.getName()
						.getText().toString()
						+ "_" + rand.getRandomID());

				for (int j = 0; j < question
						.getAnswer().toArray().length; j++) {

					if (!question.getAnswer().get(j)
							.getFraction().equals("0")) {
						correct.setValue(question
								.getAnswer().get(j).getText());
						correct.setId(rand.getRandomID());
						subTask.getCorrectOrIncorrect().add(correct);
						correct = new McSubTaskDef.Correct();

					} else {
						incorrect.setValue(question
								.getAnswer().get(j).getText());
						incorrect.setId(rand.getRandomID());
						subTask.getCorrectOrIncorrect().add(incorrect);
						incorrect = new McSubTaskDef.Incorrect();
					}

				}



				
			}
		
		
		return subTask;
	}

}
