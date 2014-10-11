package org.vietngumn.schoolapp.event.studentRecord;

import org.vietngumn.schoolapp.event.ReadEvent;

public class ReadStudentRecord extends ReadEvent {
	private StudentRecordIdPath recordIdPath;
	private StudentRecordDTO details;

	private ReadStudentRecord(final StudentRecordIdPath recordIdPath) {
		this.recordIdPath = recordIdPath;
	}

	public ReadStudentRecord(StudentRecordDTO details) {
		this.details = details;
	}

	public StudentRecordIdPath getRecordIdPath() {
		return this.recordIdPath;
	}
	
	public StudentRecordDTO getDetails() {
		return details;
	}

	public static ReadStudentRecord notFound(final StudentRecordIdPath recordIdPath) {
		ReadStudentRecord response = new ReadStudentRecord(recordIdPath);
		response.entityFound = false;
		return response;
	}
}
