package org.vietngumn.schoolapp.event.courseWork;

import org.vietngumn.schoolapp.event.ReadEvent;

public class ReadCourseWork extends ReadEvent {
	private CourseWorkIdPath workIdPath;
	private CourseWorkDTO details;

	private ReadCourseWork(final CourseWorkIdPath workIdPath) {
		this.workIdPath = workIdPath;
	}

	public ReadCourseWork(final CourseWorkIdPath workIdPath, final CourseWorkDTO details) {
		this(workIdPath);
		this.details = details;
	}

	public CourseWorkIdPath getWorkIdPath() {
		return this.workIdPath;
	}
	
	public CourseWorkDTO getDetails() {
		return details;
	}

	public static ReadCourseWork notFound(final CourseWorkIdPath workIdPath) {
		ReadCourseWork response = new ReadCourseWork(workIdPath);
		response.entityFound = false;
		return response;
	}
}
