package org.vietngumn.schoolapp.event.courseWork;

import org.vietngumn.schoolapp.event.DeleteCommand;

public class DeleteCourseWorkCommand extends DeleteCommand {

	private final String courseWorkId;

	public DeleteCourseWorkCommand(final String courseWorkId) {
		this.courseWorkId = courseWorkId;
	}

	public String getCourseWorkId() {
		return this.courseWorkId;
	}
}
