package org.vietngumn.schoolapp.event.courseWork;

import org.vietngumn.schoolapp.event.UpdateCommand;

public class UpdateCourseWorkCommand extends UpdateCommand {

	private CourseWorkIdPath workIdPath;
	private CourseWorkDTO details;

	public UpdateCourseWorkCommand(final CourseWorkIdPath workIdPath, final CourseWorkDTO details) {
		this.workIdPath = workIdPath;
		this.details = details;
	}

	public CourseWorkIdPath getWorkIdPath() {
		return this.workIdPath;
	}

	public CourseWorkDTO getDetails() {
		return details;
	}
}
