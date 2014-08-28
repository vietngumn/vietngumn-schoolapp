package org.vietngumn.schoolapp.repository;

import java.util.List;

import org.vietngumn.schoolapp.domain.CourseWork;

public interface CourseWorkRepository {

	CourseWork save(CourseWork courseWork);

	void delete(String courseWorkId);

	CourseWork findById(String courseWorkId);

	List<CourseWork> findAll();
}
