package org.vietngumn.schoolapp.event.studentGrade;

import org.vietngumn.schoolapp.event.UpdateCommand;

public class UpdateStudentGradeCommand extends UpdateCommand {

	private StudentGradeDTO details;

	public UpdateStudentGradeCommand(final StudentGradeDTO details) {
		this.details = details;
	}

	public StudentGradeDTO getDetails() {
		return details;
	}
}
