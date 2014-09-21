package org.vietngumn.schoolapp.service;

import org.vietngumn.schoolapp.event.courseWork.CreateCourseWorkCommand;
import org.vietngumn.schoolapp.event.courseWork.CreatedCourseWork;
import org.vietngumn.schoolapp.event.courseWork.DeleteCourseWorkCommand;
import org.vietngumn.schoolapp.event.courseWork.DeletedCourseWork;
import org.vietngumn.schoolapp.event.courseWork.ReadCourseWork;
import org.vietngumn.schoolapp.event.courseWork.ReadCourseWorkCommand;
import org.vietngumn.schoolapp.event.courseWork.UpdateCourseWorkCommand;
import org.vietngumn.schoolapp.event.courseWork.UpdatedCourseWork;

public interface CourseWorkService {

	CreatedCourseWork createCourseWork(CreateCourseWorkCommand createCommand);

	ReadCourseWork readCourseWork(ReadCourseWorkCommand readCommand);

	UpdatedCourseWork updateCourseWork(UpdateCourseWorkCommand updateCommand);

	DeletedCourseWork deleteCourseWork(DeleteCourseWorkCommand deleteCommand);
	
}
