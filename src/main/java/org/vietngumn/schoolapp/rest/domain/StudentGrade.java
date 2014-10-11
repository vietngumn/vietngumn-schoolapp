package org.vietngumn.schoolapp.rest.domain;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.hateoas.ResourceSupport;
import org.vietngumn.schoolapp.event.courseWork.CourseWorkDTO;
import org.vietngumn.schoolapp.event.courseWork.CourseWorkIdPath;
import org.vietngumn.schoolapp.event.studentGrade.StudentGradeDTO;
import org.vietngumn.schoolapp.event.studentGrade.StudentGradeIdPath;
import org.vietngumn.schoolapp.rest.controller.CourseQueriesController;
import org.vietngumn.schoolapp.rest.controller.CourseWorkCategoryQueriesController;
import org.vietngumn.schoolapp.rest.controller.CourseWorkQueriesController;
import org.vietngumn.schoolapp.rest.controller.StudentGradeQueriesController;
import org.vietngumn.schoolapp.rest.controller.StudentRecordQueriesController;


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

	public StudentGradeDTO toStudentGradeDTO(final StudentGradeIdPath idPath) {
		StudentGradeDTO gradeDTO = new StudentGradeDTO();
		gradeDTO.setIdPath(idPath);
		gradeDTO.setCategoryId(idPath.getCategoryId());
		gradeDTO.setWorkId(idPath.getWorkId());
		gradeDTO.setPoints(this.points);
		gradeDTO.setComment(this.comment);
		return gradeDTO;
	}

	public static StudentGrade fromStudentGradeDTO(final StudentGradeDTO dto) {
		StudentGrade grade = new StudentGrade();
		grade.setCategoryId(dto.getCategoryId());
		grade.setWorkId(dto.getWorkId());
		grade.setPoints(dto.getPoints());
		grade.setComment(dto.getComment());
		
		final StudentGradeIdPath idPath = dto.getIdPath();
		grade.add(linkTo(StudentGradeQueriesController.class, idPath.getCourseId(), idPath.getStudentId()).slash("categories").slash(idPath.getCategoryId()).slash("works").slash(idPath.getWorkId()).withSelfRel());
		grade.add(linkTo(StudentRecordQueriesController.class, idPath.getCourseId()).slash(idPath.getStudentId()).withRel("StudentRecords"));
		return grade;
	}
	
	public static StudentGrade fromQueriedStudentGradeDTO(final StudentGradeDTO dto) {
		StudentGrade grade = new StudentGrade();
		grade.setCategoryId(dto.getCategoryId());
		grade.setWorkId(dto.getWorkId());
		grade.setPoints(dto.getPoints());
		grade.setComment(dto.getComment());
		
		final StudentGradeIdPath idPath = dto.getIdPath();
		grade.add(linkTo(StudentGradeQueriesController.class, idPath.getCourseId(), idPath.getStudentId()).slash("categories").slash(idPath.getCategoryId()).slash("works").slash(idPath.getWorkId()).withSelfRel());
		return grade;
	}
	
}
