package org.vietngumn.schoolapp.event.course;

import org.vietngumn.schoolapp.event.DeletedEvent;

public class DeletedCourse extends DeletedEvent {

	private String courseId;
	private CourseDTO details;
	
	private DeletedCourse(String courseId) {
		this.courseId = courseId;
	}

	public DeletedCourse(String courseId, CourseDTO details) {
		this.courseId = courseId;
		this.details = details;
		this.deletionCompleted = true;
	}

	public String getCourseId() {
		return courseId;
	}

	public CourseDTO getCourseDetails() {
		return details;
	}

	public static DeletedEvent deletionForbidden(String courseId, CourseDTO details) {
		DeletedCourse response = new DeletedCourse(courseId, details);
		response.entityFound = true;
		response.deletionCompleted = false;
		return response;
	}

	public static DeletedCourse notFound(String courseId) {
		DeletedCourse response = new DeletedCourse(courseId);
		response.entityFound = false;
		return response;
	}
}
