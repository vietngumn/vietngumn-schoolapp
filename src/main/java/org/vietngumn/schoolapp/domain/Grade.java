package org.vietngumn.schoolapp.domain;



public class Grade {

	private String gradeId;
	private CourseWork courseWork;
	private StudentCourseRecord student;
	
	public Grade(String gradeId) {
		this.gradeId = gradeId;
	}

	public String getGradeId() {
		return gradeId;
	}
	
	public CourseWork getCourseWork() {
		return courseWork;
	}

	public void setCourseWork(CourseWork courseWork) {
		this.courseWork = courseWork;
	}

	public StudentCourseRecord getStudent() {
		return student;
	}

	public void setStudent(StudentCourseRecord student) {
		this.student = student;
	}

}
