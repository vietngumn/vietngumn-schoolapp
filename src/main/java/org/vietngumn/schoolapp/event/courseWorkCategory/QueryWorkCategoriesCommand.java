package org.vietngumn.schoolapp.event.courseWorkCategory;

import org.vietngumn.schoolapp.event.QueryCommand;

public class QueryWorkCategoriesCommand extends QueryCommand {
	private WorkCategoryQueryCriteria criteria;

	public QueryWorkCategoriesCommand(WorkCategoryQueryCriteria criteria) {
		this.criteria = criteria;
	}

	public WorkCategoryQueryCriteria getQueryCriteria() {
		return criteria;
	}
}
