package org.vietngumn.schoolapp.event.courseWork;

import org.vietngumn.schoolapp.event.CreateRequest;

public class CreateCourseWorkRequest extends CreateRequest {
	private CourseWorkDetails details;

	public CreateCourseWorkRequest(CourseWorkDetails details) {
		this.details = details;
	}

	public CourseWorkDetails getDetails() {
		return details;
	}
	
}
