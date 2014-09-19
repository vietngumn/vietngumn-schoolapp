package org.vietngumn.schoolapp.service;

import org.vietngumn.schoolapp.event.course.*;

public interface CourseService {

	CreatedCourse createCourse(CreateCourseCommand event);

	ReadCourse readCourse(ReadCourseCommand readCourseRequest);

	UpdatedCourse updateCourse(UpdateCourseCommand updateCourseRequest);

	DeletedCourse deleteCourse(DeleteCourseCommand deleteCourseRequest);
}
