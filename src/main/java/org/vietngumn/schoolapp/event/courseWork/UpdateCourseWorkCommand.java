package org.vietngumn.schoolapp.event.courseWork;

import org.vietngumn.schoolapp.event.UpdateCommand;

public class UpdateCourseWorkCommand extends UpdateCommand {

	private String courseId;
	private String categoryId;
	private String workId;
	private CourseWorkDTO details;

	public UpdateCourseWorkCommand(String courseId, String categoryId, String workId, CourseWorkDTO details) {
		this.courseId = courseId;
		this.categoryId = categoryId;
		this.workId = workId;
		this.details = details;
	}

	public String getCategoryId() {
		return categoryId;
	}
	
	public String getCourseId() {
		return courseId;
	}

	public String getWorkId() {
		return workId;
	}

	public CourseWorkDTO getDetails() {
		return details;
	}
}
