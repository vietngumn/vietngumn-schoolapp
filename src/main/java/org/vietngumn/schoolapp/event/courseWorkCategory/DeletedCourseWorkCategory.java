package org.vietngumn.schoolapp.event.courseWorkCategory;

import org.vietngumn.schoolapp.event.DeletedEvent;

public class DeletedCourseWorkCategory extends DeletedEvent {
	private CourseWorkCategoryIdPath categoryIdPath;
	private CourseWorkCategoryDTO details;
	
	public DeletedCourseWorkCategory(CourseWorkCategoryDTO details) {
		this.details = details;
	}

	public DeletedCourseWorkCategory(CourseWorkCategoryIdPath categoryIdPath) {
		this.categoryIdPath = categoryIdPath;
	}
	
	public CourseWorkCategoryDTO getDetails() {
		return details;
	}

	public CourseWorkCategoryIdPath getCategoryIdPath() {
		return categoryIdPath;
	}

	@Override
	public boolean isDeletionCompleted() {
		return deletionCompleted;
	}

	public static DeletedCourseWorkCategory deletionForbidden(CourseWorkCategoryDTO details) {
		DeletedCourseWorkCategory response = new DeletedCourseWorkCategory(details);
		response.entityFound = true;
		response.deletionCompleted = false;
		return response;
	}

	public static DeletedCourseWorkCategory notFound(CourseWorkCategoryIdPath categoryIdPath) {
		DeletedCourseWorkCategory response = new DeletedCourseWorkCategory(categoryIdPath);
		response.entityFound = false;
		return response;
	}
}
