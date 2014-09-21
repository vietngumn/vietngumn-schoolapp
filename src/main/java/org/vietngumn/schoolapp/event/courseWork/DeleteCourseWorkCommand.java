package org.vietngumn.schoolapp.event.courseWork;

import org.vietngumn.schoolapp.event.DeleteCommand;

public class DeleteCourseWorkCommand extends DeleteCommand {

	private final String courseId;
	private final String categoryId;
	private final String workId;

	public DeleteCourseWorkCommand(final String courseId, final String categoryId, final String workId) {
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
