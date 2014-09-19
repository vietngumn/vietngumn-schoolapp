package org.vietngumn.schoolapp.event.courseWork;

import org.vietngumn.schoolapp.event.DeletedEvent;

public class DeletedCourseWork extends DeletedEvent {

	private String courseWorkId;
	private CourseWorkDetails details;
	private DeletedCourseWork(String courseWorkId) {
		this.courseWorkId = courseWorkId;
	}

	public DeletedCourseWork(String courseWorkId, CourseWorkDetails details) {
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

	public static DeletedCourseWork deletionForbidden(String courseWorkId, CourseWorkDetails details) {
		DeletedCourseWork response = new DeletedCourseWork(courseWorkId, details);
		response.entityFound = true;
		response.deletionCompleted = false;
		return response;
	}

	public static DeletedCourseWork notFound(String courseWorkId) {
		DeletedCourseWork response = new DeletedCourseWork(courseWorkId);
		response.entityFound = false;
		return response;
	}
}
