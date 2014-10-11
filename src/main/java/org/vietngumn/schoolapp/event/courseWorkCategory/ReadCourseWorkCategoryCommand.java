package org.vietngumn.schoolapp.event.courseWorkCategory;

import org.vietngumn.schoolapp.event.ReadCommand;

public class ReadCourseWorkCategoryCommand extends ReadCommand {
	private final CourseWorkCategoryIdPath categoryIdPath;

	public ReadCourseWorkCategoryCommand(final CourseWorkCategoryIdPath categoryIdPath) {
		this.categoryIdPath = categoryIdPath;
	}

	public CourseWorkCategoryIdPath getCategoryIdPath() {
		return categoryIdPath;
	}

}
