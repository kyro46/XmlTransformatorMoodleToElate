/**
 * Programm zur Konvertierung von aus Moodle exportierten Übungsfragen (Moodle-XML)
 * in Elate ComplexTaskDef-XML.
 *
 * @author Christoph Jobst
 * @version 1.0
 * 
 * 
 *TODO Inputaufteiler nutzen, wg. Geschwindigkeit
 *TODO Dateipfade über String[] args fangen
 *TODO ComplexTaskDef-Category-Blöcke erschaffen, gem. category-Blöcken in Moodle-XML
 *TODO Komplette ComplexTaskDef.xml selbst generieren - ohne vorhandenen Ausgangsrahmen
 */

package de.christophjobst.main;

import de.christophjobst.converter.*;
//import de.christophjobst.test.*;
import de.thorstenberger.taskmodel.complex.complextaskdef.*;

import generated.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class ElateXMLMain {

	private static final String QUIZ_XML = "./roma4.xml";
	private static final String COMPLEXTASKDEF_XML = "./complexTaskDef.xml";

	public static void main(String[] args) throws JAXBException, IOException {

		// JAXB Contect für Moodle-Quiz
		JAXBContext context_quiz = JAXBContext.newInstance(Quiz.class);
		Unmarshaller um_quiz = context_quiz.createUnmarshaller();
		Quiz quizsammlung = (Quiz) um_quiz.unmarshal(new FileReader(QUIZ_XML));

		// JAXB Contect und Marshaller für ComplexTaskDef
		JAXBContext context_complexTaskDef = JAXBContext
				.newInstance(ComplexTaskDef.class);
		Marshaller m_complexTaskDef = context_complexTaskDef.createMarshaller();
		Unmarshaller um_complexTaskDef = context_complexTaskDef
				.createUnmarshaller();
		ComplexTaskDef complexTaskDef = (ComplexTaskDef) um_complexTaskDef
				.unmarshal(new FileReader(COMPLEXTASKDEF_XML));

		// Ausgabe des eingelesenen XML
		System.out.println("Input from XML File: ");
		// m_quiz.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		// m_quiz.marshal(quizsammlung, System.out);

		// #########################################################################
		// Objekte zur Weiterverwendung:
		// Quellobjekt: quizsammlung
		// Zielobjekt: complexTaskDef

		// System.out.println("Debugging starten:");
		//DebuggingAusgabe.printQuestionsTypeAndAnswer(quizsammlung);
		// DebuggingAusgabe.printExistingComplexTaskDefCategoryblocks(complexTaskDef);
		// System.out.println("INITIALISIERUNG ENDE");
		// #########################################################################

		// Umwandlungen
		// 1. Freitextaufgaben
		complexTaskDef = EssayToTextConverter.processing(complexTaskDef,
				quizsammlung);

		// 2. Shortanswer-Aufgaben
		complexTaskDef = ShortanswerToTextConverter.processing(complexTaskDef,
				quizsammlung);

		// 3. True-false-Aufgaben
		complexTaskDef = TruefalseToMcConverter.processing(complexTaskDef,
				quizsammlung);

		// 4. Multichoice-Aufgaben
		complexTaskDef = MultichoiceToMcConverter.processing(complexTaskDef,
				quizsammlung);
		
		// 5. Cloze-Aufgaben
		complexTaskDef = ClozeToClozeConverter.processing(complexTaskDef,
				quizsammlung);
		
		//6. Matching-Aufgaben
		complexTaskDef = MatchingToMappingConverter.processing(complexTaskDef, quizsammlung);

		// Ausgabe des generierten XML
		System.out.println("Output from XML File: ");
		m_complexTaskDef.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
				Boolean.TRUE);
		m_complexTaskDef.marshal(complexTaskDef, System.out);

		Writer w = null;
		try {
			w = new FileWriter(COMPLEXTASKDEF_XML);
			m_complexTaskDef.marshal(complexTaskDef, w);
		} finally {
			try {
				w.close();
			} catch (Exception e) {
			}
		}
	}
}