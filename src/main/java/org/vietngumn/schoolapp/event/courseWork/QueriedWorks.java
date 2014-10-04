package org.vietngumn.schoolapp.event.courseWork;

import java.util.Collections;
import java.util.List;

import org.vietngumn.schoolapp.event.QueriedEvent;

public class QueriedWorks extends QueriedEvent {
	private final List<CourseWorkDTO> works;

	public QueriedWorks(List<CourseWorkDTO> works) {
		this.works = Collections.unmodifiableList(works);
	}

	public List<CourseWorkDTO> getWorks() {
		return this.works;
	}
	
}
