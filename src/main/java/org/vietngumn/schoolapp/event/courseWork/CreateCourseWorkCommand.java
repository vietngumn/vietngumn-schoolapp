package org.vietngumn.schoolapp.event.courseWork;

import org.vietngumn.schoolapp.event.CreateCommand;

public class CreateCourseWorkCommand extends CreateCommand {
	private CourseWorkDetails details;

	public CreateCourseWorkCommand(CourseWorkDetails details) {
		this.details = details;
	}

	public CourseWorkDetails getDetails() {
		return details;
	}
	
}
