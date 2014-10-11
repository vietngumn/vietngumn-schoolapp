package org.vietngumn.schoolapp.event.courseWorkCategory;

import org.vietngumn.schoolapp.event.ReadEvent;

public class ReadCourseWorkCategory extends ReadEvent {
	private CourseWorkCategoryIdPath categoryIdPath;
	private CourseWorkCategoryDTO details;

	private ReadCourseWorkCategory(final CourseWorkCategoryIdPath categoryIdPath) {
		this.categoryIdPath = categoryIdPath;
	}

	public ReadCourseWorkCategory(CourseWorkCategoryDTO details) {
		this.details = details;
	}

	public CourseWorkCategoryIdPath getCategoryIdPath() {
		return categoryIdPath;
	}

	public CourseWorkCategoryDTO getDetails() {
		return details;
	}

	public static ReadCourseWorkCategory notFound(final CourseWorkCategoryIdPath categoryIdPath) {
		ReadCourseWorkCategory response = new ReadCourseWorkCategory(categoryIdPath);
		response.entityFound = false;
		return response;
	}
}
