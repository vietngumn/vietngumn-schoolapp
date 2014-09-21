package org.vietngumn.schoolapp.event.studentRecord;

import org.vietngumn.schoolapp.event.UpdateCommand;

public class UpdateStudentRecordCommand extends UpdateCommand {

	private String courseId;
	private String studentId;
	private StudentRecordDTO details;

	public UpdateStudentRecordCommand(String courseId, String studentId, StudentRecordDTO details) {
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
}
