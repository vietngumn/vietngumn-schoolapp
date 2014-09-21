package org.vietngumn.schoolapp.domain;

import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Transient;
import org.vietngumn.schoolapp.event.studentRecord.StudentRecordDTO;
import org.vietngumn.schoolapp.helper.ListItem;

public class StudentRecord implements ListItem {
	
	@Transient
	private Course course;
	private String courseId;
	
	@Transient
	private Student student;
	private String studentId;
	
	private String name;
	private String description;
	
	public String getCourseId() {
		return this.courseId;
	}
	
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	
	public String getStudentId() {
		return studentId;
	}
	
	@Override
	public String getListItemId() {
		return getStudentId();
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
		BeanUtils.copyProperties(this, recordDTO);
		return recordDTO;
	}

	public static StudentRecord fromStudentRecordDTO(StudentRecordDTO recordDTO) {
		StudentRecord record = new StudentRecord();
		BeanUtils.copyProperties(recordDTO, record);
		return record;
	}
	
}
