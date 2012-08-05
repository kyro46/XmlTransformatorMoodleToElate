/**
 * Programm zur Konvertierung von aus Moodle exportierten Übungsfragen (Moodle-XML)
 * in Elate ComplexTaskDef-XML.
 *
 * @author Christoph Jobst
 * @version 1.0
 */

package de.christophjobst.converter;

import de.christophjobst.main.RandomIdentifierGenerator;
import de.thorstenberger.taskmodel.complex.complextaskdef.ComplexTaskDef.Category.TextTaskBlock;
import de.thorstenberger.taskmodel.complex.complextaskdef.Config;
import de.thorstenberger.taskmodel.complex.complextaskdef.TextSubTaskDef;
import generated.Quiz;

public class ShortanswerToTextConverter {

	public static TextTaskBlock processing(Quiz quizsammlung) {

		RandomIdentifierGenerator rand = new RandomIdentifierGenerator();

		TextSubTaskDef subTask = new TextSubTaskDef();
		TextTaskBlock textTaskBlock = new TextTaskBlock();

		Config texttaskConfig = new Config();
		texttaskConfig.setNoOfSelectedTasks(1);
		texttaskConfig.setPointsPerTask(10);
		texttaskConfig.setPreserveOrder(false);
		textTaskBlock.setConfig(texttaskConfig);

		for (int i = 0; i < quizsammlung.getQuestion().toArray().length; i++) {

			if (quizsammlung.getQuestion().get(i).getType().toString()
					.equals("shortanswer")) {
				System.out.println("Es ist ein shortanswer, Indexnummer: " + i);

				subTask = new TextSubTaskDef();

				String problem = quizsammlung.getQuestion().get(i).getName()
						.getText();
				String questionname = quizsammlung.getQuestion().get(i)
						.getQuestiontext().getText();
				String answer = quizsammlung.getQuestion().get(i).getAnswer()
						.get(0).getText();

				subTask.setProblem(problem);
				subTask.setId(questionname);
				subTask.setCorrectionHint(answer);

				// Allgemeine Angaben pro Frage
				subTask.setTrash(false);
				subTask.setInteractiveFeedback(false);

				// Spezielle Angaben pro Frage
				subTask.setProblem(quizsammlung.getQuestion().get(i)
						.getQuestiontext().getText().toString());
				subTask.setId(quizsammlung.getQuestion().get(i).getName()
						.getText().toString()
						+ "_" + rand.getRandomID());
				// System.out.println(subTask.getProblem().toString());
				textTaskBlock.getTextSubTaskDefOrChoice().add(subTask);

			}
		}
		return textTaskBlock;
	}

}
