package org.vietngumn.schoolapp.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vietngumn.schoolapp.domain.Course;
import org.vietngumn.schoolapp.domain.CourseWork;
import org.vietngumn.schoolapp.domain.CourseWorkCategory;
import org.vietngumn.schoolapp.event.courseWork.CourseWorkDTO;
import org.vietngumn.schoolapp.event.courseWork.CourseWorkIdPath;
import org.vietngumn.schoolapp.event.courseWork.CreateCourseWorkCommand;
import org.vietngumn.schoolapp.event.courseWork.CreatedCourseWork;
import org.vietngumn.schoolapp.event.courseWork.DeleteCourseWorkCommand;
import org.vietngumn.schoolapp.event.courseWork.DeletedCourseWork;
import org.vietngumn.schoolapp.event.courseWork.QueriedWorks;
import org.vietngumn.schoolapp.event.courseWork.QueryWorksCommand;
import org.vietngumn.schoolapp.event.courseWork.ReadCourseWork;
import org.vietngumn.schoolapp.event.courseWork.ReadCourseWorkCommand;
import org.vietngumn.schoolapp.event.courseWork.UpdateCourseWorkCommand;
import org.vietngumn.schoolapp.event.courseWork.UpdatedCourseWork;
import org.vietngumn.schoolapp.event.courseWork.WorkQueryCriteria;
import org.vietngumn.schoolapp.repository.CourseRepository;

@Service
public class CourseWorkServiceImpl implements CourseWorkService {
	
	private CourseRepository courseRepository;
	
	@Autowired
	public CourseWorkServiceImpl(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}
	
	@Override
	public CreatedCourseWork createCourseWork(CreateCourseWorkCommand createCommand) {
		CourseWorkIdPath workIdPath = createCommand.getWorkIdPath();
		CourseWorkDTO workDTO = createCommand.getDetails();
		
		Course course = courseRepository.findByCourseId(workIdPath.getCourseId());

		CourseWorkCategory category = course.getCourseWorkCategory(workIdPath.getCategoryId());
		
		CourseWork work = CourseWork.fromCourseWorkDTO(workDTO);
		String workId = new ObjectId().toString();
		work.setWorkId(workId);
		
		CourseWork addedWork = category.addCourseWork(work);
		
		course = courseRepository.save(course);
		
		CourseWorkIdPath newCreatedId = new CourseWorkIdPath(
				workIdPath.getCourseId(), workIdPath.getCategoryId(), addedWork.getWorkId());
		return new CreatedCourseWork(newCreatedId, work.toCourseWorkDTO());
	}
	
	@Override
	public ReadCourseWork readCourseWork(ReadCourseWorkCommand readCommand) {
		CourseWorkIdPath workIdPath = readCommand.getWorkIdPath();
		
		Course course = courseRepository.findByCourseId(workIdPath.getCourseId());
		if (course == null) {
			return ReadCourseWork.notFound(workIdPath);
		}

		CourseWorkCategory category = course.getCourseWorkCategory(workIdPath.getCategoryId());
		
		CourseWork work = category.getCourseWork(workIdPath.getWorkId());
		if (work == null) {
			return ReadCourseWork.notFound(workIdPath);
		}
		
		return new ReadCourseWork(workIdPath, work.toCourseWorkDTO());
	}

	@Override
	public UpdatedCourseWork updateCourseWork(UpdateCourseWorkCommand updateCommand) {
		CourseWorkIdPath workIdPath = updateCommand.getWorkIdPath();
		CourseWorkDTO workDTO = updateCommand.getDetails();
		
		Course course = courseRepository.findByCourseId(workIdPath.getCourseId());
//		if (course == null) {
//			return CreatedCourseWork.notFound(categoryDTO.getCourseId(), categoryDTO.getCategoryId());
//		}
		
		CourseWorkCategory category = course.getCourseWorkCategory(workIdPath.getCategoryId());
		
		CourseWork work = CourseWork.fromCourseWorkDTO(workDTO);
		
		CourseWork updatedWork = category.updateCourseWork(work);
//		if (updatedCategory == null) {
//		return UpdatedCourseWork.notFound(categoryDTO.getCourseId(), categoryDTO.getCategoryId());
//	}
		
		course = courseRepository.save(course);
		
		return new UpdatedCourseWork(workIdPath, updatedWork.toCourseWorkDTO());
	}

	@Override
	public DeletedCourseWork deleteCourseWork(DeleteCourseWorkCommand deleteCommand) {
		CourseWorkIdPath workIdPath = deleteCommand.getCourseWorkIdPath();
		
		Course course = courseRepository.findByCourseId(workIdPath.getCourseId());
		if (course == null) {
			return DeletedCourseWork.notFound(workIdPath);
		}

		CourseWorkCategory category = course.getCourseWorkCategory(workIdPath.getCategoryId());
		
		CourseWork work = category.deleteCourseWork(workIdPath.getWorkId());
		if (work == null) {
			return DeletedCourseWork.notFound(workIdPath);
		}
		
		courseRepository.save(course);
		
		CourseWorkDTO details = work.toCourseWorkDTO();
		return new DeletedCourseWork(workIdPath, details);
	}
	
	@Override
	public QueriedWorks queryCourseWorks(QueryWorksCommand queryCommand) {
		
		WorkQueryCriteria criteria = queryCommand.getQueryCriteria();
		
		List<CourseWorkDTO> workDTOs = new ArrayList<CourseWorkDTO>();

		Course course = courseRepository.findByCourseId(criteria.getWorkIdPath().getCourseId());
		
		CourseWorkCategory category = course.getCourseWorkCategory(criteria.getWorkIdPath().getCategoryId());
		
		if (category != null) {
			for (CourseWork work : category.getCourseWorks()) {
				workDTOs.add(work.toCourseWorkDTO());
			}
		}
		
		return new QueriedWorks(workDTOs);
	}
}
