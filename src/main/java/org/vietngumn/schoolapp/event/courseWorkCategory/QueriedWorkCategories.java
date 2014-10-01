package org.vietngumn.schoolapp.event.courseWorkCategory;

import java.util.Collections;
import java.util.List;

import org.vietngumn.schoolapp.event.QueriedEvent;

public class QueriedWorkCategories extends QueriedEvent {
	private final List<CourseWorkCategoryDTO> workCategories;

	public QueriedWorkCategories(List<CourseWorkCategoryDTO> workCategories) {
		this.workCategories = Collections.unmodifiableList(workCategories);
	}

	public List<CourseWorkCategoryDTO> getWorkCategories() {
		return this.workCategories;
	}
	
}
