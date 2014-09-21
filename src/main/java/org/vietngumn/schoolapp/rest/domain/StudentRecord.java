package org.vietngumn.schoolapp.rest.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.hateoas.ResourceSupport;
import org.vietngumn.schoolapp.event.studentRecord.StudentRecordDTO;


@XmlRootElement
public class StudentRecord extends ResourceSupport implements Serializable {
	private static final long serialVersionUID = 1L;

	private String courseId;
	private String studentId;
	private String name;
	private String description;

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
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

	public StudentRecordDTO toStudentRecordDTO() {
		StudentRecordDTO recordDTO = new StudentRecordDTO();
		recordDTO.setCourseId(courseId);
		recordDTO.setStudentId(studentId);
		recordDTO.setName(name);
		recordDTO.setDescription(description);
		return recordDTO;
	}

	public static StudentRecord fromStudentRecordDTO(StudentRecordDTO recordDTO) {
		StudentRecord record = new StudentRecord();
		record.setStudentId(recordDTO.getStudentId());
		record.setName(recordDTO.getName());
		record.setDescription(recordDTO.getDescription());
//		courseWork.add(linkTo(OrderQueriesController.class).slash(courseWork.getCourseWorkId()).withSelfRel());

		return record;
	}
}
