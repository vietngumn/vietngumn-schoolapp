package org.vietngumn.schoolapp.event.studentRecord;

import org.vietngumn.schoolapp.event.DeleteCommand;

public class DeleteStudentRecordCommand extends DeleteCommand {

	private StudentRecordIdPath recordIdPath;

	public DeleteStudentRecordCommand(final StudentRecordIdPath recordIdPath) {
		this.recordIdPath = recordIdPath;
	}

	public StudentRecordIdPath getRecordIdPath() {
		return recordIdPath;
	}

}
