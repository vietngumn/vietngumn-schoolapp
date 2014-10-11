package org.vietngumn.schoolapp.event.studentGrade;

import org.vietngumn.schoolapp.event.QueryCommand;

public class StudentGradeQueryCriteria extends QueryCommand {
	private StudentGradeIdPath idPath;

	public StudentGradeIdPath getStudentGradeIdPath() {
		return idPath;
	}

	public void setStudentGradeIdPath(StudentGradeIdPath idPath) {
		this.idPath = idPath;
	}

}
