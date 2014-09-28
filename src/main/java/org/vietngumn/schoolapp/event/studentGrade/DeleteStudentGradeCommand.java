package org.vietngumn.schoolapp.event.studentGrade;

import org.vietngumn.schoolapp.event.DeleteCommand;

public class DeleteStudentGradeCommand extends DeleteCommand {

	private StudentGradeIdPath gradeIdPath;

	public DeleteStudentGradeCommand(final StudentGradeIdPath studentGradeId) {
		this.gradeIdPath = studentGradeId;
	}

	public StudentGradeIdPath getGradeIdPath() {
		return gradeIdPath;
	}
	
}
