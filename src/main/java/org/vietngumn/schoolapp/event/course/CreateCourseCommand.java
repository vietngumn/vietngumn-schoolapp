package org.vietngumn.schoolapp.event.course;

import org.vietngumn.schoolapp.event.CreateCommand;

public class CreateCourseCommand extends CreateCommand {
	private CourseDTO details;

	public CreateCourseCommand(CourseDTO details) {
		this.details = details;
	}

	public CourseDTO getDetails() {
		return details;
	}
	
}
