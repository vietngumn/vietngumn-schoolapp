package org.vietngumn.schoolapp.event.studentRecord;

import org.vietngumn.schoolapp.event.ReadEvent;

public class ReadStudentRecord extends ReadEvent {
	private String courseId;
	private String studentId;
	private StudentRecordDTO details;

	private ReadStudentRecord(String courseId, String studentId) {
		this.courseId = courseId;
		this.studentId = studentId;
	}

	public ReadStudentRecord(String courseId, String studentId, StudentRecordDTO details) {
		this.courseId = courseId;
		this.studentId = studentId;
		this.details = details;
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

	public static ReadStudentRecord notFound(String courseId, String categoryId) {
		ReadStudentRecord response = new ReadStudentRecord(courseId, categoryId);
		response.entityFound = false;
		return response;
	}
}
