package org.vietngumn.schoolapp.event.courseWork;

import org.vietngumn.schoolapp.event.ReadEvent;

public class ReadCourseWork extends ReadEvent {
	private String courseId;
	private String categoryId;
	private String workId;
	private CourseWorkDTO details;

	private ReadCourseWork(String courseId, String categoryId, String workId) {
		this.courseId = courseId;
		this.categoryId = categoryId;
		this.workId = workId;
	}

	public ReadCourseWork(String courseId, String categoryId, String workId, CourseWorkDTO details) {
		this.courseId = courseId;
		this.categoryId = categoryId;
		this.courseId = courseId;
		this.details = details;
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

	public static ReadCourseWork notFound(String courseId, String categoryId, String workId) {
		ReadCourseWork response = new ReadCourseWork(courseId, categoryId, workId);
		response.entityFound = false;
		return response;
	}
}
