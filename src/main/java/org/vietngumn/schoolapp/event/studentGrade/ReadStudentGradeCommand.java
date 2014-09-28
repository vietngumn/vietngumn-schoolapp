package org.vietngumn.schoolapp.event.studentGrade;

import org.vietngumn.schoolapp.event.ReadCommand;

public class ReadStudentGradeCommand extends ReadCommand {
	private StudentGradeIdPath gradeIdPath;

	public ReadStudentGradeCommand(final StudentGradeIdPath gradeIdPath) {
		this.gradeIdPath = gradeIdPath;
	}

	public StudentGradeIdPath getGradeIdPath() {
		return this.gradeIdPath;
	}
	
}
