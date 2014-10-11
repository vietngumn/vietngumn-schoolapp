package org.vietngumn.schoolapp.event.studentRecord;

import org.vietngumn.schoolapp.event.UpdatedEvent;

public class UpdatedStudentRecord extends UpdatedEvent {

	private StudentRecordDTO details;

	public UpdatedStudentRecord(StudentRecordDTO details) {
		this.details = details;
	}

	public StudentRecordDTO getDetails() {
		return details;
	}
}
