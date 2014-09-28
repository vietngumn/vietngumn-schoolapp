package org.vietngumn.schoolapp.event.studentGrade;

import org.vietngumn.schoolapp.event.UpdateCommand;

public class UpdateStudentGradeCommand extends UpdateCommand {

	private StudentGradeIdPath gradeIdPath;
	private StudentGradeDTO details;

	public UpdateStudentGradeCommand(final StudentGradeIdPath gradeIdPath, final StudentGradeDTO details) {
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
