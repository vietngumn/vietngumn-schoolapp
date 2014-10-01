package org.vietngumn.schoolapp.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vietngumn.schoolapp.domain.Course;
import org.vietngumn.schoolapp.domain.CourseWorkCategory;
import org.vietngumn.schoolapp.event.courseWorkCategory.CourseWorkCategoryDTO;
import org.vietngumn.schoolapp.event.courseWorkCategory.CreateCourseWorkCategoryCommand;
import org.vietngumn.schoolapp.event.courseWorkCategory.CreatedCourseWorkCategory;
import org.vietngumn.schoolapp.event.courseWorkCategory.DeleteCourseWorkCategoryCommand;
import org.vietngumn.schoolapp.event.courseWorkCategory.DeletedCourseWorkCategory;
import org.vietngumn.schoolapp.event.courseWorkCategory.QueriedWorkCategories;
import org.vietngumn.schoolapp.event.courseWorkCategory.QueryWorkCategoriesCommand;
import org.vietngumn.schoolapp.event.courseWorkCategory.ReadCourseWorkCategory;
import org.vietngumn.schoolapp.event.courseWorkCategory.ReadCourseWorkCategoryCommand;
import org.vietngumn.schoolapp.event.courseWorkCategory.UpdateCourseWorkCategoryCommand;
import org.vietngumn.schoolapp.event.courseWorkCategory.UpdatedCourseWorkCategory;
import org.vietngumn.schoolapp.event.courseWorkCategory.WorkCategoryQueryCriteria;
import org.vietngumn.schoolapp.repository.CourseRepository;

@Service
public class CourseWorkCategoryServiceImpl implements CourseWorkCategoryService {
	
	private CourseRepository courseRepository;
	
	@Autowired
	public CourseWorkCategoryServiceImpl(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}
	
	@Override
	public CreatedCourseWorkCategory createCourseWorkCategory(CreateCourseWorkCategoryCommand createCommand) {
		CourseWorkCategoryDTO categoryDTO = createCommand.getDetails();
		Course course = courseRepository.findByCourseId(categoryDTO.getCourseId());
//		if (course == null) {
//			return CreatedCourseWorkCategory.notFound(categoryDTO.getCourseId(), categoryDTO.getCategoryId());
//		}
		String categoryId = new ObjectId().toString();
		CourseWorkCategory category = CourseWorkCategory.fromCourseWorkCategoryDTO(categoryDTO);
		category.setCategoryId(categoryId);
		course.addCourseWorkCategory(category);
		
		course = courseRepository.save(course);
		return new CreatedCourseWorkCategory(categoryId, category.toCourseWorkCategoryDTO());
	}
	
	@Override
	public ReadCourseWorkCategory readCourseWorkCategory(ReadCourseWorkCategoryCommand readCommand) {

		Course course = courseRepository.findByCourseId(readCommand.getCourseId());
		if (course == null) {
			return ReadCourseWorkCategory.notFound(readCommand.getCourseId(), readCommand.getCategoryId());
		}

		CourseWorkCategory category = course.getCourseWorkCategory(readCommand.getCategoryId());
		if (category == null) {
			return ReadCourseWorkCategory.notFound(readCommand.getCourseId(), readCommand.getCategoryId());
		}
		
		return new ReadCourseWorkCategory(readCommand.getCourseId(), readCommand.getCategoryId(), category.toCourseWorkCategoryDTO());
	}


	@Override
	public UpdatedCourseWorkCategory updateCourseWorkCategory(UpdateCourseWorkCategoryCommand updateCommand) {
		CourseWorkCategoryDTO categoryDTO = updateCommand.getDetails();
		Course course = courseRepository.findByCourseId(categoryDTO.getCourseId());
//		if (course == null) {
//			return CreatedCourseWorkCategory.notFound(categoryDTO.getCourseId(), categoryDTO.getCategoryId());
//		}
		
		CourseWorkCategory category = CourseWorkCategory.fromCourseWorkCategoryDTO(categoryDTO);
		category.setCategoryId(updateCommand.getCategoryId());
		
		CourseWorkCategory updatedCategory = course.updateCourseWorkCategory(category);
//		if (updatedCategory == null) {
//		return UpdatedCourseWorkCategory.notFound(categoryDTO.getCourseId(), categoryDTO.getCategoryId());
//	}
		
		course = courseRepository.save(course);
		return new UpdatedCourseWorkCategory(updateCommand.getCategoryId(), updatedCategory.toCourseWorkCategoryDTO());
	}

	@Override
	public DeletedCourseWorkCategory deleteCourseWorkCategory(DeleteCourseWorkCategoryCommand deleteCommand) {
		Course course = courseRepository.findByCourseId(deleteCommand.getCourseId());
		if (course == null) {
			return DeletedCourseWorkCategory.notFound(deleteCommand.getCourseId(), deleteCommand.getCategoryId());
		}

		CourseWorkCategory category = course.deleteCourseWorkCategory(deleteCommand.getCategoryId());
		if (category == null) {
			return DeletedCourseWorkCategory.notFound(deleteCommand.getCourseId(), deleteCommand.getCategoryId());
		}
		
		CourseWorkCategoryDTO details = category.toCourseWorkCategoryDTO();
		courseRepository.save(course);
		return new DeletedCourseWorkCategory(deleteCommand.getCourseId(), deleteCommand.getCategoryId(), details);
	}
	
	@Override
	public QueriedWorkCategories queryCourseWorkCategories(QueryWorkCategoriesCommand queryCommand) {
		
		WorkCategoryQueryCriteria criteria = queryCommand.getQueryCriteria();
		
		List<CourseWorkCategoryDTO> categoryDTOs = new ArrayList<CourseWorkCategoryDTO>();

		Course course = courseRepository.findByCourseId(criteria.getCourseId());
		
		if (course != null) {
			for (CourseWorkCategory category : course.getCourseWorkCategories()) {
				categoryDTOs.add(category.toCourseWorkCategoryDTO());
			}
		}
		
		return new QueriedWorkCategories(categoryDTOs);
	}
}
