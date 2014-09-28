package org.vietngumn.schoolapp.event.studentGrade;


public class StudentGradeIdPath {

	private String courseId;
	private String studentId;
	private String categoryId;
	private String workId;
	
	public StudentGradeIdPath(final String courseId, final String studentId, final String categoryId, final String workId) {
		this.courseId = courseId;
		this.studentId = studentId;
		this.categoryId = categoryId;
		this.workId = workId;
	}

	public String getCourseId() {
		return this.courseId;
	}

	public String getStudentId() {
		return this.studentId;
	}

	public String getCategoryId() {
		return this.categoryId;
	}

	public String getWorkId() {
		return this.workId;
	}

}
