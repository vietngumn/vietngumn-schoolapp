package org.vietngumn.schoolapp.event.courseWork;

import org.vietngumn.schoolapp.event.QueryCommand;

public class WorkQueryCriteria extends QueryCommand {
	private CourseWorkIdPath workIdPath;

	public CourseWorkIdPath getWorkIdPath() {
		return workIdPath;
	}

	public void setWorkIdPath(CourseWorkIdPath workIdPath) {
		this.workIdPath = workIdPath;
	}
	
}
