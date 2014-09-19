package org.vietngumn.schoolapp.event.courseWorkCategory;

import org.vietngumn.schoolapp.event.CreatedEvent;

public class CreatedCourseWorkCategory extends CreatedEvent {

	private final String newCategoryId;
	private final CourseWorkCategoryDTO details;

	public CreatedCourseWorkCategory(final String newCategoryId, final CourseWorkCategoryDTO details) {
		this.newCategoryId = newCategoryId;
		this.details = details;
	}

	public CourseWorkCategoryDTO getDetails() {
		return details;
	}

	public String getNewCategoryId() {
		return newCategoryId;
	}
}
