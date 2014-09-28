package org.vietngumn.schoolapp.event.courseWork;

import org.vietngumn.schoolapp.event.CreateCommand;

public class CreateCourseWorkCommand extends CreateCommand {
	private CourseWorkIdPath workIdPath;
	private CourseWorkDTO details;

	public CreateCourseWorkCommand(final CourseWorkIdPath workIdPath, final CourseWorkDTO workDTO) {
		this.workIdPath = workIdPath;
		this.details = workDTO;
	}

	public CourseWorkIdPath getWorkIdPath() {
		return this.workIdPath;
	}

	public CourseWorkDTO getDetails() {
		return details;
	}
	
}
