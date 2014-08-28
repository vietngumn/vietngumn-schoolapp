package org.vietngumn.schoolapp.event.course;

import org.vietngumn.schoolapp.event.CreateRequest;

public class CreateCourseRequest extends CreateRequest {
	private CourseDetails details;

	public CreateCourseRequest(CourseDetails details) {
		this.details = details;
	}

	public CourseDetails getDetails() {
		return details;
	}
	
}
