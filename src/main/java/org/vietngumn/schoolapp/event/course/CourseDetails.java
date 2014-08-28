package org.vietngumn.schoolapp.event.course;


public class CourseDetails {

	private String courseId;
	private String courseName;
	private String courseLevel;
	private String courseSection;
	private String schoolYear;
	
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
	
	public String getCourseLevel() {
		return courseLevel;
	}
	
	public String getCourseSection() {
		return courseSection;
	}

	public void setCourseSection(String courseSection) {
		this.courseSection = courseSection;
	}

	public void setCourseLevel(String courseLevel) {
		this.courseLevel = courseLevel;
	}
	
	public String getSchoolYear() {
		return schoolYear;
	}
	
	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}
	
}
