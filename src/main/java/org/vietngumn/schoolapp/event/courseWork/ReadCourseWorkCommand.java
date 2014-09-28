package org.vietngumn.schoolapp.event.courseWork;

import org.vietngumn.schoolapp.event.ReadCommand;

public class ReadCourseWorkCommand extends ReadCommand {
	private CourseWorkIdPath workIdPath;

	public ReadCourseWorkCommand(final CourseWorkIdPath workIdPath) {
		this.workIdPath = workIdPath;
	}

	public CourseWorkIdPath getWorkIdPath() {
		return this.workIdPath;
	}
	
}
