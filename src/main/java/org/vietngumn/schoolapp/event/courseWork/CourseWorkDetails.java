package org.vietngumn.schoolapp.event.courseWork;


public class CourseWorkDetails {

	private String courseWorkId;
	private String courseId;
	private String name;
	private String description;
	private String gradeType;
	private String category;

	public CourseWorkDetails() {
		courseWorkId = null;
	}

	public CourseWorkDetails(String courseWorkId) {
		this.courseWorkId = courseWorkId;
	}

	public String getCourseWorkId() {
		return courseWorkId;
	}

	public void setCourseWorkId(String courseWorkId) {
		this.courseWorkId = courseWorkId;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGradeType() {
		return gradeType;
	}

	public void setGradeType(String gradeType) {
		this.gradeType = gradeType;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
}
