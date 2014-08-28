package org.vietngumn.schoolapp.event.courseWork;

import java.util.List;

import org.vietngumn.schoolapp.event.SearchRequest;

public class SearchCourseWorkRequest extends SearchRequest {
	private String courseWorkId;
	private String courseId;
	private List<String> categories; //all
	

	public SearchCourseWorkRequest(String courseWorkId) {
		this.courseWorkId = courseWorkId;
	}

	public String getCourseWorkId() {
		return courseWorkId;
	}
}
