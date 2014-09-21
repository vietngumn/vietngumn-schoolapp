package org.vietngumn.schoolapp.event.studentRecord;

import org.vietngumn.schoolapp.event.DeletedEvent;

public class DeletedStudentRecord extends DeletedEvent {

	private String courseId;
	private String studentId;
	private StudentRecordDTO details;
	
	private DeletedStudentRecord(String courseId, String studentId) {
		this.courseId = courseId;
		this.studentId = studentId;
	}

	public DeletedStudentRecord(String courseId, String studentId, StudentRecordDTO details) {
		this.courseId = courseId;
		this.studentId = studentId;
		this.details = details;
		this.deletionCompleted = true;
	}

	public String getCourseId() {
		return courseId;
	}
	
	public String getStudentId() {
		return studentId;
	}

	public StudentRecordDTO getDetails() {
		return details;
	}

	@Override
	public boolean isDeletionCompleted() {
		return deletionCompleted;
	}

	public static DeletedStudentRecord deletionForbidden(String courseId, String studentId, StudentRecordDTO details) {
		DeletedStudentRecord response = new DeletedStudentRecord(courseId, studentId, details);
		response.entityFound = true;
		response.deletionCompleted = false;
		return response;
	}

	public static DeletedStudentRecord notFound(String courseId, String studentId) {
		DeletedStudentRecord response = new DeletedStudentRecord(courseId, studentId);
		response.entityFound = false;
		return response;
	}
}
