package org.vietngumn.schoolapp.event.studentRecord;

import org.vietngumn.schoolapp.event.QueryCommand;

public class QueryStudentRecordsCommand extends QueryCommand {
	private StudentRecordQueryCriteria criteria;

	public QueryStudentRecordsCommand(StudentRecordQueryCriteria criteria) {
		this.criteria = criteria;
	}

	public StudentRecordQueryCriteria getQueryCriteria() {
		return criteria;
	}
}
