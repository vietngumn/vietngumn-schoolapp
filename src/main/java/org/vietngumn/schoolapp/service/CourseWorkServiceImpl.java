package org.vietngumn.schoolapp.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vietngumn.schoolapp.domain.Course;
import org.vietngumn.schoolapp.domain.CourseWork;
import org.vietngumn.schoolapp.domain.CourseWorkCategory;
import org.vietngumn.schoolapp.event.courseWork.CourseWorkDTO;
import org.vietngumn.schoolapp.event.courseWork.CreateCourseWorkCommand;
import org.vietngumn.schoolapp.event.courseWork.CreatedCourseWork;
import org.vietngumn.schoolapp.event.courseWork.DeleteCourseWorkCommand;
import org.vietngumn.schoolapp.event.courseWork.DeletedCourseWork;
import org.vietngumn.schoolapp.event.courseWork.ReadCourseWork;
import org.vietngumn.schoolapp.event.courseWork.ReadCourseWorkCommand;
import org.vietngumn.schoolapp.event.courseWork.UpdateCourseWorkCommand;
import org.vietngumn.schoolapp.event.courseWork.UpdatedCourseWork;
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
		CourseWorkDTO workDTO = createCommand.getDetails();
		Course course = courseRepository.findByCourseId(workDTO.getCourseId());

		CourseWorkCategory category = course.getCourseWorkCategory(workDTO.getCategoryId());
		
		String workId = new ObjectId().toString();
		CourseWork work = CourseWork.fromCourseWorkDTO(workDTO);
		work.setWorkId(workId);
		category.addCourseWork(work);
		
		course = courseRepository.save(course);
		return new CreatedCourseWork(workId, work.toCourseWorkDTO());
	}
	
	@Override
	public ReadCourseWork readCourseWork(ReadCourseWorkCommand readCommand) {

		Course course = courseRepository.findByCourseId(readCommand.getCourseId());
		if (course == null) {
			return ReadCourseWork.notFound(readCommand.getCourseId(), readCommand.getCategoryId(), readCommand.getWorkId());
		}

		CourseWorkCategory category = course.getCourseWorkCategory(readCommand.getCategoryId());
		
		CourseWork work = category.getCourseWork(readCommand.getWorkId());
		if (work == null) {
			return ReadCourseWork.notFound(readCommand.getCourseId(), readCommand.getCategoryId(), readCommand.getWorkId());
		}
		
		return new ReadCourseWork(readCommand.getCourseId(), readCommand.getCategoryId(), readCommand.getWorkId(), work.toCourseWorkDTO());
	}

	@Override
	public UpdatedCourseWork updateCourseWork(UpdateCourseWorkCommand updateCommand) {
		CourseWorkDTO workDTO = updateCommand.getDetails();
		
		Course course = courseRepository.findByCourseId(workDTO.getCourseId());
//		if (course == null) {
//			return CreatedCourseWork.notFound(categoryDTO.getCourseId(), categoryDTO.getCategoryId());
//		}
		
		CourseWorkCategory category = course.getCourseWorkCategory(workDTO.getCategoryId());
		
		CourseWork work = CourseWork.fromCourseWorkDTO(workDTO);
		work.setWorkId(updateCommand.getWorkId());
		
		CourseWork updatedWork = category.updateCourseWork(work);
//		if (updatedCategory == null) {
//		return UpdatedCourseWork.notFound(categoryDTO.getCourseId(), categoryDTO.getCategoryId());
//	}
		
		course = courseRepository.save(course);
		return new UpdatedCourseWork(updateCommand.getWorkId(), updatedWork.toCourseWorkDTO());
	}

	@Override
	public DeletedCourseWork deleteCourseWork(DeleteCourseWorkCommand deleteCommand) {
		Course course = courseRepository.findByCourseId(deleteCommand.getCourseId());
		if (course == null) {
			return DeletedCourseWork.notFound(deleteCommand.getCourseId(), deleteCommand.getCategoryId(), deleteCommand.getWorkId());
		}

		CourseWorkCategory category = course.getCourseWorkCategory(deleteCommand.getCategoryId());
		
		CourseWork work = category.deleteCourseWork(deleteCommand.getWorkId());
		if (work == null) {
			return DeletedCourseWork.notFound(deleteCommand.getCourseId(), deleteCommand.getCategoryId(), deleteCommand.getWorkId());
		}
		
		courseRepository.save(course);
		
		CourseWorkDTO details = work.toCourseWorkDTO();
		return new DeletedCourseWork(deleteCommand.getCourseId(), deleteCommand.getCategoryId(), deleteCommand.getWorkId(), details);
	}
}
