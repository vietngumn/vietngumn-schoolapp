package org.vietngumn.schoolapp.event.courseWorkCategory;

import org.vietngumn.schoolapp.event.ReadEvent;

public class ReadCourseWorkCategory extends ReadEvent {
	private String courseId;
	private String categoryId;
	private CourseWorkCategoryDTO details;

	private ReadCourseWorkCategory(String courseId, String categoryId) {
		this.courseId = courseId;
		this.categoryId = categoryId;
	}

	public ReadCourseWorkCategory(String courseId, String categoryId, CourseWorkCategoryDTO details) {
		this.courseId = courseId;
		this.categoryId = categoryId;
		this.details = details;
	}

	public String getCourseId() {
		return courseId;
	}
	
	public String getCategoryId() {
		return categoryId;
	}

	public CourseWorkCategoryDTO getDetails() {
		return details;
	}

	public static ReadCourseWorkCategory notFound(String courseId, String categoryId) {
		ReadCourseWorkCategory response = new ReadCourseWorkCategory(courseId, categoryId);
		response.entityFound = false;
		return response;
	}
}
