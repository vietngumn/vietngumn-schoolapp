package org.vietngumn.schoolapp.event.studentRecord;

import org.vietngumn.schoolapp.event.CreatedEvent;

public class CreatedStudentRecord extends CreatedEvent {

	private final String newCreatedId;
	private final StudentRecordDTO details;

	public CreatedStudentRecord(final String newCreatedId, final StudentRecordDTO details) {
		this.newCreatedId = newCreatedId;
		this.details = details;
	}

	public StudentRecordDTO getDetails() {
		return details;
	}

	public String getNewCreatedId() {
		return newCreatedId;
	}
}
