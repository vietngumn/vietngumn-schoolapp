package org.vietngumn.schoolapp.event.studentGrade;


public class StudentGradeDTO {
	private StudentGradeIdPath idPath;
	private String categoryId;
	private String workId;
	private String points;
	private String comment;

	public StudentGradeIdPath getIdPath() {
		return idPath;
	}

	public void setIdPath(StudentGradeIdPath idPath) {
		this.idPath = idPath;
	}
	
	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getWorkId() {
		return workId;
	}

	public void setWorkId(String workId) {
		this.workId = workId;
	}

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
