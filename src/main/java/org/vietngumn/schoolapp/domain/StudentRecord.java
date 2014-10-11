package org.vietngumn.schoolapp.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Transient;
import org.vietngumn.schoolapp.event.studentRecord.StudentRecordDTO;
import org.vietngumn.schoolapp.event.studentRecord.StudentRecordIdPath;
import org.vietngumn.schoolapp.helper.ListItem;
import org.vietngumn.schoolapp.helper.ListItemsCrudHelper;

public class StudentRecord implements ListItem {
	
	private String studentId;
	private String name;
	private String description;
	
	@Transient
	private ListItemsCrudHelper<StudentGrade> studentGradesCrudHelper;
	private List<StudentGrade> studentGrades = new ArrayList<StudentGrade>();
	
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
	
	private ListItemsCrudHelper<StudentGrade> getStudentGradesCrudHelper() {
		if (this.studentGradesCrudHelper == null) {
			if (this.studentGrades == null) {
				this.studentGrades = new ArrayList<StudentGrade>();
			}
			this.studentGradesCrudHelper = new ListItemsCrudHelper<StudentGrade>(this.studentGrades);
		}
		return this.studentGradesCrudHelper;
	}
	
	public StudentGrade getStudentGrade(final String categoryId, final String workId) {
		String itemId = StudentGrade.createListItemId(categoryId, workId);
		return getStudentGradesCrudHelper().getItem(itemId);
	}
	
	public StudentGrade addStudentGrade(StudentGrade work) {
		return this.getStudentGradesCrudHelper().addItem(work);
	}
	
	public StudentGrade updateStudentGrade(StudentGrade work) {
		return this.getStudentGradesCrudHelper().replaceItem(work);
	}
	
	public StudentGrade deleteStudentGrade(final String categoryId, final String workId) {
		String itemId = StudentGrade.createListItemId(categoryId, workId);
		return this.getStudentGradesCrudHelper().deleteItem(itemId);
	}
	
	public List<StudentGrade> getStudentGrades() {
		return Collections.unmodifiableList(this.studentGrades);
	}

	public StudentRecordDTO toStudentRecordDTO(final StudentRecordIdPath idPath) {
		StudentRecordDTO recordDTO = new StudentRecordDTO();
		recordDTO.setIdPath(idPath);
		BeanUtils.copyProperties(this, recordDTO);
		return recordDTO;
	}

	public static StudentRecord fromStudentRecordDTO(StudentRecordDTO recordDTO) {
		StudentRecord record = new StudentRecord();
		BeanUtils.copyProperties(recordDTO, record);
		return record;
	}
	
}
