package org.vietngumn.schoolapp.rest.domain;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.hateoas.ResourceSupport;
import org.vietngumn.schoolapp.event.studentRecord.StudentRecordDTO;
import org.vietngumn.schoolapp.event.studentRecord.StudentRecordIdPath;
import org.vietngumn.schoolapp.rest.controller.CourseQueriesController;
import org.vietngumn.schoolapp.rest.controller.CourseWorkCategoryQueriesController;
import org.vietngumn.schoolapp.rest.controller.StudentRecordQueriesController;


@XmlRootElement
public class StudentRecord extends ResourceSupport implements Serializable {
	private static final long serialVersionUID = 1L;

	private String studentId;
	private String studentName;
	private String description;
	private List<StudentGrade> studentGrades = new ArrayList<StudentGrade>();

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<StudentGrade> getStudentGrades() {
		return studentGrades;
	}

	public void setStudentGrades(List<StudentGrade> studentGrades) {
		this.studentGrades = studentGrades;
	}

	public StudentRecordDTO toStudentRecordDTO(final StudentRecordIdPath idPath) {
		StudentRecordDTO recordDTO = new StudentRecordDTO();
		recordDTO.setIdPath(idPath);
		recordDTO.setStudentId(idPath.getStudentId());
		recordDTO.setName(studentName);
		recordDTO.setDescription(description);
		return recordDTO;
	}

	public static StudentRecord fromStudentRecordDTO(StudentRecordDTO dto) {
		StudentRecord record = new StudentRecord();
		record.setStudentId(dto.getStudentId());
		record.setStudentName(dto.getName());
		record.setDescription(dto.getDescription());
		
		StudentRecordIdPath idPath = dto.getIdPath();
		record.add(linkTo(StudentRecordQueriesController.class, idPath.getCourseId()).slash(idPath.getStudentId()).withSelfRel());
		record.add(linkTo(CourseQueriesController.class).slash(idPath.getCourseId()).withRel("Course"));
		record.add(linkTo(CourseWorkCategoryQueriesController.class, idPath.getCourseId()).withRel("StudentGrades"));
		return record;
	}
	
	public static StudentRecord fromQueriedStudentRecordDTO(StudentRecordDTO dto) {
		StudentRecord record = new StudentRecord();
		record.setStudentId(dto.getStudentId());
		record.setStudentName(dto.getName());
		record.setDescription(dto.getDescription());
		
		StudentRecordIdPath idPath = dto.getIdPath();
		record.add(linkTo(StudentRecordQueriesController.class, idPath.getCourseId()).slash(idPath.getStudentId()).withSelfRel());
		return record;
	}
	
}
