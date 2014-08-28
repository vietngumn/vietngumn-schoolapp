package org.vietngumn.schoolapp.event.courseWork;

import org.vietngumn.schoolapp.event.UpdateResponse;

public class UpdateCourseWorkResponse extends UpdateResponse {

	private String courseWorkId;
	private CourseWorkDetails orderDetails;

	public UpdateCourseWorkResponse(String courseWorkId, CourseWorkDetails orderDetails) {
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
