package org.vietngumn.schoolapp.event.courseWorkCategory;

import org.vietngumn.schoolapp.event.DeletedEvent;

public class DeletedCourseWorkCategory extends DeletedEvent {

	private String courseId;
	private String categoryId;
	private CourseWorkCategoryDTO details;
	
	private DeletedCourseWorkCategory(String courseId, String categoryId) {
		this.courseId = courseId;
		this.categoryId = categoryId;
	}

	public DeletedCourseWorkCategory(String courseId, String categoryId, CourseWorkCategoryDTO details) {
		this.courseId = courseId;
		this.categoryId = categoryId;
		this.details = details;
		this.deletionCompleted = true;
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

	@Override
	public boolean isDeletionCompleted() {
		return deletionCompleted;
	}

	public static DeletedCourseWorkCategory deletionForbidden(String courseId, String categoryId, CourseWorkCategoryDTO details) {
		DeletedCourseWorkCategory response = new DeletedCourseWorkCategory(courseId, categoryId, details);
		response.entityFound = true;
		response.deletionCompleted = false;
		return response;
	}

	public static DeletedCourseWorkCategory notFound(String courseId, String categoryId) {
		DeletedCourseWorkCategory response = new DeletedCourseWorkCategory(courseId, categoryId);
		response.entityFound = false;
		return response;
	}
}
