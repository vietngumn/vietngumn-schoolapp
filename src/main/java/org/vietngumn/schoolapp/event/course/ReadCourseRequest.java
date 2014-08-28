package org.vietngumn.schoolapp.event.course;

import org.vietngumn.schoolapp.event.ReadRequest;

public class ReadCourseRequest extends ReadRequest {
	private String courseId;

	public ReadCourseRequest(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseId() {
		return courseId;
	}
}
