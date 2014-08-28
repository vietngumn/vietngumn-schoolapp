package org.vietngumn.schoolapp.event.courseWork;

import org.vietngumn.schoolapp.event.ReadRequest;

public class ReadCourseWorkRequest extends ReadRequest {
	private String courseWorkId;

	public ReadCourseWorkRequest(String courseWorkId) {
		this.courseWorkId = courseWorkId;
	}

	public String getCourseWorkId() {
		return courseWorkId;
	}
}
