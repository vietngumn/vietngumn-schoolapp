package org.vietngumn.schoolapp.event.courseWork;

import org.vietngumn.schoolapp.event.CreatedEvent;

public class CreatedCourseWork extends CreatedEvent {

	private final String newCreatedId;
	private final CourseWorkDTO details;

	public CreatedCourseWork(final String newCreatedId, final CourseWorkDTO details) {
		this.newCreatedId = newCreatedId;
		this.details = details;
	}

	public CourseWorkDTO getDetails() {
		return details;
	}

	public String getNewCreatedId() {
		return newCreatedId;
	}
}
