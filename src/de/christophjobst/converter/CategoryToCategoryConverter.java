package de.christophjobst.converter;

import generated.Quiz.Question;
import de.christophjobst.main.RandomIdentifierGenerator;
import de.thorstenberger.taskmodel.complex.complextaskdef.Category;

public class CategoryToCategoryConverter {

	public static Category processing(Question question) {

		RandomIdentifierGenerator rand = new RandomIdentifierGenerator();

		Category category = new Category();

//		if (question.getType().toString().equals("category")) {
//			System.out.println("Es ist ein category.");
//		}

		category.setTitle(question.getCategory().getText().substring(9));
		category.setId("Kategorie_" + rand.getRandomID());
		category.setIgnoreOrderOfBlocks(false);
		category.setMixAllSubTasks(false);

		return category;
	}

}