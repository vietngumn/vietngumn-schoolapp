package org.vietngumn.schoolapp.event.studentGrade;

import org.vietngumn.schoolapp.event.CreateCommand;

public class CreateStudentGradeCommand extends CreateCommand {
	private StudentGradeDTO details;

	public CreateStudentGradeCommand(final StudentGradeDTO gradeDTO) {
		this.details = gradeDTO;
	}

	public StudentGradeDTO getDetails() {
		return details;
	}
	
}
