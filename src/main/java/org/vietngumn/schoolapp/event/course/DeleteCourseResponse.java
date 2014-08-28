package org.vietngumn.schoolapp.event.course;

import org.vietngumn.schoolapp.event.DeleteResponse;

public class DeleteCourseResponse extends DeleteResponse {

	private String courseId;
	private CourseDetails details;
	
	private DeleteCourseResponse(String courseId) {
		this.courseId = courseId;
	}

	public DeleteCourseResponse(String courseId, CourseDetails details) {
		this.courseId = courseId;
		this.details = details;
		this.deletionCompleted = true;
	}

	public String getCourseId() {
		return courseId;
	}

	public CourseDetails getCourseDetails() {
		return details;
	}

	public static DeleteResponse deletionForbidden(String courseId, CourseDetails details) {
		DeleteCourseResponse response = new DeleteCourseResponse(courseId, details);
		response.entityFound = true;
		response.deletionCompleted = false;
		return response;
	}

	public static DeleteCourseResponse notFound(String courseId) {
		DeleteCourseResponse response = new DeleteCourseResponse(courseId);
		response.entityFound = false;
		return response;
	}
}
