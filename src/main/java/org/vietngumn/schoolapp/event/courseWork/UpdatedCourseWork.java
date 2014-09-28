package org.vietngumn.schoolapp.event.courseWork;

import org.vietngumn.schoolapp.event.UpdatedEvent;

public class UpdatedCourseWork extends UpdatedEvent {

	private CourseWorkIdPath workIdPath;
	private CourseWorkDTO details;

	public UpdatedCourseWork(final CourseWorkIdPath workIdPath, final CourseWorkDTO details) {
		this.workIdPath = workIdPath;
		this.details = details;
	}

	public CourseWorkIdPath getWorkIdPath() {
		return this.workIdPath;
	}

	public CourseWorkDTO getDetails() {
		return details;
	}
}
