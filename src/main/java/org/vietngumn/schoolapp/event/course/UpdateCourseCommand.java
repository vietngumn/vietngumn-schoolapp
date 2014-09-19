package org.vietngumn.schoolapp.event.course;

import org.vietngumn.schoolapp.event.UpdateCommand;

public class UpdateCourseCommand extends UpdateCommand {

	private String courseId;
	private CourseDTO courseDetails;

	public UpdateCourseCommand(String courseId, CourseDTO courseDetails) {
		this.courseId = courseId;
		this.courseDetails = courseDetails;
	}

	public String getCourseId() {
		return courseId;
	}

	public CourseDTO getCourseDetails() {
		return courseDetails;
	}
}
