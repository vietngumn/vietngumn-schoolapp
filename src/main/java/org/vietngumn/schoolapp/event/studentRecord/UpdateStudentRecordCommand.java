package org.vietngumn.schoolapp.event.studentRecord;

import org.vietngumn.schoolapp.event.UpdateCommand;

public class UpdateStudentRecordCommand extends UpdateCommand {

	private StudentRecordDTO details;

	public UpdateStudentRecordCommand(StudentRecordDTO details) {
		this.details = details;
	}

	public StudentRecordDTO getDetails() {
		return details;
	}
}
