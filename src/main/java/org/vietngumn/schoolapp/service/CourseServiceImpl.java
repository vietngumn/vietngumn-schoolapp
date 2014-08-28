package org.vietngumn.schoolapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vietngumn.schoolapp.domain.Course;
import org.vietngumn.schoolapp.event.course.CourseDetails;
import org.vietngumn.schoolapp.event.course.CreateCourseRequest;
import org.vietngumn.schoolapp.event.course.CreateCourseResponse;
import org.vietngumn.schoolapp.event.course.DeleteCourseRequest;
import org.vietngumn.schoolapp.event.course.DeleteCourseResponse;
import org.vietngumn.schoolapp.event.course.ReadCourseRequest;
import org.vietngumn.schoolapp.event.course.ReadCourseResponse;
import org.vietngumn.schoolapp.event.course.UpdateCourseRequest;
import org.vietngumn.schoolapp.event.course.UpdateCourseResponse;
import org.vietngumn.schoolapp.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {

	
	private CourseRepository courseRepository;
	
	@Autowired
	public CourseServiceImpl(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	@Override
	public CreateCourseResponse createCourse(CreateCourseRequest createRequest) {
		CourseDetails courseDetails = createRequest.getDetails();
		//		String courseId = new CourseId(courseDetails.getCourseLevel(), courseDetails.getCourseSection(), courseDetails.getSchoolYear());

		Course course = new Course();
		course.setCourseId(courseDetails.getCourseId());
		course.setCourseName(courseDetails.getCourseName());

		//		Course course = coursesRepository.findById(courseId.getId());
		//		if (course == null) {
		//			//throw handle create error response
		//			
		//		}

		course = courseRepository.save(course);
		return new CreateCourseResponse(course.getCourseId(), course.toCourseDetails());
	}

	@Override
	public ReadCourseResponse readCourse(ReadCourseRequest readRequest) {

		Course course = courseRepository.findByCourseId(readRequest.getCourseId());

		if (course == null) {
			return ReadCourseResponse.notFound(readRequest.getCourseId());
		}

		return new ReadCourseResponse(readRequest.getCourseId(), course.toCourseDetails());
	}

	@Override
	public UpdateCourseResponse updateCourse(UpdateCourseRequest updateRequest) {
		Course course = courseRepository.findByCourseId(updateRequest.getCourseId());

		if (course == null) {
			return UpdateCourseResponse.notFound(updateRequest.getCourseId());
		}

		CourseDetails courseDetails = updateRequest.getCourseDetails();
		course.setCourseName(courseDetails.getCourseName());

		Course updatedCourse = courseRepository.save(course);

		return new UpdateCourseResponse(updateRequest.getCourseId(), updatedCourse.toCourseDetails());
	}
	
	@Override
	public DeleteCourseResponse deleteCourse(DeleteCourseRequest deleteRequest) {

		Course course = courseRepository.findByCourseId(deleteRequest.getCourseId());

		if (course == null) {
			return DeleteCourseResponse.notFound(deleteRequest.getCourseId());
		}

		CourseDetails details = course.toCourseDetails();

		//		if (!course.canBeDeleted()) {
		//			return DeleteCourseResponse.deletionForbidden(deleteRequest.getCourseId(), details);
		//		}

		courseRepository.delete(course);
		return new DeleteCourseResponse(deleteRequest.getCourseId(), details);
	}
}
