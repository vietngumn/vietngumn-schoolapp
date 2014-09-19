package org.vietngumn.schoolapp.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;
import org.vietngumn.schoolapp.event.course.CourseDTO;

@Document
public class Course extends AbstractDocument {
	
	private String courseId;
	private String courseName;
	private Map<String, StudentCourseRecord> studentRecords;
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
	
	public CourseWorkCategory getCourseWorkCategory(String categoryId) {
		for (CourseWorkCategory category : this.courseWorkCategories) {
			if (category.getCategoryId().equalsIgnoreCase(categoryId)) {
				return category;
			}
		}
		return null;
	}
	
	public void addCourseWorkCategory(CourseWorkCategory category) {
		this.courseWorkCategories.add(category);
	}
	
	public CourseWorkCategory updateCourseWorkCategory(CourseWorkCategory category) {
		if (this.findAndReplaceCourseWorkCategory(this.courseWorkCategories, category)) {
			return category;
		}
		return null;
	}
	
	private boolean findAndReplaceCourseWorkCategory(List<CourseWorkCategory> categories, CourseWorkCategory categoryToReplace) {
		for (int i = 0; i < categories.size(); i++) {
			CourseWorkCategory category = categories.get(i);
			if (category.getCategoryId().equalsIgnoreCase(categoryToReplace.getCategoryId())) {
				categories.set(i, categoryToReplace);
				return true;
			}
		}
		return false;
	}

	public CourseWorkCategory deleteCourseWorkCategory(String categoryId) {
		CourseWorkCategory categoryToBeDeleted = null;
		for (CourseWorkCategory category : this.courseWorkCategories) {
			if (category.getCategoryId().equalsIgnoreCase(categoryId)) {
				categoryToBeDeleted = category;
				break;
			}
		}
		
		if (categoryToBeDeleted != null) {
			this.courseWorkCategories.remove(categoryToBeDeleted);
		}
		return categoryToBeDeleted;
	}
	
	public List<CourseWorkCategory> getCourseWorkCategories() {
		return this.courseWorkCategories;
//		return Collections.unmodifiableList(this.courseWorkCategories);
	}
	
	public void setCourseWorkCategories(List<CourseWorkCategory> courseWorkCategories) {
		this.courseWorkCategories = courseWorkCategories;
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
