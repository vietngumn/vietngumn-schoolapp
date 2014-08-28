package org.vietngumn.schoolapp.event.course;

import org.vietngumn.schoolapp.event.DeleteRequest;

public class DeleteCourseRequest extends DeleteRequest {

	private final String courseId;

	public DeleteCourseRequest(final String courseId) {
		this.courseId = courseId;
	}

	public String getCourseId() {
		return this.courseId;
	}
}
