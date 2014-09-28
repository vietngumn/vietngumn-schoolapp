package org.vietngumn.schoolapp.event.courseWork;


public class CourseWorkIdPath {

	private String courseId;
	private String categoryId;
	private String workId;
	
	public CourseWorkIdPath(final String courseId, final String categoryId, final String workId) {
		this.courseId = courseId;
		this.categoryId = categoryId;
		this.workId = workId;
	}

	public String getCourseId() {
		return this.courseId;
	}

	public String getCategoryId() {
		return this.categoryId;
	}

	public String getWorkId() {
		return this.workId;
	}

}
