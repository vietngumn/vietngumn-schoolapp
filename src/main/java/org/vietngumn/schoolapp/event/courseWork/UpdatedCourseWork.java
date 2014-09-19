package org.vietngumn.schoolapp.event.courseWork;

import org.vietngumn.schoolapp.event.UpdatedEvent;

public class UpdatedCourseWork extends UpdatedEvent {

	private String courseWorkId;
	private CourseWorkDetails orderDetails;

	public UpdatedCourseWork(String courseWorkId, CourseWorkDetails orderDetails) {
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
