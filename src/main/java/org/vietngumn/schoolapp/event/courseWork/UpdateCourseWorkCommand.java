package org.vietngumn.schoolapp.event.courseWork;

import org.vietngumn.schoolapp.event.UpdateCommand;

public class UpdateCourseWorkCommand extends UpdateCommand {

	private String courseWorkId;
	private CourseWorkDetails orderDetails;

	public UpdateCourseWorkCommand(String courseWorkId, CourseWorkDetails orderDetails) {
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
