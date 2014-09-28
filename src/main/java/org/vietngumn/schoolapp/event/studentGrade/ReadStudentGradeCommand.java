package org.vietngumn.schoolapp.event.studentGrade;

import org.vietngumn.schoolapp.event.ReadCommand;

public class ReadStudentGradeCommand extends ReadCommand {
	private StudentGradeIdPath studentGradeId;

	public ReadStudentGradeCommand(final StudentGradeIdPath studentGradeId) {
		this.studentGradeId = studentGradeId;
	}

	public StudentGradeIdPath getStudentGradeId() {
		return this.studentGradeId;
	}
	
}
