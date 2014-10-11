package org.vietngumn.schoolapp.event.courseWorkCategory;

import org.vietngumn.schoolapp.event.CreatedEvent;

public class CreatedCourseWorkCategory extends CreatedEvent {

	private final CourseWorkCategoryDTO details;

	public CreatedCourseWorkCategory(final CourseWorkCategoryDTO details) {
		this.details = details;
	}

	public CourseWorkCategoryDTO getDetails() {
		return details;
	}
}
