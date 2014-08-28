package org.vietngumn.schoolapp.rest.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.hateoas.ResourceSupport;
import org.vietngumn.schoolapp.event.course.CourseDetails;


@XmlRootElement
public class Course extends ResourceSupport implements Serializable {
	private static final long serialVersionUID = 1L;

	private String courseId;
	private String courseName;

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public CourseDetails toCourseDetails() {
		CourseDetails details = new CourseDetails();

		details.setCourseId(courseId);
		details.setCourseName(courseName);
		return details;
	}

	public static Course fromCourseDetails(CourseDetails courseDetails) {
		Course course = new Course();

		course.setCourseId(courseDetails.getCourseId());

//		courseWork.add(linkTo(OrderQueriesController.class).slash(courseWork.getCourseWorkId()).withSelfRel());

		return course;
	}
	
}
