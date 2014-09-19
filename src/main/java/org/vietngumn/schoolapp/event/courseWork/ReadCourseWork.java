package org.vietngumn.schoolapp.event.courseWork;

import org.vietngumn.schoolapp.event.ReadEvent;

public class ReadCourseWork extends ReadEvent {
	private String courseWorkId;
	private CourseWorkDetails orderDetails;

	private ReadCourseWork(String courseWorkId) {
		this.courseWorkId = courseWorkId;
	}

	public ReadCourseWork(String courseWorkId, CourseWorkDetails courseWorkDetails) {
		this.courseWorkId = courseWorkId;
		this.orderDetails = courseWorkDetails;
	}

	public String getKey() {
		return courseWorkId;
	}

	public CourseWorkDetails getOrderDetails() {
		return orderDetails;
	}

	public static ReadCourseWork notFound(String key) {
		ReadCourseWork response = new ReadCourseWork(key);
		response.entityFound = false;
		return response;
	}
}
