package org.vietngumn.schoolapp.service;

import org.vietngumn.schoolapp.event.studentRecord.CreateStudentRecordCommand;
import org.vietngumn.schoolapp.event.studentRecord.CreatedStudentRecord;
import org.vietngumn.schoolapp.event.studentRecord.DeleteStudentRecordCommand;
import org.vietngumn.schoolapp.event.studentRecord.DeletedStudentRecord;
import org.vietngumn.schoolapp.event.studentRecord.QueriedStudentRecords;
import org.vietngumn.schoolapp.event.studentRecord.QueryStudentRecordsCommand;
import org.vietngumn.schoolapp.event.studentRecord.ReadStudentRecord;
import org.vietngumn.schoolapp.event.studentRecord.ReadStudentRecordCommand;
import org.vietngumn.schoolapp.event.studentRecord.UpdateStudentRecordCommand;
import org.vietngumn.schoolapp.event.studentRecord.UpdatedStudentRecord;

public interface StudentRecordService {

	CreatedStudentRecord createStudentRecord(CreateStudentRecordCommand createCommand);

	ReadStudentRecord readStudentRecord(ReadStudentRecordCommand readCommand);

	UpdatedStudentRecord updateStudentRecord(UpdateStudentRecordCommand updateCommand);

	DeletedStudentRecord deleteStudentRecord(DeleteStudentRecordCommand deleteCommand);

	QueriedStudentRecords queryStudentRecords(QueryStudentRecordsCommand queryCommand);
}
