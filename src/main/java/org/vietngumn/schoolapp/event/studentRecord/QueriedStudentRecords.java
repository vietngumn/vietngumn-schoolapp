package org.vietngumn.schoolapp.event.studentRecord;

import java.util.Collections;
import java.util.List;

import org.vietngumn.schoolapp.event.QueriedEvent;

public class QueriedStudentRecords extends QueriedEvent {
	private final List<StudentRecordDTO> records;

	public QueriedStudentRecords(List<StudentRecordDTO> records) {
		this.records = Collections.unmodifiableList(records);
	}

	public List<StudentRecordDTO> getStudentRecords() {
		return this.records;
	}
	
}
