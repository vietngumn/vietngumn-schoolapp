package org.vietngumn.schoolapp.event.studentRecord;

import org.vietngumn.schoolapp.event.ReadCommand;

public class ReadStudentRecordCommand extends ReadCommand {
	private String courseId;
	private String studentId;

	public ReadStudentRecordCommand(String courseId, String studentId) {
		this.courseId = courseId;
		this.studentId = studentId;
	}

	public String getCourseId() {
		return courseId;
	}

	public String getStudentId() {
		return studentId;
	}
}
