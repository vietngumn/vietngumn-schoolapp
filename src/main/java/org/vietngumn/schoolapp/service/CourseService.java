package org.vietngumn.schoolapp.service;

import org.vietngumn.schoolapp.event.course.*;

public interface CourseService {

	CreatedCourse createCourse(CreateCourseCommand createCommand);

	ReadCourse readCourse(ReadCourseCommand readCommand);

	UpdatedCourse updateCourse(UpdateCourseCommand updateCommand);

	DeletedCourse deleteCourse(DeleteCourseCommand deleteCommand);
	
	QueriedCourses queryCourses(QueryCoursesCommand queryCommand);
}
