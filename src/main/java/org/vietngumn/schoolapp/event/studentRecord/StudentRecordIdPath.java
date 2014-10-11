package org.vietngumn.schoolapp.event.studentRecord;


public class StudentRecordIdPath {

	private String courseId;
	private String studentId;
	
	public StudentRecordIdPath(final String courseId, final String studentId) {
		this.courseId = courseId;
		this.studentId = studentId;
	}

	public String getCourseId() {
		return this.courseId;
	}

	public String getStudentId() {
		return this.studentId;
	}

}
