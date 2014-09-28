package org.vietngumn.schoolapp.event.studentGrade;

import org.vietngumn.schoolapp.event.ReadEvent;

public class ReadStudentGrade extends ReadEvent {
	private StudentGradeIdPath studentGradeId;
	private StudentGradeDTO details;

	private ReadStudentGrade(final StudentGradeIdPath studentGradeId) {
		this.studentGradeId = studentGradeId;
	}

	public ReadStudentGrade(final StudentGradeIdPath studentGradeId, final StudentGradeDTO details) {
		this(studentGradeId);
		this.details = details;
	}
	
	public StudentGradeIdPath getStudentGradeId() {
		return studentGradeId;
	}

	public StudentGradeDTO getDetails() {
		return details;
	}

	public static ReadStudentGrade notFound(final StudentGradeIdPath studentGradeId) {
		ReadStudentGrade response = new ReadStudentGrade(studentGradeId);
		response.entityFound = false;
		return response;
	}
}
