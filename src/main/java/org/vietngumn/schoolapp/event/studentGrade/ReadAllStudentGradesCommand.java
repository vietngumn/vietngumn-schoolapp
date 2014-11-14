package org.vietngumn.schoolapp.event.studentGrade;

import org.vietngumn.schoolapp.event.QueryCommand;

public class ReadAllStudentGradesCommand extends QueryCommand {
	private StudentGradeIdPath gradeIdPath;

	public ReadAllStudentGradesCommand(final StudentGradeIdPath gradeIdPath) {
		this.gradeIdPath = gradeIdPath;
	}

	public StudentGradeIdPath getGradeIdPath() {
		return this.gradeIdPath;
	}
}
