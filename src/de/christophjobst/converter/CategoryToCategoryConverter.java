package de.christophjobst.converter;

import generated.Quiz.Question;
import de.christophjobst.main.RandomIdentifierGenerator;
import de.thorstenberger.taskmodel.complex.complextaskdef.ComplexTaskDef;
import de.thorstenberger.taskmodel.complex.complextaskdef.ComplexTaskDef.Category;

public class CategoryToCategoryConverter {

	public static Category processing(Question question) {

		RandomIdentifierGenerator rand = new RandomIdentifierGenerator();

		ComplexTaskDef.Category category = new ComplexTaskDef.Category();

		if (question.getType().toString().equals("category")) {
			System.out.println("Es ist ein category.");
		}

		category.setTitle(question.getCategory().getText());
		category.setId("Kategorie_" + rand.getRandomID());
		category.setIgnoreOrderOfBlocks(false);
		category.setMixAllSubTasks(false);

		return category;
	}

}
