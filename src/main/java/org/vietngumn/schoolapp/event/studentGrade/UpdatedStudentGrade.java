package org.vietngumn.schoolapp.event.studentGrade;

import org.vietngumn.schoolapp.event.UpdatedEvent;

public class UpdatedStudentGrade extends UpdatedEvent {

	private StudentGradeIdPath gradeIdPath;
	private StudentGradeDTO details;

	public UpdatedStudentGrade(StudentGradeIdPath gradeIdPath, StudentGradeDTO details) {
		this.gradeIdPath = gradeIdPath;
		this.details = details;
	}

	public StudentGradeIdPath getGradeIdPath() {
		return gradeIdPath;
	}

	public StudentGradeDTO getDetails() {
		return details;
	}
}
