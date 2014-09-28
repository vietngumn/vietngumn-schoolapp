package org.vietngumn.schoolapp.event.courseWork;

import org.vietngumn.schoolapp.event.DeletedEvent;

public class DeletedCourseWork extends DeletedEvent {

	private CourseWorkIdPath workIdPath;
	private CourseWorkDTO details;
	
	private DeletedCourseWork(final CourseWorkIdPath workIdPath) {
		this.workIdPath = workIdPath;
	}

	public DeletedCourseWork(final CourseWorkIdPath workIdPath, final CourseWorkDTO details) {
		this(workIdPath);
		this.details = details;
		this.deletionCompleted = true;
	}

	public CourseWorkIdPath getWorkIdPath() {
		return this.workIdPath;
	}
	
	public CourseWorkDTO getDetails() {
		return details;
	}

	@Override
	public boolean isDeletionCompleted() {
		return deletionCompleted;
	}

	public static DeletedCourseWork deletionForbidden(final CourseWorkIdPath workIdPath, final CourseWorkDTO details) {
		DeletedCourseWork response = new DeletedCourseWork(workIdPath, details);
		response.entityFound = true;
		response.deletionCompleted = false;
		return response;
	}

	public static DeletedCourseWork notFound(final CourseWorkIdPath workIdPath) {
		DeletedCourseWork response = new DeletedCourseWork(workIdPath);
		response.entityFound = false;
		return response;
	}
}
