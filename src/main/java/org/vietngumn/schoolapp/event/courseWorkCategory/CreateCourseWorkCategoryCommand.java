package org.vietngumn.schoolapp.event.courseWorkCategory;

import org.vietngumn.schoolapp.event.CreateCommand;

public class CreateCourseWorkCategoryCommand extends CreateCommand {
	private CourseWorkCategoryDTO details;

	public CreateCourseWorkCategoryCommand(final CourseWorkCategoryDTO categoryDTO) {
		this.details = categoryDTO;
	}

	public CourseWorkCategoryDTO getDetails() {
		return details;
	}
	
}
