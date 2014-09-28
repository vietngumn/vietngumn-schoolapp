package org.vietngumn.schoolapp.event.studentGrade;

import org.vietngumn.schoolapp.event.UpdatedEvent;

public class UpdatedStudentGrade extends UpdatedEvent {

	private StudentGradeIdPath studentGradeId;
	private StudentGradeDTO details;

	public UpdatedStudentGrade(StudentGradeIdPath studentGradeId, StudentGradeDTO details) {
		this.studentGradeId = studentGradeId;
		this.details = details;
	}

	public StudentGradeIdPath getStudentGradeId() {
		return studentGradeId;
	}

	public StudentGradeDTO getDetails() {
		return details;
	}
}
