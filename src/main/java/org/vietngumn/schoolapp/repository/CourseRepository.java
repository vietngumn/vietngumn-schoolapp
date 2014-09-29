package org.vietngumn.schoolapp.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.vietngumn.schoolapp.domain.Course;

/**
* Repository interface to access {@link Course}s.
* 
* @author Steve Nguyen
*/
public interface CourseRepository extends PagingAndSortingRepository<Course, String> {

	Course findByCourseId(String id);
	
	List<Course> findBySchoolYearId(String schoolYearId);
}
