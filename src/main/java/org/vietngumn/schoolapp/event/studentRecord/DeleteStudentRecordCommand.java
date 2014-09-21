package org.vietngumn.schoolapp.event.studentRecord;

import org.vietngumn.schoolapp.event.DeleteCommand;

public class DeleteStudentRecordCommand extends DeleteCommand {

	private final String courseId;
	private final String studentId;

	public DeleteStudentRecordCommand(final String courseId, final String studentId) {
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
