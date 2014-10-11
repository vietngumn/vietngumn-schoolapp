package org.vietngumn.schoolapp.event.courseWorkCategory;

import org.vietngumn.schoolapp.event.QueryCommand;

public class WorkCategoryQueryCriteria extends QueryCommand {
	private CourseWorkCategoryIdPath categoryIdPath;

	public void setCategoryIdPath(CourseWorkCategoryIdPath categoryIdPath) {
		this.categoryIdPath = categoryIdPath;
	}

	public CourseWorkCategoryIdPath getCategoryIdPath() {
		return categoryIdPath;
	}

}
