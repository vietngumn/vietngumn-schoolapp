package org.vietngumn.schoolapp.event.course;

import java.util.ArrayList;
import java.util.List;

import org.vietngumn.schoolapp.event.courseWorkCategory.CourseWorkCategoryDTO;


public class CourseDTO {

	private String courseId;
	private String courseName;
	private String courseLevel;
	private String courseSection;
	private String schoolYear;
	private List<CourseWorkCategoryDTO> courseWorkCategories = new ArrayList<CourseWorkCategoryDTO>();
	
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
	
	public void addCourseWorkCategory(CourseWorkCategoryDTO details) {
		courseWorkCategories.add(details);
	}

	public List<CourseWorkCategoryDTO> getCourseWorkCategories() {
		return courseWorkCategories;
	}
	
}
