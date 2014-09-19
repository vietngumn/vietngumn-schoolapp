package org.vietngumn.schoolapp.event.courseWorkCategory;

import org.vietngumn.schoolapp.event.UpdateCommand;

public class UpdateCourseWorkCategoryCommand extends UpdateCommand {

	private String categoryId;
	private String courseId;
	private CourseWorkCategoryDTO details;

	public UpdateCourseWorkCategoryCommand(String courseId, String categoryId, CourseWorkCategoryDTO details) {
		this.courseId = courseId;
		this.categoryId = categoryId;
		this.details = details;
	}

	public String getCategoryId() {
		return categoryId;
	}
	
	public String getCourseId() {
		return courseId;
	}

	public CourseWorkCategoryDTO getDetails() {
		return details;
	}
}
