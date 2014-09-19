package org.vietngumn.schoolapp.event.courseWork;

import org.vietngumn.schoolapp.event.ReadCommand;

public class ReadCourseWorkCommand extends ReadCommand {
	private String courseWorkId;

	public ReadCourseWorkCommand(String courseWorkId) {
		this.courseWorkId = courseWorkId;
	}

	public String getCourseWorkId() {
		return courseWorkId;
	}
}
