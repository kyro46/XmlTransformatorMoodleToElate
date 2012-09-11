/**
 * Programm zur Konvertierung von aus Moodle exportierten Übungsfragen (Moodle-XML)
 * in Elate ComplexTaskDef-XML.
 *
 * @author Christoph Jobst
 * @version 1.0
 */

package de.christophjobst.converter;

import de.christophjobst.main.RandomIdentifierGenerator;
import de.thorstenberger.taskmodel.complex.complextaskdef.TextSubTaskDef;
import generated.Quiz.Question;

public class ShortanswerToTextConverter {

	public static TextSubTaskDef processing(Question question) {

		RandomIdentifierGenerator rand = new RandomIdentifierGenerator();

		TextSubTaskDef subTask = new TextSubTaskDef();

				subTask = new TextSubTaskDef();

				String problem = question.getName()
						.getText();
				subTask.setHint(question.getName().getText().toString());

				String questionname = question
						.getQuestiontext().getText();
				String answer = question.getAnswer()
						.get(0).getText();

				subTask.setProblem(problem);
				subTask.setId(questionname);
				subTask.setCorrectionHint(answer);

				// Allgemeine Angaben pro Frage
				subTask.setTrash(false);
				subTask.setInteractiveFeedback(false);

				// Spezielle Angaben pro Frage
				subTask.setProblem(question
						.getQuestiontext().getText().toString());
				subTask.setId(question.getName()
						.getText().toString()
						+ "_" + rand.getRandomID());
		
		return subTask;
	}

}
