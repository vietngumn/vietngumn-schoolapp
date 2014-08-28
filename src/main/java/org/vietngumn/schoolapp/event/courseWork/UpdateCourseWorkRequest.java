package org.vietngumn.schoolapp.event.courseWork;

import org.vietngumn.schoolapp.event.UpdateRequest;

public class UpdateCourseWorkRequest extends UpdateRequest {

	private String courseWorkId;
	private CourseWorkDetails orderDetails;

	public UpdateCourseWorkRequest(String courseWorkId, CourseWorkDetails orderDetails) {
		this.courseWorkId = courseWorkId;
		this.orderDetails = orderDetails;
	}

	public String getCourseWorkId() {
		return courseWorkId;
	}

	public CourseWorkDetails getOrderDetails() {
		return orderDetails;
	}
}
