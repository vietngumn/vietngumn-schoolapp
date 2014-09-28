package org.vietngumn.schoolapp.event.courseWork;

import org.vietngumn.schoolapp.event.CreatedEvent;

public class CreatedCourseWork extends CreatedEvent {

	private final CourseWorkIdPath newCreatedId;
	private final CourseWorkDTO details;

	public CreatedCourseWork(final CourseWorkIdPath newCreatedId, final CourseWorkDTO details) {
		this.newCreatedId = newCreatedId;
		this.details = details;
	}

	public CourseWorkDTO getDetails() {
		return details;
	}

	public CourseWorkIdPath getNewCreatedId() {
		return newCreatedId;
	}
}
