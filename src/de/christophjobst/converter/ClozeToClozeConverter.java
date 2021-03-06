/**
 * Programm zur Konvertierung von aus Moodle exportierten Übungsfragen (Moodle-XML)
 * in Elate ComplexTaskDef-XML.
 *
 * @author Christoph Jobst
 * @version 1.0
 */

package de.christophjobst.converter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import generated.Quiz.Question;
import de.christophjobst.main.Base64Relocator;
import de.christophjobst.main.RandomIdentifierGenerator;
import de.thorstenberger.taskmodel.complex.complextaskdef.ClozeSubTaskDef.Cloze;
import de.thorstenberger.taskmodel.complex.complextaskdef.ClozeSubTaskDef.Cloze.Gap;
import de.thorstenberger.taskmodel.complex.complextaskdef.ClozeSubTaskDef;

public class ClozeToClozeConverter {

	public static float punktzahl = 0;

	public static ClozeSubTaskDef processing(Question question)
			throws ParserConfigurationException, SAXException, IOException,
			TransformerException {

		RandomIdentifierGenerator rand = new RandomIdentifierGenerator();

		ClozeSubTaskDef subTask = new ClozeSubTaskDef();

		// Allgemeine Angaben pro Frage
		subTask.setTrash(false);
		subTask.setInteractiveFeedback(false);
		subTask.setCorrectionHint(" ");
		subTask.setHint(Base64Relocator.relocateBase64(question
				.getGeneralfeedback().getText(), question.getQuestiontext()
				.getFile()));

		// Spezielle Angaben pro Frage
		subTask.setId(question.getName().getText().toString() + "_"
				+ rand.getRandomID());

		String problem = "Lösen Sie folgenden Lückentext.";
		
		// Konvertierung des String in separaten Block,
		// falls bei häufiger Nutzung Auslagerung nötig
		try {
			byte[] bytes;
			if (!System.getProperty("os.name").contains("indows")) {
				bytes = problem.getBytes("UTF-8");
				problem = new String(bytes);
				// System.out.println(problem);
			}
			subTask.setProblem(problem);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		subTask.setCloze(clozeParser(Base64Relocator.relocateBase64(question
				.getQuestiontext().getText(), question.getQuestiontext()
				.getFile())));

		return subTask;
	}

	private static Cloze clozeParser(String moodleText) {

		Cloze cloze = new Cloze();

		punktzahl = 0;
		int klammeraufindex = 0;
		int klammerzuindex = 0;

		int shortanswerindex = 0;
		int multichoiceindex = 0;
		int numericalindex = 0;
		int gapIndexToUse = 0;

		shortanswerindex = moodleText.indexOf(":SHORTANSWER:");
		multichoiceindex = moodleText.indexOf(":MULTICHOICE:");
		numericalindex = moodleText.indexOf(":NUMERICAL:");

		if (shortanswerindex == -1) shortanswerindex = Math.max(numericalindex, multichoiceindex) +1;
		if (multichoiceindex == -1) multichoiceindex = Math.max(numericalindex, shortanswerindex) +1;
		if (numericalindex == -1) numericalindex = Math.max(shortanswerindex, multichoiceindex) +1;
		
		
		// Die { und } suchen nachdem man :SHORTANSWER: :MULTICHOICE: oder
		// :NUMERICAL: gefunden hat
		do {
			
			gapIndexToUse = Math.min(shortanswerindex,
					Math.min(multichoiceindex, numericalindex));

			klammeraufindex = moodleText.lastIndexOf("{", gapIndexToUse);

			if (klammeraufindex != -1) {

				// Text am Anfang der Aufgabe
				if (klammerzuindex == 0) {
					cloze.getTextOrGap().add(
							moodleText.substring(klammerzuindex,
									klammeraufindex));
				} else {
					// Text zwischen 2 Lücken
					cloze.getTextOrGap().add(
							moodleText.substring(klammerzuindex + 1,
									klammeraufindex));
				}

				klammerzuindex = moodleText.indexOf("}", klammeraufindex);
				cloze.getTextOrGap().add(
						gapBuilder(moodleText.substring(klammeraufindex + 1,
								klammerzuindex)));
			} else {
				// Text nach der letzten Lücke
				cloze.getTextOrGap().add(
						moodleText.substring(klammerzuindex + 1));
			}

			shortanswerindex = moodleText.indexOf(":SHORTANSWER:",
					gapIndexToUse + 1);
			multichoiceindex = moodleText.indexOf(":MULTICHOICE:",
					gapIndexToUse + 1);
			numericalindex = moodleText.indexOf(":NUMERICAL:",
					gapIndexToUse + 1);
			
			//Die Suche nach dem kleinsten Zähler sorgt bei fehlendem Vorkommen für Fehler
			//Weil dann -1 im Index steht was immer die kleinste Zahl ist -> Algo. terminiert
			// -> Wenn Index == -1 -> Mit größerer Zahl ersetzen... naja...
			if (shortanswerindex == -1) shortanswerindex = Math.max(numericalindex, multichoiceindex) +1;
			if (multichoiceindex == -1) multichoiceindex = Math.max(numericalindex, shortanswerindex) +1;
			if (numericalindex == -1) numericalindex = Math.max(shortanswerindex, multichoiceindex) +1;
			
			
			
		} while (klammeraufindex != -1);

		// System.out.println("Gesamtpunktzahl für die Aufgabe: " + punktzahl);

		return cloze;
	}

	private static Gap gapBuilder(String input) {

		Gap gap = new Gap();
		gap.setIgnoreCase(true);
		String correctAnswer;

		// try {
		// punktzahl += Float.parseFloat(input.substring(0,
		// input.indexOf(":")));
		// } catch (NumberFormatException e) {
		// punktzahl += 1;
		// }

		// Jede Lücke bekommt standardmäßig genau 1 Punkt
		punktzahl += 1;

		int nextAnswer = 0;
		// System.out.println(input);
		while ((nextAnswer = input.indexOf("=", nextAnswer + 1)) != -1) {
			//TODO falsche Antworten rausfiltern
			//Momentan: {1:MULTICHOICE:=a~b~=c~b~=jo}
			//Wird zu: [ a~b | c~b | jo ]
			if (input.indexOf("=", nextAnswer + 1) == -1) {
				correctAnswer = input.substring(nextAnswer + 1);
//				 System.out.println(correctAnswer);
			} else {
				correctAnswer = input.substring(nextAnswer + 1,
						input.indexOf("=", nextAnswer + 1) - 1);
//				 System.out.println(correctAnswer);
			}

			gap.getCorrect().add(correctAnswer);

			correctAnswer = new String();

		}

		return gap;
	}

}
