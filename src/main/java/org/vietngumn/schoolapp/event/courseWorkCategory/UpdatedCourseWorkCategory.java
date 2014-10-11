package org.vietngumn.schoolapp.event.courseWorkCategory;

import org.vietngumn.schoolapp.event.UpdatedEvent;

public class UpdatedCourseWorkCategory extends UpdatedEvent {

	private CourseWorkCategoryDTO details;

	public UpdatedCourseWorkCategory(CourseWorkCategoryDTO details) {
		this.details = details;
	}

	public CourseWorkCategoryDTO getDetails() {
		return details;
	}
}
