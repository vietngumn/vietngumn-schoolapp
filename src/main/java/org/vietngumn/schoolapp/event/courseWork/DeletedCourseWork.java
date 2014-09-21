package org.vietngumn.schoolapp.event.courseWork;

import org.vietngumn.schoolapp.event.DeletedEvent;

public class DeletedCourseWork extends DeletedEvent {

	private String courseId;
	private String categoryId;
	private String workId;
	private CourseWorkDTO details;
	
	private DeletedCourseWork(String courseId, String categoryId, String workId) {
		this.courseId = courseId;
		this.categoryId = categoryId;
		this.workId = workId;
	}

	public DeletedCourseWork(String courseId, String categoryId, String workId, CourseWorkDTO details) {
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

	public String getWorkId() {
		return workId;
	}

	public CourseWorkDTO getDetails() {
		return details;
	}

	@Override
	public boolean isDeletionCompleted() {
		return deletionCompleted;
	}

	public static DeletedCourseWork deletionForbidden(String courseId, String categoryId, String workId, CourseWorkDTO details) {
		DeletedCourseWork response = new DeletedCourseWork(courseId, categoryId, workId, details);
		response.entityFound = true;
		response.deletionCompleted = false;
		return response;
	}

	public static DeletedCourseWork notFound(String courseId, String categoryId, String workId) {
		DeletedCourseWork response = new DeletedCourseWork(courseId, categoryId, workId);
		response.entityFound = false;
		return response;
	}
}
