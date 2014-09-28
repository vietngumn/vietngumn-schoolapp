package org.vietngumn.schoolapp.event.courseWork;

import org.vietngumn.schoolapp.event.DeleteCommand;

public class DeleteCourseWorkCommand extends DeleteCommand {

	private CourseWorkIdPath workIdPath;

	public DeleteCourseWorkCommand(final CourseWorkIdPath workIdPath) {
		this.workIdPath = workIdPath;
	}

	public CourseWorkIdPath getCourseWorkIdPath() {
		return this.workIdPath;
	}
	
}
