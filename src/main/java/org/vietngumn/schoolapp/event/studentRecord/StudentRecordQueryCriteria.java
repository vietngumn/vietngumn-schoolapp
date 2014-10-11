package org.vietngumn.schoolapp.event.studentRecord;

import org.vietngumn.schoolapp.event.QueryCommand;

public class StudentRecordQueryCriteria extends QueryCommand {
	private StudentRecordIdPath recordIdPath;

	public StudentRecordIdPath getRecordIdPath() {
		return recordIdPath;
	}

	public void setRecordIdPath(StudentRecordIdPath recordIdPath) {
		this.recordIdPath = recordIdPath;
	}

}
