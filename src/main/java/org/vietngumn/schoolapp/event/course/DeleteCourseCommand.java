package org.vietngumn.schoolapp.event.course;

import org.vietngumn.schoolapp.event.DeleteCommand;

public class DeleteCourseCommand extends DeleteCommand {

	private final String courseId;

	public DeleteCourseCommand(final String courseId) {
		this.courseId = courseId;
	}

	public String getCourseId() {
		return this.courseId;
	}
}
