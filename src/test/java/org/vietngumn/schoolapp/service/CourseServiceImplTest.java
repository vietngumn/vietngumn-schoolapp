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
		CourseDetails courseDetails = new CourseDetails();
		courseDetails.setCourseId(courseId);
		courseDetails.setCourseName(courseName);
		CreateCourseRequest createRequest = new CreateCourseRequest(courseDetails);

		Course course = new Course();
		course.setCourseId(courseId);
		course.setCourseName(courseName);
		
		ArgumentCaptor<Course> argumentCaptor = ArgumentCaptor.forClass(Course.class);
		when(mockCourseRepository.save(argumentCaptor.capture())).thenReturn(course);

		CreateCourseResponse createResponse = courseService.createCourse(createRequest);

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
		ReadCourseRequest readRequest = new ReadCourseRequest(courseId);

		when(mockCourseRepository.findByCourseId(courseId)).thenReturn(course);

		ReadCourseResponse readResponse = courseService.readCourse(readRequest);

		verify(mockCourseRepository).findByCourseId(courseId);

		assertEquals(courseId, readResponse.getCourseId());
		assertEquals("courseName", readResponse.getCourseDetails().getCourseName());
	}

	@Test
	public void readCourse_notFound() {
		String courseId = "invalidCourseId";
		ReadCourseRequest readRequest = new ReadCourseRequest(courseId);

		when(mockCourseRepository.findByCourseId(courseId)).thenReturn(null);

		ReadCourseResponse readResponse = courseService.readCourse(readRequest);

		verify(mockCourseRepository).findByCourseId(courseId);

		assertEquals(courseId, readResponse.getCourseId());
		assertFalse(readResponse.isEntityFound());
	}

	@Test
	public void updateCourse() {
		String courseId = "courseId";
		CourseDetails courseDetails = new CourseDetails();
		courseDetails.setCourseName("newCourseName");
		UpdateCourseRequest updateRequest = new UpdateCourseRequest(courseId, courseDetails);

		Course course = new Course();
		course.setCourseId(courseId);
		course.setCourseName("courseName");

		when(mockCourseRepository.findByCourseId(courseId)).thenReturn(course);
		when(mockCourseRepository.save(course)).thenReturn(course);

		UpdateCourseResponse updateResponse = courseService.updateCourse(updateRequest);

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
		CourseDetails courseDetails = new CourseDetails();
		courseDetails.setCourseName("newCourseName");
		UpdateCourseRequest updateRequest = new UpdateCourseRequest(courseId, courseDetails);

		when(mockCourseRepository.findByCourseId(courseId)).thenReturn(null);

		UpdateCourseResponse updateResponse = courseService.updateCourse(updateRequest);

		verify(mockCourseRepository).findByCourseId(courseId);

		assertEquals(courseId, updateResponse.getCourseId());
		assertFalse(updateResponse.isEntityFound());
	}
	
	@Test
	public void deleteCourse() {
		String courseId = "courseId";
		DeleteCourseRequest deleteRequest = new DeleteCourseRequest(courseId);

		Course course = new Course();
		course.setCourseId(courseId);
		course.setCourseName("courseName");

		when(mockCourseRepository.findByCourseId(courseId)).thenReturn(course);

		DeleteCourseResponse deleteResponse = courseService.deleteCourse(deleteRequest);

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
		DeleteCourseRequest deleteRequest = new DeleteCourseRequest(courseId);

		when(mockCourseRepository.findByCourseId(courseId)).thenReturn(null);

		DeleteCourseResponse deleteResponse = courseService.deleteCourse(deleteRequest);

		verify(mockCourseRepository).findByCourseId(courseId);
		verify(mockCourseRepository, Mockito.never()).delete(any(Course.class));

		assertEquals(courseId, deleteResponse.getCourseId());
		assertFalse(deleteResponse.isEntityFound());
	}
}
