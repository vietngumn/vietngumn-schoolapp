package org.vietngumn.schoolapp.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.vietngumn.schoolapp.event.courseWork.QueriedCourseWorks;
import org.vietngumn.schoolapp.event.courseWork.QueryCourseWorksCommand;
import org.vietngumn.schoolapp.event.courseWork.ReadCourseWork;
import org.vietngumn.schoolapp.event.courseWork.ReadCourseWorkCommand;
import org.vietngumn.schoolapp.event.courseWork.UpdateCourseWorkCommand;
import org.vietngumn.schoolapp.event.courseWork.UpdatedCourseWork;
import org.vietngumn.schoolapp.event.courseWork.WorkQueryCriteria;
import org.vietngumn.schoolapp.repository.CourseRepository;

@Service
public class CourseWorkServiceImpl implements CourseWorkService {
	
	private static Logger LOG = LoggerFactory.getLogger(CourseWorkServiceImpl.class);
	
	private CourseRepository courseRepository;
	
	@Autowired
	public CourseWorkServiceImpl(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}
	
	@Override
	public CreatedCourseWork createCourseWork(CreateCourseWorkCommand createCommand) {
		CourseWorkDTO workDTO = createCommand.getDetails();
		CourseWorkIdPath workIdPath = workDTO.getIdPath();
		
		Course course = courseRepository.findByCourseId(workIdPath.getCourseId());

		CourseWorkCategory category = course.getCourseWorkCategory(workIdPath.getCategoryId());
		
		CourseWork work = CourseWork.fromCourseWorkDTO(workDTO);
		String workId = new ObjectId().toString();
		work.setWorkId(workId);
		
		CourseWork addedWork = category.addCourseWork(work);
		
		courseRepository.save(course);
		
		CourseWorkIdPath newCreatedId = new CourseWorkIdPath(
				workIdPath.getCourseId(), workIdPath.getCategoryId(), addedWork.getWorkId());
		return new CreatedCourseWork(addedWork.toCourseWorkDTO(newCreatedId));
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
		
		return new ReadCourseWork(work.toCourseWorkDTO(workIdPath));
	}

	@Override
	public UpdatedCourseWork updateCourseWork(UpdateCourseWorkCommand updateCommand) {
		CourseWorkDTO workDTO = updateCommand.getDetails();
		CourseWorkIdPath workIdPath = workDTO.getIdPath();
		
		Course course = courseRepository.findByCourseId(workIdPath.getCourseId());
		if (course == null) {
			LOG.debug("Could not find Course with ID [" + workIdPath.getCourseId() + "]");
		}
		
		CourseWorkCategory category = course.getCourseWorkCategory(workIdPath.getCategoryId());
		if (category == null) {
			LOG.debug("Could not find Course Work Category with ID [" + workIdPath.getCategoryId() + "]");
		}
		
		CourseWork work = CourseWork.fromCourseWorkDTO(workDTO);
		
		CourseWork updatedWork = category.updateCourseWork(work);
		if (updatedWork == null) {
			LOG.debug("Could not find Course Work with ID [" + workIdPath.getCategoryId() + "]");
		}
		
		courseRepository.save(course);
		
		return new UpdatedCourseWork(updatedWork.toCourseWorkDTO(workIdPath));
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
		
		CourseWorkDTO details = work.toCourseWorkDTO(workIdPath);
		return new DeletedCourseWork(details);
	}
	
	@Override
	public QueriedCourseWorks queryCourseWorks(QueryCourseWorksCommand queryCommand) {
		
		WorkQueryCriteria criteria = queryCommand.getQueryCriteria();
		String courseId = criteria.getWorkIdPath().getCourseId();
		String categoryId = criteria.getWorkIdPath().getCategoryId();
		
		List<CourseWorkDTO> workDTOs = new ArrayList<CourseWorkDTO>();

		Course course = courseRepository.findByCourseId(courseId);
		
		CourseWorkCategory category = course.getCourseWorkCategory(categoryId);
		
		if (category != null) {
			for (CourseWork work : category.getCourseWorks()) {
				CourseWorkIdPath idPath = new CourseWorkIdPath(courseId, categoryId, work.getWorkId());
				workDTOs.add(work.toCourseWorkDTO(idPath));
			}
		}
		
		return new QueriedCourseWorks(workDTOs);
	}
}
