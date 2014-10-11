package org.vietngumn.schoolapp.event.studentGrade;

import org.vietngumn.schoolapp.event.QueryCommand;

public class QueryStudentGradesCommand extends QueryCommand {
	private StudentGradeQueryCriteria criteria;

	public QueryStudentGradesCommand(StudentGradeQueryCriteria criteria) {
		this.criteria = criteria;
	}

	public StudentGradeQueryCriteria getQueryCriteria() {
		return criteria;
	}
}
