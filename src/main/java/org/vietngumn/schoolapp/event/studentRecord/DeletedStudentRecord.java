package org.vietngumn.schoolapp.event.studentRecord;

import org.vietngumn.schoolapp.event.DeletedEvent;

public class DeletedStudentRecord extends DeletedEvent {

	private StudentRecordIdPath recordIdPath;
	private StudentRecordDTO details;
	
	private DeletedStudentRecord(final StudentRecordIdPath recordIdPath) {
		this.recordIdPath = recordIdPath;
	}

	public DeletedStudentRecord(final StudentRecordDTO details) {
		this.details = details;
	}

	public StudentRecordIdPath getRecordIdPath() {
		return this.recordIdPath;
	}
	
	public StudentRecordDTO getDetails() {
		return details;
	}

	@Override
	public boolean isDeletionCompleted() {
		return deletionCompleted;
	}

	public static DeletedStudentRecord deletionForbidden(final StudentRecordDTO details) {
		DeletedStudentRecord response = new DeletedStudentRecord(details);
		response.entityFound = true;
		response.deletionCompleted = false;
		return response;
	}

	public static DeletedStudentRecord notFound(final StudentRecordIdPath recordIdPath) {
		DeletedStudentRecord response = new DeletedStudentRecord(recordIdPath);
		response.entityFound = false;
		return response;
	}
}
