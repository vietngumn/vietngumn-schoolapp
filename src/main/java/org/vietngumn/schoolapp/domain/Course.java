package org.vietngumn.schoolapp.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.vietngumn.schoolapp.event.course.CourseDTO;
import org.vietngumn.schoolapp.helper.ListItemsCrudHelper;

@Document
public class Course extends AbstractDocument {
	
	private String courseId;
	private String courseName;
	
	@Transient
	private ListItemsCrudHelper<CourseWorkCategory> categoriesListHelper;
	private List<CourseWorkCategory> courseWorkCategories = new ArrayList<CourseWorkCategory>();
	
	@Transient
	private ListItemsCrudHelper<StudentRecord> recordsListHelper;
	private List<StudentRecord> studentRecords = new ArrayList<StudentRecord>();
	
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
	
	private ListItemsCrudHelper<CourseWorkCategory> getCategoriesListHelper() {
		if (this.categoriesListHelper == null) {
			if (this.courseWorkCategories == null) {
				this.courseWorkCategories = new ArrayList<CourseWorkCategory>();
			}
			this.categoriesListHelper = new ListItemsCrudHelper<CourseWorkCategory>(this.courseWorkCategories);
		}
		return this.categoriesListHelper;
	}
	
	public CourseWorkCategory getCourseWorkCategory(String categoryId) {
		return getCategoriesListHelper().getItem(categoryId);
	}
	
	public void addCourseWorkCategory(CourseWorkCategory category) {
		this.getCategoriesListHelper().addItem(category);
	}
	
	public CourseWorkCategory updateCourseWorkCategory(CourseWorkCategory category) {
		return this.getCategoriesListHelper().replaceItem(category);
	}
	
	public CourseWorkCategory deleteCourseWorkCategory(String categoryId) {
		return this.getCategoriesListHelper().deleteItem(categoryId);
	}
	
	public List<CourseWorkCategory> getCourseWorkCategories() {
		return Collections.unmodifiableList(this.courseWorkCategories);
	}
	
	private ListItemsCrudHelper<StudentRecord> getRecordsListHelper() {
		if (this.recordsListHelper == null) {
			if (this.studentRecords == null) {
				this.studentRecords = new ArrayList<StudentRecord>();
			}
			this.recordsListHelper = new ListItemsCrudHelper<StudentRecord>(this.studentRecords);
		}
		return this.recordsListHelper;
	}
	
	public StudentRecord getStudentRecord(String workId) {
		return getRecordsListHelper().getItem(workId);
	}
	
	public void addStudentRecord(StudentRecord record) {
		this.getRecordsListHelper().addItem(record);
	}
	
	public StudentRecord updateStudentRecord(StudentRecord record) {
		return this.getRecordsListHelper().replaceItem(record);
	}
	
	public StudentRecord deleteStudentRecord(String workId) {
		return this.getRecordsListHelper().deleteItem(workId);
	}
	
	public List<StudentRecord> getstudentRecords() {
		return Collections.unmodifiableList(this.studentRecords);
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
