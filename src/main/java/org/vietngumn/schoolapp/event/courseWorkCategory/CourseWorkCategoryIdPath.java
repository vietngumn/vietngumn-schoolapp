package org.vietngumn.schoolapp.event.courseWorkCategory;


public class CourseWorkCategoryIdPath {

	private String courseId;
	private String categoryId;
	
	public CourseWorkCategoryIdPath(final String courseId, final String categoryId) {
		this.courseId = courseId;
		this.categoryId = categoryId;
	}

	public String getCourseId() {
		return this.courseId;
	}

	public String getCategoryId() {
		return this.categoryId;
	}

}
