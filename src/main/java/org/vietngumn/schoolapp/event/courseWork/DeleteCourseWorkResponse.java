package org.vietngumn.schoolapp.event.courseWork;

import org.vietngumn.schoolapp.event.DeleteResponse;

public class DeleteCourseWorkResponse extends DeleteResponse {

	private String courseWorkId;
	private CourseWorkDetails details;
	private DeleteCourseWorkResponse(String courseWorkId) {
		this.courseWorkId = courseWorkId;
	}

	public DeleteCourseWorkResponse(String courseWorkId, CourseWorkDetails details) {
		this.courseWorkId = courseWorkId;
		this.details = details;
		this.deletionCompleted = true;
	}

	public String getcourseWorkId() {
		return courseWorkId;
	}

	public CourseWorkDetails getDetails() {
		return details;
	}

	public boolean isDeletionCompleted() {
		return deletionCompleted;
	}

	public static DeleteCourseWorkResponse deletionForbidden(String courseWorkId, CourseWorkDetails details) {
		DeleteCourseWorkResponse response = new DeleteCourseWorkResponse(courseWorkId, details);
		response.entityFound = true;
		response.deletionCompleted = false;
		return response;
	}

	public static DeleteCourseWorkResponse notFound(String courseWorkId) {
		DeleteCourseWorkResponse response = new DeleteCourseWorkResponse(courseWorkId);
		response.entityFound = false;
		return response;
	}
}
