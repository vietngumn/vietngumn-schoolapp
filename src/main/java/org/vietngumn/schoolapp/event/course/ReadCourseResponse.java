package org.vietngumn.schoolapp.event.course;

import org.vietngumn.schoolapp.event.ReadResponse;

public class ReadCourseResponse extends ReadResponse {
	private String courseId;
	private CourseDetails courseDetails;

	private ReadCourseResponse(String courseId) {
		this.courseId = courseId;
	}

	public ReadCourseResponse(String courseId, CourseDetails courseDetails) {
		this.courseId = courseId;
		this.courseDetails = courseDetails;
	}

	public String getCourseId() {
		return courseId;
	}

	public CourseDetails getCourseDetails() {
		return courseDetails;
	}

	public static ReadCourseResponse notFound(String courseId) {
		ReadCourseResponse response = new ReadCourseResponse(courseId);
		response.entityFound = false;
		return response;
	}
}
