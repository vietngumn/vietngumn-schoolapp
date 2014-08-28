package org.vietngumn.schoolapp.service;

import org.vietngumn.schoolapp.event.course.*;

public interface CourseService {

	CreateCourseResponse createCourse(CreateCourseRequest event);

	ReadCourseResponse readCourse(ReadCourseRequest readCourseRequest);

	UpdateCourseResponse updateCourse(UpdateCourseRequest updateCourseRequest);

	DeleteCourseResponse deleteCourse(DeleteCourseRequest deleteCourseRequest);
}
