package org.vietngumn.schoolapp.event.studentGrade;

import org.vietngumn.schoolapp.event.DeleteCommand;

public class DeleteStudentGradeCommand extends DeleteCommand {

	private StudentGradeIdPath studentGradeId;

	public DeleteStudentGradeCommand(final StudentGradeIdPath studentGradeId) {
		this.studentGradeId = studentGradeId;
	}

	public StudentGradeIdPath getStudentGradeId() {
		return studentGradeId;
	}
	
}
