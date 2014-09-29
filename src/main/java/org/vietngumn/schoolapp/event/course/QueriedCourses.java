package org.vietngumn.schoolapp.event.course;

import java.util.Collections;
import java.util.List;

import org.vietngumn.schoolapp.event.QueriedEvent;

public class QueriedCourses extends QueriedEvent {
	private final List<CourseDTO> courses;

	public QueriedCourses(List<CourseDTO> courses) {
		this.courses = Collections.unmodifiableList(courses);
	}

	public List<CourseDTO> getCourses() {
		return this.courses;
	}
	
}
