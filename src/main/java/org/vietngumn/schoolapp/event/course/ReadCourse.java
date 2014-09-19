package org.vietngumn.schoolapp.event.course;

import org.vietngumn.schoolapp.event.ReadEvent;

public class ReadCourse extends ReadEvent {
	private String courseId;
	private CourseDTO courseDetails;

	private ReadCourse(String courseId) {
		this.courseId = courseId;
	}

	public ReadCourse(String courseId, CourseDTO courseDetails) {
		this.courseId = courseId;
		this.courseDetails = courseDetails;
	}

	public String getCourseId() {
		return courseId;
	}

	public CourseDTO getCourseDetails() {
		return courseDetails;
	}

	public static ReadCourse notFound(String courseId) {
		ReadCourse response = new ReadCourse(courseId);
		response.entityFound = false;
		return response;
	}
}
