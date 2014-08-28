package org.vietngumn.schoolapp.event.course;

import org.vietngumn.schoolapp.event.UpdateResponse;

public class UpdateCourseResponse extends UpdateResponse {

	private String courseId;
	private CourseDetails courseDetails;
	
	private UpdateCourseResponse(String courseId) {
		this.courseId = courseId;
	}
	
	public UpdateCourseResponse(String courseId, CourseDetails details) {
		this.courseId = courseId;
		this.courseDetails = details;
		this.updateCompleted = true;
	}

	public String getCourseId() {
		return courseId;
	}

	public CourseDetails getCourseDetails() {
		return courseDetails;
	}

	public static UpdateResponse updateForbidden(String courseId, CourseDetails details) {
		UpdateCourseResponse response = new UpdateCourseResponse(courseId, details);
		response.entityFound = true;
		response.updateCompleted = false;
		return response;
	}

	public static UpdateCourseResponse notFound(String courseId) {
		UpdateCourseResponse response = new UpdateCourseResponse(courseId);
		response.entityFound = false;
		return response;
	}
}

