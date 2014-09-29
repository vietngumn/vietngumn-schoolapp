package org.vietngumn.schoolapp.event.course;

import java.util.ArrayList;
import java.util.List;

import org.vietngumn.schoolapp.event.courseWorkCategory.CourseWorkCategoryDTO;


public class CourseDTO {

	private String courseId;
	private String courseName;
	private String courseLevel;
	private String courseSection;
	private String schoolYearId;
	private String description;
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

	public void addCourseWorkCategory(CourseWorkCategoryDTO details) {
		courseWorkCategories.add(details);
	}

	public List<CourseWorkCategoryDTO> getCourseWorkCategories() {
		return courseWorkCategories;
	}
	
}
