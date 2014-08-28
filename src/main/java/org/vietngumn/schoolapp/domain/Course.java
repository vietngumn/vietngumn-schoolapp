package org.vietngumn.schoolapp.domain;

import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;
import org.vietngumn.schoolapp.event.course.CourseDetails;

@Document
public class Course extends AbstractDocument {

	private String courseId;
	private String courseName;
	private Map<String, StudentCourseRecord> studentRecords;
//	private Map<String, CourseWork> courseWorks;

	public Course(String courseId) {
		this.courseId = courseId;
	}
	
	public Course() {
		
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseId() {
		return this.courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public boolean addStudentRecord(StudentCourseRecord studentRecord) {
//		if (studentRecord == null || !studentRecord.getCourseId().equals(this.courseId) || studentRecords.containsKey(studentRecord.getStudentId())) {
//			return false;
//		}
		
		studentRecords.put(studentRecord.getStudentId(), studentRecord);
		return true;
	}
	
	public CourseDetails toCourseDetails() {
		CourseDetails details = new CourseDetails();
		details.setCourseId(this.courseId);
		details.setCourseName(this.courseName);
		return details;
	}

}
