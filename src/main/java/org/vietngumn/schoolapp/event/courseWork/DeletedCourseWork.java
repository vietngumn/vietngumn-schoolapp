package org.vietngumn.schoolapp.event.courseWork;

import org.vietngumn.schoolapp.event.DeletedEvent;

public class DeletedCourseWork extends DeletedEvent {
	private CourseWorkIdPath workIdPath;
	private CourseWorkDTO details;
	
	public DeletedCourseWork(final CourseWorkDTO details) {
		this.details = details;
	}
	
	public DeletedCourseWork(final CourseWorkIdPath workIdPath) {
		this.workIdPath = workIdPath;
	}

	public CourseWorkDTO getDetails() {
		return details;
	}

	public CourseWorkIdPath getWorkIdPath() {
		return workIdPath;
	}

	@Override
	public boolean isDeletionCompleted() {
		return deletionCompleted;
	}

	public static DeletedCourseWork deletionForbidden(final CourseWorkDTO details) {
		DeletedCourseWork response = new DeletedCourseWork(details);
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
