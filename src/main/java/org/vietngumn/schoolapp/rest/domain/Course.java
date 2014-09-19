package org.vietngumn.schoolapp.rest.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.hateoas.ResourceSupport;
import org.vietngumn.schoolapp.event.course.CourseDTO;


@XmlRootElement
public class Course extends ResourceSupport implements Serializable {
	private static final long serialVersionUID = 1L;

	private String courseId;
	private String courseName;
	private List<CourseWorkCategory> courseWorkCategories = new ArrayList<CourseWorkCategory>();

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

	public void addCourseWorkCategory(CourseWorkCategory category) {
		courseWorkCategories.add(category);
	}
	
	
	public List<CourseWorkCategory> getCourseWorkCategories() {
		return courseWorkCategories;
	}

	public void setCourseWorks(List<CourseWorkCategory> categories) {
		this.courseWorkCategories = categories;
	}

	public CourseDTO toCourseDetails() {
		CourseDTO details = new CourseDTO();

		details.setCourseId(courseId);
		details.setCourseName(courseName);
		return details;
	}

	public static Course fromCourseDetails(CourseDTO courseDetails) {
		Course course = new Course();

		course.setCourseId(courseDetails.getCourseId());

//		courseWork.add(linkTo(OrderQueriesController.class).slash(courseWork.getCourseWorkId()).withSelfRel());

		return course;
	}
	
}
