package org.vietngumn.schoolapp.event.studentGrade;

import org.vietngumn.schoolapp.event.CreateCommand;

public class CreateStudentGradeCommand extends CreateCommand {
	private StudentGradeIdPath studentGradeId;
	private StudentGradeDTO details;

	public CreateStudentGradeCommand(final StudentGradeIdPath studentGradeId, final StudentGradeDTO gradeDTO) {
		this.studentGradeId = studentGradeId;
		this.details = gradeDTO;
	}
	
	public StudentGradeIdPath getStudentGradeId() {
		return studentGradeId;
	}

	public StudentGradeDTO getDetails() {
		return details;
	}
	
}
