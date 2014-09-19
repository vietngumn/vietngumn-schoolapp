package org.vietngumn.schoolapp.event.course;

import org.vietngumn.schoolapp.event.ReadCommand;

public class ReadCourseCommand extends ReadCommand {
	private String courseId;

	public ReadCourseCommand(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseId() {
		return courseId;
	}
}
