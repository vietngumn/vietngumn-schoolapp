package org.vietngumn.schoolapp.event.studentRecord;

import org.vietngumn.schoolapp.event.UpdatedEvent;

public class UpdatedStudentRecord extends UpdatedEvent {

	private String studentId;
	private StudentRecordDTO details;

	public UpdatedStudentRecord(String studentId, StudentRecordDTO details) {
		this.studentId = studentId;
		this.details = details;
	}

	public String getStudentId() {
		return studentId;
	}

	public StudentRecordDTO getDetails() {
		return details;
	}
}
