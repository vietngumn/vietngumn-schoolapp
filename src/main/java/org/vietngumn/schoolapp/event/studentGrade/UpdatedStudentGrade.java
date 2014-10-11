package org.vietngumn.schoolapp.event.studentGrade;

import org.vietngumn.schoolapp.event.UpdatedEvent;

public class UpdatedStudentGrade extends UpdatedEvent {

	private StudentGradeDTO details;

	public UpdatedStudentGrade(final StudentGradeDTO details) {
		this.details = details;
	}

	public StudentGradeDTO getDetails() {
		return details;
	}
}
