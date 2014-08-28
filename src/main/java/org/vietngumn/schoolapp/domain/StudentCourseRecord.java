package org.vietngumn.schoolapp.domain;

import java.util.List;


public class StudentCourseRecord {
	
	private Course course;
	private Student student;
	
	private List<Grade> grades;

	public StudentCourseRecord(Student student, Course course) {
		this.student = student;
		this.course = course;
	}

	public String getStudentId() {
		return this.student.getStudentId();
	}

	public String getFirstName() {
		return this.student.getFirstName();
	}

	public String getLastName() {
		return this.student.getLastName();
	}

	public String getCourseId() {
		return this.course.getCourseId();
	}
	
	public Course getCourse() {
		return course;
	}

	public Student getStudent() {
		return student;
	}

}
