package org.vietngumn.schoolapp.event.courseWork;

import org.vietngumn.schoolapp.event.DeleteRequest;

public class DeleteCourseWorkRequest extends DeleteRequest {

	private final String courseWorkId;

	public DeleteCourseWorkRequest(final String courseWorkId) {
		this.courseWorkId = courseWorkId;
	}

	public String getCourseWorkId() {
		return this.courseWorkId;
	}
}
