/**
 * Programm zur Konvertierung von aus Moodle exportierten Übungsfragen (Moodle-XML)
 * in Elate ComplexTaskDef-XML.
 *
 * @author Christoph Jobst
 * @version 1.0
 */

package de.christophjobst.converter;

import java.util.*;

import generated.Quiz;

import de.christophjobst.main.RandomIdentifierGenerator;
import de.thorstenberger.taskmodel.complex.complextaskdef.ComplexTaskDef.Category.MappingTaskBlock;
import de.thorstenberger.taskmodel.complex.complextaskdef.ComplexTaskDef.Category.MappingTaskBlock.MappingConfig;
import de.thorstenberger.taskmodel.complex.complextaskdef.MappingSubTaskDef.Concept;
import de.thorstenberger.taskmodel.complex.complextaskdef.MappingSubTaskDef.Assignment;
import de.thorstenberger.taskmodel.complex.complextaskdef.Config;
import de.thorstenberger.taskmodel.complex.complextaskdef.MappingSubTaskDef;

public class MatchingToMappingConverter {

	public static MappingTaskBlock processing(
			Quiz quizsammlung) {

		RandomIdentifierGenerator rand = new RandomIdentifierGenerator();

		MappingSubTaskDef subTask = new MappingSubTaskDef();
		MappingTaskBlock mappingTaskBlock = new MappingTaskBlock();

		Config mappingTaskConfig = new Config();
		mappingTaskConfig.setNoOfSelectedTasks(1);
		mappingTaskConfig.setPointsPerTask(5);// TODO Punkte = Anzahl der
												// Matchings -
												// inkonsistent, da in
												// Frageinstanzen nicht
												// einheitlich
												// viele Matchings
		mappingTaskConfig.setPreserveOrder(false);
		mappingTaskBlock.setConfig(mappingTaskConfig);

		MappingConfig mappingConfig = new MappingConfig();
		mappingConfig.setNegativePoints(0);
		mappingTaskBlock.setMappingConfig(mappingConfig);

		for (int i = 0; i < quizsammlung.getQuestion().toArray().length; i++) {

			if (quizsammlung.getQuestion().get(i).getType().toString()
					.equals("matching")) {
				System.out.println("Es ist ein matching, Indexnummer: " + i);

				// Allgemeine Angaben pro Frage
				subTask.setTrash(false);
				subTask.setInteractiveFeedback(false);
				subTask.setCorrectionHint(" ");
				subTask.setHint(" ");

				// Spezielle Angaben pro Frage
				subTask.setId(quizsammlung.getQuestion().get(i).getName()
						.getText().toString()
						+ "_" + rand.getRandomID());

				subTask.setProblem(quizsammlung.getQuestion().get(i)
						.getQuestiontext().getText());

				Concept concept = new Concept();
				Assignment assignment = new Assignment();

				/*
				 * 1. lies alle assignments ein und gib ihnen eine id 2. wenn
				 * ein string schon vorhanden, tue nichts 3. lies die concepts
				 * ein und suche bei den assignments nach der Lösung 4. weise
				 * die LösungsID dem concept zu
				 */
				List<String> assignmentList = new ArrayList<String>();
				List<String> conceptList = new ArrayList<String>();
				List<String> conceptAnswerListRaw = new ArrayList<String>();
				for (int j = 0; j < quizsammlung.getQuestion().get(i)
						.getSubquestion().toArray().length; j++) {

					if (!assignmentList.contains(quizsammlung.getQuestion()
							.get(i).getSubquestion().get(j).getAnswer()
							.getText())) {
						assignmentList.add(quizsammlung.getQuestion().get(i)
								.getSubquestion().get(j).getAnswer().getText());
					}
					conceptList.add(quizsammlung.getQuestion().get(i)
							.getSubquestion().get(j).getText());
					conceptAnswerListRaw.add(quizsammlung.getQuestion().get(i)
							.getSubquestion().get(j).getAnswer().getText());
				}

				List<Assignment> assignmentObjectList = new ArrayList<MappingSubTaskDef.Assignment>(); 
								
				List<String> assingmentIDList = new ArrayList<String>();
				for (int j = 0; j < assignmentList.toArray().length; j++) {
					assingmentIDList.add(rand.getRandomID());
					assignment.setId(assingmentIDList.get(j));
					assignment.setName(assignmentList.get(j));
					assignmentObjectList.add(assignment);
					assignment = new Assignment();
				}

				List<String> conceptIDList = new ArrayList<String>();
				for (int j = 0; j < conceptAnswerListRaw.toArray().length; j++) {
					// if
					// (assignmentList.contains(conceptAnswerListRaw.get(j))){
					conceptIDList.add(assingmentIDList.get(assignmentList
							.indexOf(conceptAnswerListRaw.get(j))));
					// }
				}

				for (int j = 0; j < conceptList.toArray().length; j++) {
					concept.setName(conceptList.get(j));
					concept.getCorrectAssignmentID().add(conceptIDList.get(j));
					subTask.getConcept().add(concept);
					concept = new Concept();
				}

				//Assignments zufällig anordnen
				Collections.shuffle(assignmentObjectList);

				//Assignemntobjekte der SubTask zuordnen
				for (int j = 0; j < assignmentObjectList.toArray().length; j++) {
				subTask.getAssignment().add(assignmentObjectList.get(j));
				}

				
				
				mappingTaskBlock.getMappingSubTaskDefOrChoice().add(subTask);

				subTask = new MappingSubTaskDef();
			}
		}

		return mappingTaskBlock;
	}

}
