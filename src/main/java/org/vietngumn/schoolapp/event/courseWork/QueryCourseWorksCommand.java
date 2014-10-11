package org.vietngumn.schoolapp.event.courseWork;

import org.vietngumn.schoolapp.event.QueryCommand;

public class QueryCourseWorksCommand extends QueryCommand {
	private WorkQueryCriteria criteria;

	public QueryCourseWorksCommand(WorkQueryCriteria criteria) {
		this.criteria = criteria;
	}

	public WorkQueryCriteria getQueryCriteria() {
		return criteria;
	}
}
