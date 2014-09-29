package org.vietngumn.schoolapp.rest.domain;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.hateoas.ResourceSupport;
import org.vietngumn.schoolapp.event.course.CourseDTO;
import org.vietngumn.schoolapp.rest.controller.CourseQueriesController;
import org.vietngumn.schoolapp.rest.controller.CourseWorkCategoryQueriesController;
import org.vietngumn.schoolapp.rest.controller.StudentRecordQueriesController;


@XmlRootElement
public class Course extends ResourceSupport implements Serializable {
	private static final long serialVersionUID = 1L;

	private String courseId;
	private String courseName;
	private String schoolYearId;
	private String description;

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

	public String getSchoolYearId() {
		return schoolYearId;
	}

	public void setSchoolYearId(String schoolYearId) {
		this.schoolYearId = schoolYearId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CourseDTO toCourseDetails() {
		CourseDTO details = new CourseDTO();
		details.setCourseId(this.courseId);
		details.setCourseName(this.courseName);
		details.setSchoolYearId(this.schoolYearId);
		details.setDescription(this.description);
		return details;
	}

	public static Course fromCourseDetails(CourseDTO courseDetails) {
		Course course = new Course();
		course.setCourseId(courseDetails.getCourseId());
		course.setCourseName(courseDetails.getCourseName());
		course.setSchoolYearId(courseDetails.getSchoolYearId());
		course.setDescription(courseDetails.getDescription());

		course.add(linkTo(CourseQueriesController.class).slash(course.getCourseId()).withSelfRel());
		course.add(linkTo(CourseWorkCategoryQueriesController.class, course.getCourseId()).withRel("Course WorkCategories"));
		course.add(linkTo(StudentRecordQueriesController.class, course.getCourseId()).withRel("Course StudentRecords"));
		return course;
	}
	
	public static Course fromQueriedCourseDTO(CourseDTO dto) {
		Course course = new Course();
		course.setCourseId(dto.getCourseId());
		course.setCourseName(dto.getCourseName());
		course.setSchoolYearId(dto.getSchoolYearId());
		course.setDescription(dto.getDescription());

		course.add(linkTo(CourseQueriesController.class).slash(course.getCourseId()).withSelfRel());
		return course;
	}
	
}
