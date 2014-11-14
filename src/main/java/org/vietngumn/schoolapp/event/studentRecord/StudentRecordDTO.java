package org.vietngumn.schoolapp.event.studentRecord;

import java.util.ArrayList;
import java.util.List;

import org.vietngumn.schoolapp.event.studentGrade.StudentGradeDTO;


public class StudentRecordDTO {
	private StudentRecordIdPath idPath;
	private String studentId;
	private String name;
	private String description;
	private List<StudentGradeDTO> studentGrades = new ArrayList<StudentGradeDTO>();

	public StudentRecordIdPath getIdPath() {
		return idPath;
	}

	public void setIdPath(StudentRecordIdPath idPath) {
		this.idPath = idPath;
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

	public void addStudentGrade(StudentGradeDTO studentGrade) {
		this.studentGrades.add(studentGrade);
	}

	public List<StudentGradeDTO> getStudentGrades() {
		return studentGrades;
	}
	
}
