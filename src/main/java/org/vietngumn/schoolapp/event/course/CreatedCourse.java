package org.vietngumn.schoolapp.event.course;

import org.vietngumn.schoolapp.event.CreatedEvent;

public class CreatedCourse extends CreatedEvent {

	private final String newCourseId;
	private final CourseDTO courseDetails;

	public CreatedCourse(final String newCourseId, final CourseDTO details) {
		this.newCourseId = newCourseId;
		this.courseDetails = details;
	}

	public CourseDTO getCourseDetails() {
		return courseDetails;
	}

	public String getNewCourseId() {
		return newCourseId;
	}
	
}
