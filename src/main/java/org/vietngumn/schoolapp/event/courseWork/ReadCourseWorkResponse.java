package org.vietngumn.schoolapp.event.courseWork;

import org.vietngumn.schoolapp.event.ReadResponse;

public class ReadCourseWorkResponse extends ReadResponse {
	private String courseWorkId;
	private CourseWorkDetails orderDetails;

	private ReadCourseWorkResponse(String courseWorkId) {
		this.courseWorkId = courseWorkId;
	}

	public ReadCourseWorkResponse(String courseWorkId, CourseWorkDetails courseWorkDetails) {
		this.courseWorkId = courseWorkId;
		this.orderDetails = courseWorkDetails;
	}

	public String getKey() {
		return courseWorkId;
	}

	public CourseWorkDetails getOrderDetails() {
		return orderDetails;
	}

	public static ReadCourseWorkResponse notFound(String key) {
		ReadCourseWorkResponse response = new ReadCourseWorkResponse(key);
		response.entityFound = false;
		return response;
	}
}
