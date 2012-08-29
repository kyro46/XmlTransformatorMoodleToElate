package de.christophjobst.main;

import java.util.ArrayList;
import java.util.List;

import de.thorstenberger.taskmodel.complex.complextaskdef.ComplexTaskDef;

public class CategoryAssignment {

	// Alle Category-Blöcke auf einer einzigen Ebene
	public static ComplexTaskDef assignFlatCategories(
			ComplexTaskDef complexTaskDef,
			List<CategoryManager> categoryManagerList) {
		for (CategoryManager categoryManager : categoryManagerList) {
			complexTaskDef.getCategory()
					.add(categoryManager.generateCategory());
		}
		return complexTaskDef;
	}

	// Category-Blöcke gemäß ihrer Hierarchie schachteln
	public static ComplexTaskDef assignSubCategories(
			ComplexTaskDef complexTaskDef,
			List<CategoryManager> categoryManagerList) {

		// TODO Gem. CategoryAssignment nur die oberen Elemente zuweisen, die
		// anderen sind in diesen bereits enthalten!

		// 1. Cat-Name erhalten
		List<String> nameList = new ArrayList<String>();

		for (CategoryManager categoryMana : categoryManagerList) {
			nameList.add(categoryMana.getTitle().toString());
		}

		// 2. Namen auf einzelne Elemente reduzieren
		// Falls Elemente gleich heißen -> die ganze Kette speichern

		// Debug Category-Namen anzeigen lassen
		for (String a : nameList) {
			System.out.println(a);
		}
		System.out.println("................");
		// Ende Debug

		int lastSlashIndex = 0;
		int nextSlashIndex = 0;
		List<String> categoryNameParts = new ArrayList<String>();
		List<List<String>> pathNames = new ArrayList<List<String>>();

		for (int i = 0; i < nameList.toArray().length; i++) {
			System.out.println("Durchlauf :" + i);
			nextSlashIndex = 0;
			lastSlashIndex = 0;

			while (true) {
				nextSlashIndex = nameList.get(i).indexOf("/",
						lastSlashIndex + 1);

				// System.out.println(lastSlashIndex);
				// System.out.println(nextSlashIndex);

				if (nextSlashIndex == -1) {
					// System.out.println(nameList.get(i).substring(lastSlashIndex+1));
					categoryNameParts.add(nameList.get(i).substring(
							lastSlashIndex + 1));
					break;
				}

				if (lastSlashIndex != 0) {
					// System.out.println(nameList.get(i).substring(lastSlashIndex+1,nextSlashIndex));
					categoryNameParts.add(nameList.get(i).substring(
							lastSlashIndex + 1, nextSlashIndex));

				} else {
					// System.out.println(nameList.get(i).substring(lastSlashIndex,nextSlashIndex));
					categoryNameParts.add(nameList.get(i).substring(
							lastSlashIndex, nextSlashIndex));

				}
				lastSlashIndex = nextSlashIndex;

			}

			for (String a : categoryNameParts) {
				System.out.println(a);
			}

			pathNames.add(categoryNameParts);
			categoryNameParts = new ArrayList<String>();
		}

		// 3. Jetzt sind die einzelnen Namensbestandteile als Liste in der Liste
		// categoryNameParts gespeichert
		// Nun muss nach Unterkategorien gesucht und diese im CategoryManager
		// bei SubCategory eingetragen werden

		// 3.1 Suche größte Pfadtiefe
		int max_depth = 0;
		for (List<String> list : pathNames) {
			if (list.toArray().length > max_depth)
				max_depth = list.toArray().length;
		}
		System.out.println("Größte Pfadtiefe = " + max_depth);

		// 3.2 Vom Allgemeinen ins Spezielle durchiterieren und die SubCat's
		// zuweisen

		/*
		 * wurzelelement ist tiefe 0 nehme tiefe 1 weise alles mit tiefe 1 der
		 * wurzel zu
		 * 
		 * suche alles mit tiefe 2, was zu einer bestimmten tiefe 1 gehört weise
		 * es den entsprechenden tiefe 1-elementen zu
		 * 
		 * wiederhole das für alle unterschiedlichen elemente auf ebene 2
		 * 
		 * wieder hole das mit den anderen ebenen, bis max_depth erreicht
		 */

		for (int i = 0; i < pathNames.toArray().length; i++) {
			for (int j = 0; i < pathNames.get(i).toArray().length; i++) {
				
				
				
				
				
			}
		}

		return complexTaskDef;

	}

}