package org.vietngumn.schoolapp.event.course;

import org.vietngumn.schoolapp.event.QueryCommand;

public class QueryCoursesCommand extends QueryCommand {
	private CourseQueryCriteria courseCriteria;

	public QueryCoursesCommand(CourseQueryCriteria courseCriteria) {
		this.courseCriteria = courseCriteria;
	}

	public CourseQueryCriteria getCourseQueryCriteria() {
		return courseCriteria;
	}
}
