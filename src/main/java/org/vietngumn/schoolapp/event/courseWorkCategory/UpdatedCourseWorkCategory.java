package org.vietngumn.schoolapp.event.courseWorkCategory;

import org.vietngumn.schoolapp.event.UpdatedEvent;

public class UpdatedCourseWorkCategory extends UpdatedEvent {

	private String categoryId;
	private CourseWorkCategoryDTO details;

	public UpdatedCourseWorkCategory(String categoryId, CourseWorkCategoryDTO details) {
		this.categoryId = categoryId;
		this.details = details;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public CourseWorkCategoryDTO getDetails() {
		return details;
	}
}
