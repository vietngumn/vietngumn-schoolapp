package org.vietngumn.schoolapp.event.studentRecord;

import org.vietngumn.schoolapp.event.ReadCommand;

public class ReadStudentRecordCommand extends ReadCommand {
	private StudentRecordIdPath recordIdPath;

	public ReadStudentRecordCommand(final StudentRecordIdPath recordIdPath) {
		this.recordIdPath = recordIdPath;
	}

	public StudentRecordIdPath getRecordIdPath() {
		return this.recordIdPath;
	}
	
}
