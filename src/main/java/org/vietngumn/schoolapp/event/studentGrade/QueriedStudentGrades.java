package org.vietngumn.schoolapp.event.studentGrade;

import java.util.Collections;
import java.util.List;

import org.vietngumn.schoolapp.event.QueriedEvent;

public class QueriedStudentGrades extends QueriedEvent {
	private final List<StudentGradeDTO> grades;

	public QueriedStudentGrades(List<StudentGradeDTO> grades) {
		this.grades = Collections.unmodifiableList(grades);
	}

	public List<StudentGradeDTO> getStudentGrades() {
		return this.grades;
	}
	
}
