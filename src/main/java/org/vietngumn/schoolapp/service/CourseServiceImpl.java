package org.vietngumn.schoolapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vietngumn.schoolapp.domain.Course;
import org.vietngumn.schoolapp.event.course.CourseDTO;
import org.vietngumn.schoolapp.event.course.CourseQueryCriteria;
import org.vietngumn.schoolapp.event.course.CreateCourseCommand;
import org.vietngumn.schoolapp.event.course.CreatedCourse;
import org.vietngumn.schoolapp.event.course.DeleteCourseCommand;
import org.vietngumn.schoolapp.event.course.DeletedCourse;
import org.vietngumn.schoolapp.event.course.QueriedCourses;
import org.vietngumn.schoolapp.event.course.QueryCoursesCommand;
import org.vietngumn.schoolapp.event.course.ReadCourse;
import org.vietngumn.schoolapp.event.course.ReadCourseCommand;
import org.vietngumn.schoolapp.event.course.UpdateCourseCommand;
import org.vietngumn.schoolapp.event.course.UpdatedCourse;
import org.vietngumn.schoolapp.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {

	
	private CourseRepository courseRepository;
	
	@Autowired
	public CourseServiceImpl(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	@Override
	public CreatedCourse createCourse(CreateCourseCommand createCommand) {
		CourseDTO courseDetails = createCommand.getDetails();
		//		String courseId = new CourseId(courseDetails.getCourseLevel(), courseDetails.getCourseSection(), courseDetails.getSchoolYear());

		Course course = Course.fromCourseDTO(courseDetails);
		
		//		Course course = coursesRepository.findById(courseId.getId());
		//		if (course == null) {
		//			//throw handle create error response
		//			
		//		}

		course = courseRepository.save(course);
		return new CreatedCourse(course.getCourseId(), course.toCourseDTO());
	}

	@Override
	public ReadCourse readCourse(ReadCourseCommand readCommand) {

		Course course = courseRepository.findByCourseId(readCommand.getCourseId());

		if (course == null) {
			return ReadCourse.notFound(readCommand.getCourseId());
		}

		return new ReadCourse(readCommand.getCourseId(), course.toCourseDTO());
	}

	@Override
	public UpdatedCourse updateCourse(UpdateCourseCommand updateCommand) {
		Course course = courseRepository.findByCourseId(updateCommand.getCourseId());

		if (course == null) {
			return UpdatedCourse.notFound(updateCommand.getCourseId());
		}

		CourseDTO courseDetails = updateCommand.getCourseDetails();
		course.setCourseName(courseDetails.getCourseName());
		course.setSchoolYearId(courseDetails.getSchoolYearId());
		course.setDescription(courseDetails.getDescription());

		Course updatedCourse = courseRepository.save(course);

		return new UpdatedCourse(updateCommand.getCourseId(), updatedCourse.toCourseDTO());
	}
	
	@Override
	public DeletedCourse deleteCourse(DeleteCourseCommand deleteCommand) {

		Course course = courseRepository.findByCourseId(deleteCommand.getCourseId());

		if (course == null) {
			return DeletedCourse.notFound(deleteCommand.getCourseId());
		}

		CourseDTO details = course.toCourseDTO();

		//		if (!course.canBeDeleted()) {
		//			return DeleteCourseResponse.deletionForbidden(deleteRequest.getCourseId(), details);
		//		}

		courseRepository.delete(course);
		return new DeletedCourse(deleteCommand.getCourseId(), details);
	}
	
	@Override
	public QueriedCourses queryCourses(QueryCoursesCommand queryCommand) {
		CourseQueryCriteria criteria = queryCommand.getQueryCriteria();
		
		List<Course> courses = courseRepository.findBySchoolYearId(criteria.getSchoolYearId());
		
		List<CourseDTO> courseDTOs = new ArrayList<CourseDTO>();
		for (Course course : courses) {
			courseDTOs.add(course.toCourseDTO());
		}
		
		return new QueriedCourses(courseDTOs);
	}
}
