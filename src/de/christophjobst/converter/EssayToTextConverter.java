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

public class EssayToTextConverter {

	public static TextSubTaskDef processing(Question question) {

		RandomIdentifierGenerator rand = new RandomIdentifierGenerator();

		TextSubTaskDef subTask = new TextSubTaskDef();

		if (question.getType().toString().equals("essay")) {
			System.out.println("Es ist ein essay.");

			subTask.setProblem(question.getQuestiontext().getText().toString());
			subTask.setTrash(false);
			subTask.setInteractiveFeedback(false);
			subTask.setId(question.getName().getText().toString() + "_"
					+ rand.getRandomID());

		}

		return subTask;
	}

}
