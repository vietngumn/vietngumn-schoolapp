package org.vietngumn.schoolapp.event.studentGrade;

import java.util.Collections;
import java.util.List;

import org.vietngumn.schoolapp.event.QueriedEvent;
import org.vietngumn.schoolapp.event.studentRecord.StudentRecordDTO;

public class QueriedStudentGrades extends QueriedEvent {
	private final List<StudentRecordDTO> recordGrades;

	public QueriedStudentGrades(List<StudentRecordDTO> recordGrades) {
		this.recordGrades = Collections.unmodifiableList(recordGrades);
	}

	public List<StudentRecordDTO> getStudentRecordGrades() {
		return this.recordGrades;
	}
	
}
