package org.vietngumn.schoolapp.event.course;

import org.vietngumn.schoolapp.event.UpdateRequest;

public class UpdateCourseRequest extends UpdateRequest {

	private String courseId;
	private CourseDetails courseDetails;

	public UpdateCourseRequest(String courseId, CourseDetails courseDetails) {
		this.courseId = courseId;
		this.courseDetails = courseDetails;
	}

	public String getCourseId() {
		return courseId;
	}

	public CourseDetails getCourseDetails() {
		return courseDetails;
	}
}
