package org.vietngumn.schoolapp.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.vietngumn.schoolapp.domain.CourseWork;

public class CourseWorkMemoryRepository implements CourseWorkRepository {

	private Map<String, CourseWork> courseWorks;

	public CourseWorkMemoryRepository(final Map<String, CourseWork> courseWorks) {
		this.courseWorks = Collections.unmodifiableMap(courseWorks);
	}

	@Override
	public synchronized CourseWork save(CourseWork courseWork) {

		Map<String, CourseWork> modifiableCourseWorks = new HashMap<String, CourseWork>(courseWorks);
		modifiableCourseWorks.put(courseWork.getCourseWorkId(), courseWork);
		this.courseWorks = Collections.unmodifiableMap(modifiableCourseWorks);

		return courseWork;
	}

	@Override
	public synchronized void delete(String courseWorkId) {
		if (courseWorks.containsKey(courseWorkId)) {
			Map<String, CourseWork> modifiableCourseWorks = new HashMap<String, CourseWork>(courseWorks);
			modifiableCourseWorks.remove(courseWorkId);
			this.courseWorks = Collections.unmodifiableMap(modifiableCourseWorks);
		}
	}

	@Override
	public CourseWork findById(String courseWorkId) {
		return courseWorks.get(courseWorkId);
	}

	@Override
	public List<CourseWork> findAll() {
		return Collections.unmodifiableList(new ArrayList<CourseWork>(courseWorks.values()));
	}
}
