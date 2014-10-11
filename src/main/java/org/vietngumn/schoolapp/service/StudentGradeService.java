package org.vietngumn.schoolapp.service;

import org.vietngumn.schoolapp.event.studentGrade.CreateStudentGradeCommand;
import org.vietngumn.schoolapp.event.studentGrade.CreatedStudentGrade;
import org.vietngumn.schoolapp.event.studentGrade.DeleteStudentGradeCommand;
import org.vietngumn.schoolapp.event.studentGrade.DeletedStudentGrade;
import org.vietngumn.schoolapp.event.studentGrade.QueriedStudentGrades;
import org.vietngumn.schoolapp.event.studentGrade.QueryStudentGradesCommand;
import org.vietngumn.schoolapp.event.studentGrade.ReadStudentGrade;
import org.vietngumn.schoolapp.event.studentGrade.ReadStudentGradeCommand;
import org.vietngumn.schoolapp.event.studentGrade.UpdateStudentGradeCommand;
import org.vietngumn.schoolapp.event.studentGrade.UpdatedStudentGrade;

public interface StudentGradeService {

	CreatedStudentGrade createStudentGrade(CreateStudentGradeCommand createCommand);

	ReadStudentGrade readStudentGrade(ReadStudentGradeCommand readCommand);

	UpdatedStudentGrade updateStudentGrade(UpdateStudentGradeCommand updateCommand);

	DeletedStudentGrade deleteStudentGrade(DeleteStudentGradeCommand deleteCommand);

	QueriedStudentGrades queryCourseWorks(QueryStudentGradesCommand queryCommand);
}
