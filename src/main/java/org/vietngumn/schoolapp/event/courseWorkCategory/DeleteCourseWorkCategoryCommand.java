package org.vietngumn.schoolapp.event.courseWorkCategory;

import org.vietngumn.schoolapp.event.DeleteCommand;

public class DeleteCourseWorkCategoryCommand extends DeleteCommand {

	private final String courseId;
	private final String categoryId;

	public DeleteCourseWorkCategoryCommand(final String courseId, final String categoryId) {
		this.courseId = courseId;
		this.categoryId = categoryId;
	}

	public String getCourseId() {
		return courseId;
	}

	public String getCategoryId() {
		return categoryId;
	}
	
}
