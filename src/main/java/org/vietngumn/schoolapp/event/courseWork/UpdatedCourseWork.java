package org.vietngumn.schoolapp.event.courseWork;

import org.vietngumn.schoolapp.event.UpdatedEvent;

public class UpdatedCourseWork extends UpdatedEvent {

	private String categoryId;
	private CourseWorkDTO details;

	public UpdatedCourseWork(String categoryId, CourseWorkDTO details) {
		this.categoryId = categoryId;
		this.details = details;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public CourseWorkDTO getDetails() {
		return details;
	}
}
