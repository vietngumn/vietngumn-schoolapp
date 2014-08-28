package org.vietngumn.schoolapp.domain;

import org.springframework.beans.BeanUtils;
import org.vietngumn.schoolapp.event.courseWork.CourseWorkDetails;

public class CourseWork {

	private String courseWorkId;
	private Course course;
	private String name;
	private String description;
	private String gradeType; //enum type: point, letter grade
	private String category; //enum type: exam, homework, attendance, extra credit
	

	public CourseWork(String courseWorkId) {
		this.courseWorkId = courseWorkId;
	}

	public String getCourseWorkId() {
		return this.courseWorkId;
	}

	public CourseWorkDetails toCourseWorkDetails() {
		CourseWorkDetails details = new CourseWorkDetails();
		BeanUtils.copyProperties(this, details);
		return details;
	}

	public static CourseWork fromCourseWorkDetails(CourseWorkDetails courseWorkDetails) {
		CourseWork courseWork = new CourseWork(courseWorkDetails.getCourseWorkId());

		BeanUtils.copyProperties(courseWorkDetails, courseWork);

		return courseWork;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public boolean canBeDeleted() {
		return true;
	}

}
