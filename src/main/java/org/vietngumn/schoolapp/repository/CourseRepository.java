package org.vietngumn.schoolapp.repository;

import java.math.BigInteger;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.vietngumn.schoolapp.domain.Course;

/**
* Repository interface to access {@link Course}s.
* 
* @author Steve Nguyen
*/
public interface CourseRepository extends PagingAndSortingRepository<Course, BigInteger> {

	Course findByCourseId(String id);
	
}
