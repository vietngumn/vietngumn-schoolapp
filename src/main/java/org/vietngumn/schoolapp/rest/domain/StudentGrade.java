package org.vietngumn.schoolapp.rest.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.hateoas.ResourceSupport;
import org.vietngumn.schoolapp.event.studentGrade.StudentGradeDTO;


@XmlRootElement
public class StudentGrade extends ResourceSupport implements Serializable {
	private static final long serialVersionUID = 1L;

	private String categoryId;
	private String workId;
	private String points;
	private String comment;

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

	public StudentGradeDTO toStudentGradeDTO() {
		StudentGradeDTO gradeDTO = new StudentGradeDTO();
		gradeDTO.setCategoryId(this.categoryId);
		gradeDTO.setWorkId(this.workId);
		gradeDTO.setPoints(this.points);
		gradeDTO.setComment(this.comment);
		return gradeDTO;
	}

	public static StudentGrade fromStudentGradeDTO(final StudentGradeDTO gradeDTO) {
		StudentGrade grade = new StudentGrade();
		grade.setCategoryId(gradeDTO.getCategoryId());
		grade.setWorkId(gradeDTO.getWorkId());
		grade.setPoints(gradeDTO.getPoints());
		grade.setComment(gradeDTO.getComment());
//		courseWork.add(linkTo(OrderQueriesController.class).slash(courseWork.getCourseWorkId()).withSelfRel());
		return grade;
	}
}
