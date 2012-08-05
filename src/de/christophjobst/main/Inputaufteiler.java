/**
 * Programm zur Konvertierung von aus Moodle exportierten Übungsfragen (Moodle-XML)
 * in Elate ComplexTaskDef-XML.
 *
 * @author Christoph Jobst
 * @version 1.0
 */

package de.christophjobst.main;

import de.thorstenberger.taskmodel.complex.complextaskdef.ComplexTaskDef;
import generated.*;

public class Inputaufteiler {

	/*
	Quiz-Input nach Knoten der Aufgabentypen durchlaufen
	und den entsprechenden Konvertern zuordnen
	Mögliche Typen:
		- Essay
		- Cloze
		- Multichoice
		- Shortanswer
		- Mapping
		- Truefalse
	*/
	public static void inputAufteilen(ComplexTaskDef complexTaskDef, Quiz quizsammlung) {

		for (int i = 0; i < quizsammlung.getQuestion().toArray().length; i++) {
			try {
				if (quizsammlung.getQuestion().get(i).getType().toString()
						.equals("essay")) {
					System.out.println("Es ist ein essay, Indexnummer: " + i);
					//EssayToTextConverter.processing(quizsammlung.getQuestion().get(i));

				}

				if (quizsammlung.getQuestion().get(i).getType().toString()
						.equals("cloze")) {

					System.out.println("Es ist ein cloze, Indexnummer: " + i);

				}

				if (quizsammlung.getQuestion().get(i).getType().toString()
						.equals("truefalse")) {

					System.out.println("Es ist ein truefalse, Indexnummer: "
							+ i);

				}

				if (quizsammlung.getQuestion().get(i).getType().toString()
						.equals("multichoice")) {

					System.out.println("Es ist ein multichoice, Indexnummer: "
							+ i);

				}

				if (quizsammlung.getQuestion().get(i).getType().toString()
						.equals("shortanswer")) {

					System.out.println("Es ist ein shortanswer, Indexnummer: "
							+ i);

				}

				if (quizsammlung.getQuestion().get(i).getType().toString()
						.equals("matching")) {

					System.out
							.println("Es ist ein matching, Indexnummer: " + i);

				}
			} catch (Exception e) {
				System.out.println("PROBLEM BEIM INPUTAUFTEILER");
			}

		}

	}

}
