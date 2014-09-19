package org.vietngumn.schoolapp.event.course;

import org.vietngumn.schoolapp.event.UpdatedEvent;

public class UpdatedCourse extends UpdatedEvent {

	private String courseId;
	private CourseDTO courseDetails;
	
	private UpdatedCourse(String courseId) {
		this.courseId = courseId;
	}
	
	public UpdatedCourse(String courseId, CourseDTO details) {
		this.courseId = courseId;
		this.courseDetails = details;
		this.updateCompleted = true;
	}

	public String getCourseId() {
		return courseId;
	}

	public CourseDTO getCourseDetails() {
		return courseDetails;
	}

	public static UpdatedEvent updateForbidden(String courseId, CourseDTO details) {
		UpdatedCourse response = new UpdatedCourse(courseId, details);
		response.entityFound = true;
		response.updateCompleted = false;
		return response;
	}

	public static UpdatedCourse notFound(String courseId) {
		UpdatedCourse response = new UpdatedCourse(courseId);
		response.entityFound = false;
		return response;
	}
}

