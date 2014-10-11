package org.vietngumn.schoolapp.event.courseWorkCategory;

import org.vietngumn.schoolapp.event.UpdateCommand;

public class UpdateCourseWorkCategoryCommand extends UpdateCommand {

	private CourseWorkCategoryDTO details;

	public UpdateCourseWorkCategoryCommand(CourseWorkCategoryDTO details) {
		this.details = details;
	}

	public CourseWorkCategoryDTO getDetails() {
		return details;
	}
}
