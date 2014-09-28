package org.vietngumn.schoolapp.event.studentGrade;

import org.vietngumn.schoolapp.event.CreatedEvent;

public class CreatedStudentGrade extends CreatedEvent {

	private final StudentGradeIdPath newCreatedId;
	private final StudentGradeDTO details;

	public CreatedStudentGrade(final StudentGradeIdPath newCreatedId, final StudentGradeDTO details) {
		this.newCreatedId = newCreatedId;
		this.details = details;
	}

	public StudentGradeDTO getDetails() {
		return details;
	}

	public StudentGradeIdPath getNewCreatedId() {
		return newCreatedId;
	}
}
