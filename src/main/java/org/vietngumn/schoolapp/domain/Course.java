package org.vietngumn.schoolapp.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.vietngumn.schoolapp.event.course.CourseDTO;
import org.vietngumn.schoolapp.helper.ListItemsCrudHelper;

@Document
public class Course extends AbstractDocument {
	
	private String courseId;
	private String courseName;
	private Map<String, StudentCourseRecord> studentRecords;
	
	@Transient
	private ListItemsCrudHelper<CourseWorkCategory> categoriesListCrudHelper;
	private List<CourseWorkCategory> courseWorkCategories = new ArrayList<CourseWorkCategory>();
	
	public Course(String courseId) {
		this.courseId = courseId;
	}
	
	public Course() {
		
	}
	
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseId() {
		return this.courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	private ListItemsCrudHelper<CourseWorkCategory> getCategoriesListCrudHelper() {
		if (this.categoriesListCrudHelper == null) {
			if (this.courseWorkCategories == null) {
				this.courseWorkCategories = new ArrayList<CourseWorkCategory>();
			}
			this.categoriesListCrudHelper = new ListItemsCrudHelper<CourseWorkCategory>(this.courseWorkCategories);
		}
		return this.categoriesListCrudHelper;
	}
	
	public CourseWorkCategory getCourseWorkCategory(String categoryId) {
		return getCategoriesListCrudHelper().getItem(categoryId);
	}
	
	public void addCourseWorkCategory(CourseWorkCategory category) {
		this.getCategoriesListCrudHelper().addItem(category);
	}
	
	public CourseWorkCategory updateCourseWorkCategory(CourseWorkCategory category) {
		return this.getCategoriesListCrudHelper().replaceItem(category);
	}
	
	public CourseWorkCategory deleteCourseWorkCategory(String categoryId) {
		return this.getCategoriesListCrudHelper().deleteItem(categoryId);
	}
	
	public List<CourseWorkCategory> getCourseWorkCategories() {
		return Collections.unmodifiableList(this.courseWorkCategories);
	}
	
	public boolean addStudentRecord(StudentCourseRecord studentRecord) {
//		if (studentRecord == null || !studentRecord.getCourseId().equals(this.courseId) || studentRecords.containsKey(studentRecord.getStudentId())) {
//			return false;
//		}
		
		studentRecords.put(studentRecord.getStudentId(), studentRecord);
		return true;
	}
	
	public CourseDTO toCourseDTO() {
		CourseDTO details = new CourseDTO();
		details.setCourseId(this.courseId);
		details.setCourseName(this.courseName);
		
		if (this.courseWorkCategories != null) {
			for (CourseWorkCategory category : this.courseWorkCategories) {
				details.addCourseWorkCategory(category.toCourseWorkCategoryDTO());
			}
		}
		return details;
	}
}
