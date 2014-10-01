package org.vietngumn.schoolapp.event.course;

import org.vietngumn.schoolapp.event.QueryCommand;

public class QueryCoursesCommand extends QueryCommand {
	private CourseQueryCriteria criteria;

	public QueryCoursesCommand(CourseQueryCriteria criteria) {
		this.criteria = criteria;
	}

	public CourseQueryCriteria getQueryCriteria() {
		return criteria;
	}
}
