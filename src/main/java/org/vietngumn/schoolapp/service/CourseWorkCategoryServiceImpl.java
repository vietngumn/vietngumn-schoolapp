package org.vietngumn.schoolapp.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vietngumn.schoolapp.domain.Course;
import org.vietngumn.schoolapp.domain.CourseWorkCategory;
import org.vietngumn.schoolapp.event.courseWorkCategory.CourseWorkCategoryDTO;
import org.vietngumn.schoolapp.event.courseWorkCategory.CourseWorkCategoryIdPath;
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
	
	private static Logger LOG = LoggerFactory.getLogger(CourseWorkCategoryServiceImpl.class);
	
	private CourseRepository courseRepository;
	
	@Autowired
	public CourseWorkCategoryServiceImpl(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}
	
	@Override
	public CreatedCourseWorkCategory createCourseWorkCategory(CreateCourseWorkCategoryCommand createCommand) {
		CourseWorkCategoryDTO categoryDTO = createCommand.getDetails();
		CourseWorkCategoryIdPath categoryIdPath = categoryDTO.getIdPath();
		
		Course course = courseRepository.findByCourseId(categoryIdPath.getCourseId());
//		if (course == null) {
//			return CreatedCourseWorkCategory.notFound(categoryDTO.getCourseId(), categoryDTO.getCategoryId());
//		}
		
		CourseWorkCategory category = CourseWorkCategory.fromCourseWorkCategoryDTO(categoryDTO);
		String categoryId = new ObjectId().toString();
		category.setCategoryId(categoryId);
		
		CourseWorkCategory addedCategory = course.addCourseWorkCategory(category);
		
		courseRepository.save(course);
		
		CourseWorkCategoryIdPath newCreatedId = new CourseWorkCategoryIdPath(
				categoryIdPath.getCourseId(), addedCategory.getCategoryId());
		return new CreatedCourseWorkCategory(category.toCourseWorkCategoryDTO(newCreatedId));
	}
	
	@Override
	public ReadCourseWorkCategory readCourseWorkCategory(ReadCourseWorkCategoryCommand readCommand) {
		CourseWorkCategoryIdPath categoryIdPath = readCommand.getCategoryIdPath();
		
		Course course = courseRepository.findByCourseId(categoryIdPath.getCourseId());
		if (course == null) {
			return ReadCourseWorkCategory.notFound(categoryIdPath);
		}

		CourseWorkCategory category = course.getCourseWorkCategory(categoryIdPath.getCategoryId());
		if (category == null) {
			return ReadCourseWorkCategory.notFound(categoryIdPath);
		}
		
		return new ReadCourseWorkCategory(category.toCourseWorkCategoryDTO(categoryIdPath));
	}


	@Override
	public UpdatedCourseWorkCategory updateCourseWorkCategory(UpdateCourseWorkCategoryCommand updateCommand) {
		CourseWorkCategoryDTO categoryDTO = updateCommand.getDetails();
		CourseWorkCategoryIdPath categoryIdPath = categoryDTO.getIdPath();
		
		Course course = courseRepository.findByCourseId(categoryIdPath.getCourseId());
		if (course == null) {
			LOG.debug("Could not find Course with ID [" + categoryIdPath.getCourseId() + "]");
		}
		
		CourseWorkCategory category = CourseWorkCategory.fromCourseWorkCategoryDTO(categoryDTO);
		
		CourseWorkCategory updatedCategory = course.updateCourseWorkCategory(category);
		if (updatedCategory == null) {
			LOG.debug("Could not find Course Work Category with ID [" + categoryIdPath.getCategoryId() + "]");
		}
		
		courseRepository.save(course);
		
		return new UpdatedCourseWorkCategory(updatedCategory.toCourseWorkCategoryDTO(categoryIdPath));
	}

	@Override
	public DeletedCourseWorkCategory deleteCourseWorkCategory(DeleteCourseWorkCategoryCommand deleteCommand) {
		CourseWorkCategoryIdPath categoryIdPath = deleteCommand.getCategoryIdPath();
		
		Course course = courseRepository.findByCourseId(categoryIdPath.getCourseId());
		if (course == null) {
			return DeletedCourseWorkCategory.notFound(categoryIdPath);
		}

		CourseWorkCategory category = course.deleteCourseWorkCategory(categoryIdPath.getCategoryId());
		if (category == null) {
			return DeletedCourseWorkCategory.notFound(categoryIdPath);
		}
		
		courseRepository.save(course);
		
		CourseWorkCategoryDTO details = category.toCourseWorkCategoryDTO(categoryIdPath);
		return new DeletedCourseWorkCategory(details);
	}
	
	@Override
	public QueriedWorkCategories queryCourseWorkCategories(QueryWorkCategoriesCommand queryCommand) {
		
		WorkCategoryQueryCriteria criteria = queryCommand.getQueryCriteria();
		String courseId = criteria.getCategoryIdPath().getCourseId();
		
		List<CourseWorkCategoryDTO> categoryDTOs = new ArrayList<CourseWorkCategoryDTO>();

		Course course = courseRepository.findByCourseId(criteria.getCategoryIdPath().getCourseId());
		
		if (course != null) {
			for (CourseWorkCategory category : course.getCourseWorkCategories()) {
				CourseWorkCategoryIdPath categoryIdPath = new CourseWorkCategoryIdPath(courseId, category.getCategoryId());
				categoryDTOs.add(category.toCourseWorkCategoryDTO(categoryIdPath));
			}
		}
		
		return new QueriedWorkCategories(categoryDTOs);
	}
}
