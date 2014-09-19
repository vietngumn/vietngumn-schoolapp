package org.vietngumn.schoolapp.service;

import org.vietngumn.schoolapp.event.courseWorkCategory.CreateCourseWorkCategoryCommand;
import org.vietngumn.schoolapp.event.courseWorkCategory.CreatedCourseWorkCategory;
import org.vietngumn.schoolapp.event.courseWorkCategory.DeleteCourseWorkCategoryCommand;
import org.vietngumn.schoolapp.event.courseWorkCategory.DeletedCourseWorkCategory;
import org.vietngumn.schoolapp.event.courseWorkCategory.ReadCourseWorkCategory;
import org.vietngumn.schoolapp.event.courseWorkCategory.ReadCourseWorkCategoryCommand;
import org.vietngumn.schoolapp.event.courseWorkCategory.UpdateCourseWorkCategoryCommand;
import org.vietngumn.schoolapp.event.courseWorkCategory.UpdatedCourseWorkCategory;

public interface CourseWorkService {

	CreatedCourseWorkCategory createCourseWorkCategory(CreateCourseWorkCategoryCommand createCommand);

	ReadCourseWorkCategory readCourseWorkCategory(ReadCourseWorkCategoryCommand readCommand);

	UpdatedCourseWorkCategory updateCourseWorkCategory(UpdateCourseWorkCategoryCommand updateCommand);

	DeletedCourseWorkCategory deleteCourseWorkCategory(DeleteCourseWorkCategoryCommand deleteCommand);
//	
//	CreateCourseWorkResponse createCourseWork(CreateCourseWorkRequest event);
//
//	ReadCourseWorkResponse readCourseWork(ReadCourseWorkRequest readCourseRequest);
//
//	UpdateCourseWorkResponse updateCourseWork(UpdateCourseWorkRequest updateCourseRequest);
//
//	DeleteCourseWorkResponse deleteCourseWork(DeleteCourseWorkRequest deleteCourseRequest);
}
