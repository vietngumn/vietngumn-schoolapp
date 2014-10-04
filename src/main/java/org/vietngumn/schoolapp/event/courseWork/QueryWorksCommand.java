package org.vietngumn.schoolapp.event.courseWork;

import org.vietngumn.schoolapp.event.QueryCommand;

public class QueryWorksCommand extends QueryCommand {
	private WorkQueryCriteria criteria;

	public QueryWorksCommand(WorkQueryCriteria criteria) {
		this.criteria = criteria;
	}

	public WorkQueryCriteria getQueryCriteria() {
		return criteria;
	}
}
