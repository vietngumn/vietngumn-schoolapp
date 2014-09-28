package org.vietngumn.schoolapp.event.studentGrade;

import org.vietngumn.schoolapp.event.UpdateCommand;

public class UpdateStudentGradeCommand extends UpdateCommand {

	private StudentGradeIdPath studentGradeId;
	private StudentGradeDTO details;

	public UpdateStudentGradeCommand(final StudentGradeIdPath studentGradeId, final StudentGradeDTO details) {
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
