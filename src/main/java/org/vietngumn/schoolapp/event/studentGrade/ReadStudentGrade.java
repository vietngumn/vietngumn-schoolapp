package org.vietngumn.schoolapp.event.studentGrade;

import org.vietngumn.schoolapp.event.ReadEvent;

public class ReadStudentGrade extends ReadEvent {
	private StudentGradeIdPath gradeIdPath;
	private StudentGradeDTO details;

	private ReadStudentGrade(final StudentGradeIdPath gradeIdPath) {
		this.gradeIdPath = gradeIdPath;
	}

	public ReadStudentGrade(final StudentGradeDTO details) {
		this.details = details;
	}
	
	public StudentGradeIdPath getStudentGradeId() {
		return gradeIdPath;
	}

	public StudentGradeDTO getDetails() {
		return details;
	}

	public static ReadStudentGrade notFound(final StudentGradeIdPath gradeIdPath) {
		ReadStudentGrade response = new ReadStudentGrade(gradeIdPath);
		response.entityFound = false;
		return response;
	}
}
