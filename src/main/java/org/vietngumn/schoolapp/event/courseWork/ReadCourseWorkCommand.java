package org.vietngumn.schoolapp.event.courseWork;

import org.vietngumn.schoolapp.event.ReadCommand;

public class ReadCourseWorkCommand extends ReadCommand {
	private String categoryId;
	private String courseId;
	private String workId;

	public ReadCourseWorkCommand(String courseId, String categoryId, String workId) {
		this.courseId = courseId;
		this.categoryId = categoryId;
		this.workId = workId;
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
	
}
