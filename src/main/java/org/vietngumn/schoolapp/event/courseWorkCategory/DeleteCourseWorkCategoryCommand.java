package org.vietngumn.schoolapp.event.courseWorkCategory;

import org.vietngumn.schoolapp.event.DeleteCommand;

public class DeleteCourseWorkCategoryCommand extends DeleteCommand {
	
	private final CourseWorkCategoryIdPath categoryIdPath;

	public DeleteCourseWorkCategoryCommand(final CourseWorkCategoryIdPath categoryIdPath) {
		this.categoryIdPath = categoryIdPath;
	}

	public CourseWorkCategoryIdPath getCategoryIdPath() {
		return this.categoryIdPath;
	}
	
}
