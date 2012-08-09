/**
 * Programm zur Konvertierung von aus Moodle exportierten Übungsfragen (Moodle-XML)
 * in Elate ComplexTaskDef-XML.
 *
 * @author Christoph Jobst
 * @version 1.0
 * 
 * TODO Umlaute korrigieren
 * TODO ComplexTaskDef-Category-Blöcke filtern und Fragen zuordnen
 */

package de.christophjobst.main;

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

	//private static final String QUIZ_XML = "./roma2.xml";
	private static final String COMPLEXTASKDEF_XML = "./complexTaskDef.xml";

	public static void main(String[] args) throws JAXBException, IOException {

		final String QUIZ_XML = "./" + args[0];

		// JAXB Contect für Moodle-Quiz
		JAXBContext context_quiz = JAXBContext.newInstance(Quiz.class);
		Unmarshaller um_quiz = context_quiz.createUnmarshaller();
		Quiz quizsammlung = (Quiz) um_quiz.unmarshal(new FileReader(QUIZ_XML));

		// JAXB Contect und Marshaller für ComplexTaskDef
		JAXBContext context_complexTaskDef = JAXBContext
				.newInstance(ComplexTaskDef.class);
		Marshaller m_complexTaskDef = context_complexTaskDef.createMarshaller();

		// System.out.println("Input from XML File: ");
		// m_quiz.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		// m_quiz.marshal(quizsammlung, System.out);

		// System.out.println("Debugging starten:");
		// DebuggingAusgabe.printQuestionsTypeAndAnswer(quizsammlung);
		// DebuggingAusgabe.printExistingComplexTaskDefCategoryblocks(complexTaskDef);

		
		ComplexTaskDef complexTaskDef = new ComplexTaskDef();
		complexTaskDef = Inputaufteiler.inputAufteilen(quizsammlung);

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