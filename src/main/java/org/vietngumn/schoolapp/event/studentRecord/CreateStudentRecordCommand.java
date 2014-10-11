package org.vietngumn.schoolapp.event.studentRecord;

import org.vietngumn.schoolapp.event.CreateCommand;

public class CreateStudentRecordCommand extends CreateCommand {
	private StudentRecordDTO details;

	public CreateStudentRecordCommand(final StudentRecordDTO recordDTO) {
		this.details = recordDTO;
	}

	public StudentRecordDTO getDetails() {
		return details;
	}
	
}
