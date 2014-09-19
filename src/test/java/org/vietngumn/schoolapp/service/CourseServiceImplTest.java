package org.vietngumn.schoolapp.service;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.vietngumn.schoolapp.domain.Course;
import org.vietngumn.schoolapp.event.course.CourseDTO;
import org.vietngumn.schoolapp.event.course.CreateCourseCommand;
import org.vietngumn.schoolapp.event.course.CreatedCourse;
import org.vietngumn.schoolapp.event.course.DeleteCourseCommand;
import org.vietngumn.schoolapp.event.course.DeletedCourse;
import org.vietngumn.schoolapp.event.course.ReadCourseCommand;
import org.vietngumn.schoolapp.event.course.ReadCourse;
import org.vietngumn.schoolapp.event.course.UpdateCourseCommand;
import org.vietngumn.schoolapp.event.course.UpdatedCourse;
import org.vietngumn.schoolapp.repository.CourseRepository;
import org.vietngumn.schoolapp.service.CourseServiceImpl;

public class CourseServiceImplTest {

	private CourseServiceImpl courseService;
	private CourseRepository mockCourseRepository;
	@Mock Course mockCourse;


	@Before
	public void setup() {
		mockCourseRepository = mock(CourseRepository.class);
		courseService = new CourseServiceImpl(mockCourseRepository);
	}

	@Test
	public void createCourse() {
		String courseId = "courseId";
		String courseName = "courseName";
		CourseDTO courseDetails = new CourseDTO();
		courseDetails.setCourseId(courseId);
		courseDetails.setCourseName(courseName);
		CreateCourseCommand createRequest = new CreateCourseCommand(courseDetails);

		Course course = new Course();
		course.setCourseId(courseId);
		course.setCourseName(courseName);
		
		ArgumentCaptor<Course> argumentCaptor = ArgumentCaptor.forClass(Course.class);
		when(mockCourseRepository.save(argumentCaptor.capture())).thenReturn(course);

		CreatedCourse createResponse = courseService.createCourse(createRequest);

		verify(mockCourseRepository).save(any(Course.class));
		verifyNoMoreInteractions(mockCourseRepository);
		
		Course savedCourse = argumentCaptor.getValue();
		assertEquals(courseId, savedCourse.getCourseId());
		assertEquals(courseName, savedCourse.getCourseName());

		assertEquals(courseId, createResponse.getNewCourseId());
		assertEquals(courseName, createResponse.getCourseDetails().getCourseName());
	}

	@Test
	public void readCourse() {
		String courseId = "courseId";
		Course course = new Course();
		course.setCourseId(courseId);
		course.setCourseName("courseName");
		ReadCourseCommand readRequest = new ReadCourseCommand(courseId);

		when(mockCourseRepository.findByCourseId(courseId)).thenReturn(course);

		ReadCourse readResponse = courseService.readCourse(readRequest);

		verify(mockCourseRepository).findByCourseId(courseId);

		assertEquals(courseId, readResponse.getCourseId());
		assertEquals("courseName", readResponse.getCourseDetails().getCourseName());
	}

	@Test
	public void readCourse_notFound() {
		String courseId = "invalidCourseId";
		ReadCourseCommand readRequest = new ReadCourseCommand(courseId);

		when(mockCourseRepository.findByCourseId(courseId)).thenReturn(null);

		ReadCourse readResponse = courseService.readCourse(readRequest);

		verify(mockCourseRepository).findByCourseId(courseId);

		assertEquals(courseId, readResponse.getCourseId());
		assertFalse(readResponse.isEntityFound());
	}

	@Test
	public void updateCourse() {
		String courseId = "courseId";
		CourseDTO courseDetails = new CourseDTO();
		courseDetails.setCourseName("newCourseName");
		UpdateCourseCommand updateRequest = new UpdateCourseCommand(courseId, courseDetails);

		Course course = new Course();
		course.setCourseId(courseId);
		course.setCourseName("courseName");

		when(mockCourseRepository.findByCourseId(courseId)).thenReturn(course);
		when(mockCourseRepository.save(course)).thenReturn(course);

		UpdatedCourse updateResponse = courseService.updateCourse(updateRequest);

		verify(mockCourseRepository).findByCourseId(courseId);
		verify(mockCourseRepository).save(any(Course.class));

		assertEquals(courseId, updateResponse.getCourseId());
		assertEquals("newCourseName", updateResponse.getCourseDetails().getCourseName());
		assertTrue(updateResponse.isEntityFound());
		assertTrue( updateResponse.isUpdateCompleted());
	}

	@Test
	public void updateCourse_notFound() {
		String courseId = "invalidCourseId";
		CourseDTO courseDetails = new CourseDTO();
		courseDetails.setCourseName("newCourseName");
		UpdateCourseCommand updateRequest = new UpdateCourseCommand(courseId, courseDetails);

		when(mockCourseRepository.findByCourseId(courseId)).thenReturn(null);

		UpdatedCourse updateResponse = courseService.updateCourse(updateRequest);

		verify(mockCourseRepository).findByCourseId(courseId);

		assertEquals(courseId, updateResponse.getCourseId());
		assertFalse(updateResponse.isEntityFound());
	}
	
	@Test
	public void deleteCourse() {
		String courseId = "courseId";
		DeleteCourseCommand deleteRequest = new DeleteCourseCommand(courseId);

		Course course = new Course();
		course.setCourseId(courseId);
		course.setCourseName("courseName");

		when(mockCourseRepository.findByCourseId(courseId)).thenReturn(course);

		DeletedCourse deleteResponse = courseService.deleteCourse(deleteRequest);

		verify(mockCourseRepository).findByCourseId(courseId);
		verify(mockCourseRepository).delete(course);

		assertEquals(courseId, deleteResponse.getCourseId());
		assertEquals("courseName", deleteResponse.getCourseDetails().getCourseName());
		assertTrue(deleteResponse.isEntityFound());
		assertTrue( deleteResponse.isDeletionCompleted());
	}
	
	@Test
	public void deleteCourse_notFound() {
		String courseId = "courseId";
		DeleteCourseCommand deleteRequest = new DeleteCourseCommand(courseId);

		when(mockCourseRepository.findByCourseId(courseId)).thenReturn(null);

		DeletedCourse deleteResponse = courseService.deleteCourse(deleteRequest);

		verify(mockCourseRepository).findByCourseId(courseId);
		verify(mockCourseRepository, Mockito.never()).delete(any(Course.class));

		assertEquals(courseId, deleteResponse.getCourseId());
		assertFalse(deleteResponse.isEntityFound());
	}
}
