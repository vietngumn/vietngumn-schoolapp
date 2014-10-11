package org.vietngumn.schoolapp.event.courseWork;

import org.vietngumn.schoolapp.event.UpdateCommand;

public class UpdateCourseWorkCommand extends UpdateCommand {

	private CourseWorkDTO details;

	public UpdateCourseWorkCommand(final CourseWorkDTO details) {
		this.details = details;
	}

	public CourseWorkDTO getDetails() {
		return details;
	}
}
