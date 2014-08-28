package org.vietngumn.schoolapp.event.course;

import org.vietngumn.schoolapp.event.CreateResponse;

public class CreateCourseResponse extends CreateResponse {

	private final String newCourseId;
	private final CourseDetails courseDetails;

	public CreateCourseResponse(final String newCourseId, final CourseDetails details) {
		this.newCourseId = newCourseId;
		this.courseDetails = details;
	}

	public CourseDetails getCourseDetails() {
		return courseDetails;
	}

	public String getNewCourseId() {
		return newCourseId;
	}
	
}
