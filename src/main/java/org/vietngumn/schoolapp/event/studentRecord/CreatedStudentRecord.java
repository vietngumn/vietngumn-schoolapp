package org.vietngumn.schoolapp.event.studentRecord;

import org.vietngumn.schoolapp.event.CreatedEvent;

public class CreatedStudentRecord extends CreatedEvent {

	private final StudentRecordDTO details;

	public CreatedStudentRecord(final StudentRecordDTO details) {
		this.details = details;
	}

	public StudentRecordDTO getDetails() {
		return details;
	}
}
