package org.vietngumn.schoolapp.domain;

import org.springframework.beans.BeanUtils;
import org.vietngumn.schoolapp.event.studentGrade.StudentGradeDTO;
import org.vietngumn.schoolapp.event.studentGrade.StudentGradeIdPath;
import org.vietngumn.schoolapp.helper.ListItem;

public class StudentGrade implements ListItem {

	private String categoryId;
	private String workId;
	private String points;
	private String comment;

	@Override
	public String getListItemId() {
		return createListItemId(getCategoryId(), getWorkId());
	}
	
	static public String createListItemId(final String categoryId, final String workId) {
		return categoryId + workId;
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

	public StudentGradeDTO toStudentGradeDTO(final StudentGradeIdPath idPath) {
		StudentGradeDTO gradeDTO = new StudentGradeDTO();
		gradeDTO.setIdPath(idPath);
		BeanUtils.copyProperties(this, gradeDTO);
		return gradeDTO;
	}

	public static StudentGrade fromStudentGradeDTO(final StudentGradeDTO gradeDTO) {
		StudentGrade grade = new StudentGrade();
		BeanUtils.copyProperties(gradeDTO, grade);
		return grade;
	}

}
