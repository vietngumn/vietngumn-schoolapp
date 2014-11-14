package org.vietngumn.schoolapp.event.studentGrade;

import java.util.List;

import org.vietngumn.schoolapp.event.QueryCommand;

public class StudentGradeQueryCriteria extends QueryCommand {
	private String courseId;
	private List<String> workCategoryIds;
	private List<String> courseWorkIds;
	private List<String> studentIds;
	private List<String> schoolDateIds;
	
	private StudentGradeIdPath idPath;

	public StudentGradeIdPath getStudentGradeIdPath() {
		return idPath;
	}

	public void setStudentGradeIdPath(StudentGradeIdPath idPath) {
		this.idPath = idPath;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public List<String> getWorkCategoryIds() {
		return workCategoryIds;
	}

	public void setWorkCategoryIds(List<String> workCategoryIds) {
		this.workCategoryIds = workCategoryIds;
	}

	public List<String> getCourseWorkIds() {
		return courseWorkIds;
	}

	public void setCourseWorkIds(List<String> courseWorkIds) {
		this.courseWorkIds = courseWorkIds;
	}

	public List<String> getStudentIds() {
		return studentIds;
	}

	public void setStudentIds(List<String> studentIds) {
		this.studentIds = studentIds;
	}

	public List<String> getSchoolDateIds() {
		return schoolDateIds;
	}

	public void setSchoolDateIds(List<String> schoolDateIds) {
		this.schoolDateIds = schoolDateIds;
	}

}
