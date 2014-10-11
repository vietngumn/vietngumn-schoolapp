package org.vietngumn.schoolapp.event.studentGrade;

import org.vietngumn.schoolapp.event.CreatedEvent;

public class CreatedStudentGrade extends CreatedEvent {

	private final StudentGradeDTO details;

	public CreatedStudentGrade(final StudentGradeDTO details) {
		this.details = details;
	}

	public StudentGradeDTO getDetails() {
		return details;
	}

}
