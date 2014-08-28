package org.vietngumn.schoolapp.event.courseWork;

import org.vietngumn.schoolapp.event.CreateResponse;

public class CreateCourseWorkResponse extends CreateResponse {

	private final String newCourseWorkId;
	private final CourseWorkDetails details;

	public CreateCourseWorkResponse(final String newCourseWorkId, final CourseWorkDetails details) {
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
