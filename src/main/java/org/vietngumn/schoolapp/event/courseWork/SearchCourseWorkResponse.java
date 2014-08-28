package org.vietngumn.schoolapp.event.courseWork;

import org.vietngumn.schoolapp.event.ReadResponse;

public class SearchCourseWorkResponse extends ReadResponse {
	private String courseWorkId;
	private CourseWorkDetails orderDetails;

	private SearchCourseWorkResponse(String courseWorkId) {
		this.courseWorkId = courseWorkId;
	}

	public SearchCourseWorkResponse(String courseWorkId, CourseWorkDetails courseWorkDetails) {
		this.courseWorkId = courseWorkId;
		this.orderDetails = courseWorkDetails;
	}

	public String getKey() {
		return courseWorkId;
	}

	public CourseWorkDetails getOrderDetails() {
		return orderDetails;
	}

	public static SearchCourseWorkResponse notFound(String key) {
		SearchCourseWorkResponse response = new SearchCourseWorkResponse(key);
		response.entityFound = false;
		return response;
	}
}
