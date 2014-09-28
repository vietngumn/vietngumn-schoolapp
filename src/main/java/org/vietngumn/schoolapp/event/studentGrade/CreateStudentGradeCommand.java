package org.vietngumn.schoolapp.event.studentGrade;

import org.vietngumn.schoolapp.event.CreateCommand;

public class CreateStudentGradeCommand extends CreateCommand {
	private StudentGradeIdPath gradeIdPath;
	private StudentGradeDTO details;

	public CreateStudentGradeCommand(final StudentGradeIdPath gradeIdPath, final StudentGradeDTO gradeDTO) {
		this.gradeIdPath = gradeIdPath;
		this.details = gradeDTO;
	}
	
	public StudentGradeIdPath getGradeIdPath() {
		return gradeIdPath;
	}

	public StudentGradeDTO getDetails() {
		return details;
	}
	
}
