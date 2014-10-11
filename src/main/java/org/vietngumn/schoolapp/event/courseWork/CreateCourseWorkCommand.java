package org.vietngumn.schoolapp.event.courseWork;

import org.vietngumn.schoolapp.event.CreateCommand;

public class CreateCourseWorkCommand extends CreateCommand {
	private CourseWorkDTO details;

	public CreateCourseWorkCommand(final CourseWorkDTO workDTO) {
		this.details = workDTO;
	}

	public CourseWorkDTO getDetails() {
		return details;
	}
	
}
