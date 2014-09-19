package org.vietngumn.schoolapp.event.courseWorkCategory;

import org.vietngumn.schoolapp.event.ReadCommand;

public class ReadCourseWorkCategoryCommand extends ReadCommand {
	private String categoryId;
	private String courseId;

	public ReadCourseWorkCategoryCommand(String courseId, String categoryId) {
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
