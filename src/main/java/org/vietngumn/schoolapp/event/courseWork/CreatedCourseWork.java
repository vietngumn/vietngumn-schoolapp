package org.vietngumn.schoolapp.event.courseWork;

import org.vietngumn.schoolapp.event.CreatedEvent;

public class CreatedCourseWork extends CreatedEvent {

	private final String newCourseWorkId;
	private final CourseWorkDetails details;

	public CreatedCourseWork(final String newCourseWorkId, final CourseWorkDetails details) {
		this.newCourseWorkId = newCourseWorkId;
		this.details = details;
	}

	public CourseWorkDetails getDetails() {
		return details;
	}

	public String getNewCourseWorkId() {
		return newCourseWorkId;
	}
}
