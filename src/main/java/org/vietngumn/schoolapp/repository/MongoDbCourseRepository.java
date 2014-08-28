package org.vietngumn.schoolapp.repository;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.vietngumn.schoolapp.domain.Course;
/**
 * {@link CustomerRepository} implementation using the Spring Data {@link MongoOperations} API.
 *
 * @author Steve Nguyen
 */
//@Repository
//@Profile("mongodb")
public class MongoDbCourseRepository  {
	private final MongoOperations operations;
	/**
	 * Creates a new {@link MongoDbCourseRepository} using the given {@link MongoOperations}.
	 *
	 * @param operations must not be {@literal null}.
	 */
	@Autowired
	public MongoDbCourseRepository(MongoOperations operations) {
		Assert.notNull(operations);
		this.operations = operations;
	}
	
	/**
	 * @see org.vietngumn.schoolapp.repository.CourseRepository#findOne(java.lang.String)
	 */
//	@Override
	public Course findOne(String id) {
		Query query = query(where("id").is(id));
		return operations.findOne(query, Course.class);
	}

}
