package org.vietngumn.schoolapp.event.courseWork;

import java.util.Collections;
import java.util.List;

import org.vietngumn.schoolapp.event.QueriedEvent;

public class QueriedCourseWorks extends QueriedEvent {
	private final List<CourseWorkDTO> works;

	public QueriedCourseWorks(List<CourseWorkDTO> works) {
		this.works = Collections.unmodifiableList(works);
	}

	public List<CourseWorkDTO> getCourseWorks() {
		return this.works;
	}
	
}
