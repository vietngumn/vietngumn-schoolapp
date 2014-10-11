package org.vietngumn.schoolapp.event.courseWork;

import org.vietngumn.schoolapp.event.UpdatedEvent;

public class UpdatedCourseWork extends UpdatedEvent {

	private CourseWorkDTO details;

	public UpdatedCourseWork(final CourseWorkDTO details) {
		this.details = details;
	}

	public CourseWorkDTO getDetails() {
		return details;
	}
}
