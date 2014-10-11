package org.vietngumn.schoolapp.event.courseWork;

import org.vietngumn.schoolapp.event.CreatedEvent;

public class CreatedCourseWork extends CreatedEvent {

	private final CourseWorkDTO details;

	public CreatedCourseWork(final CourseWorkDTO details) {
		this.details = details;
	}

	public CourseWorkDTO getDetails() {
		return details;
	}

}
