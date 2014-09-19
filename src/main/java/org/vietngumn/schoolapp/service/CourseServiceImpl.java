package org.vietngumn.schoolapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vietngumn.schoolapp.domain.Course;
import org.vietngumn.schoolapp.event.course.CourseDTO;
import org.vietngumn.schoolapp.event.course.CreateCourseCommand;
import org.vietngumn.schoolapp.event.course.CreatedCourse;
import org.vietngumn.schoolapp.event.course.DeleteCourseCommand;
import org.vietngumn.schoolapp.event.course.DeletedCourse;
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

		Course course = new Course();
		course.setCourseId(courseDetails.getCourseId());
		course.setCourseName(courseDetails.getCourseName());

		//		Course course = coursesRepository.findById(courseId.getId());
		//		if (course == null) {
		//			//throw handle create error response
		//			
		//		}

		/*CourseWork courseWork = new CourseWork();
		courseWork.setCourseWorkId(new ObjectId().toString());
		courseWork.setName("exam one");
		courseWork.setDescription("exam one description");
		course.addCourseWork(courseWork);
		
		CourseWork courseWork2 = new CourseWork();
		courseWork2.setCourseWorkId(new ObjectId().toString());
		courseWork2.setName("exam two");
		courseWork2.setDescription("exam two description");
		course.addCourseWork(courseWork2);*/
		
		course = courseRepository.save(course);
		return new CreatedCourse(course.getCourseId(), course.toCourseDTO());
	}

	@Override
	public ReadCourse readCourse(ReadCourseCommand readRequest) {

		Course course = courseRepository.findByCourseId(readRequest.getCourseId());

		if (course == null) {
			return ReadCourse.notFound(readRequest.getCourseId());
		}

		return new ReadCourse(readRequest.getCourseId(), course.toCourseDTO());
	}

	@Override
	public UpdatedCourse updateCourse(UpdateCourseCommand updateRequest) {
		Course course = courseRepository.findByCourseId(updateRequest.getCourseId());

		if (course == null) {
			return UpdatedCourse.notFound(updateRequest.getCourseId());
		}

		CourseDTO courseDetails = updateRequest.getCourseDetails();
		course.setCourseName(courseDetails.getCourseName());

		Course updatedCourse = courseRepository.save(course);

		return new UpdatedCourse(updateRequest.getCourseId(), updatedCourse.toCourseDTO());
	}
	
	@Override
	public DeletedCourse deleteCourse(DeleteCourseCommand deleteRequest) {

		Course course = courseRepository.findByCourseId(deleteRequest.getCourseId());

		if (course == null) {
			return DeletedCourse.notFound(deleteRequest.getCourseId());
		}

		CourseDTO details = course.toCourseDTO();

		//		if (!course.canBeDeleted()) {
		//			return DeleteCourseResponse.deletionForbidden(deleteRequest.getCourseId(), details);
		//		}

		courseRepository.delete(course);
		return new DeletedCourse(deleteRequest.getCourseId(), details);
	}
}
