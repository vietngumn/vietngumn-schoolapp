package org.vietngumn.schoolapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vietngumn.schoolapp.domain.Course;
import org.vietngumn.schoolapp.domain.StudentRecord;
import org.vietngumn.schoolapp.event.studentRecord.CreateStudentRecordCommand;
import org.vietngumn.schoolapp.event.studentRecord.CreatedStudentRecord;
import org.vietngumn.schoolapp.event.studentRecord.DeleteStudentRecordCommand;
import org.vietngumn.schoolapp.event.studentRecord.DeletedStudentRecord;
import org.vietngumn.schoolapp.event.studentRecord.QueriedStudentRecords;
import org.vietngumn.schoolapp.event.studentRecord.QueryStudentRecordsCommand;
import org.vietngumn.schoolapp.event.studentRecord.ReadStudentRecord;
import org.vietngumn.schoolapp.event.studentRecord.ReadStudentRecordCommand;
import org.vietngumn.schoolapp.event.studentRecord.StudentRecordDTO;
import org.vietngumn.schoolapp.event.studentRecord.StudentRecordIdPath;
import org.vietngumn.schoolapp.event.studentRecord.StudentRecordQueryCriteria;
import org.vietngumn.schoolapp.event.studentRecord.UpdateStudentRecordCommand;
import org.vietngumn.schoolapp.event.studentRecord.UpdatedStudentRecord;
import org.vietngumn.schoolapp.repository.CourseRepository;

@Service
public class StudentRecordServiceImpl implements StudentRecordService {
	
	private CourseRepository courseRepository;
	
	@Autowired
	public StudentRecordServiceImpl(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}
	
	@Override
	public CreatedStudentRecord createStudentRecord(CreateStudentRecordCommand createCommand) {
		StudentRecordDTO recordDTO = createCommand.getDetails();
		StudentRecordIdPath recordIdPath = recordDTO.getIdPath();
		
		Course course = courseRepository.findByCourseId(recordIdPath.getCourseId());
		
		StudentRecord record = StudentRecord.fromStudentRecordDTO(recordDTO);
		
		StudentRecord addedRecord = course.addStudentRecord(record);
		
		courseRepository.save(course);
		
		StudentRecordIdPath newCreatedId = new StudentRecordIdPath(
				recordIdPath.getCourseId(), addedRecord.getStudentId());
		return new CreatedStudentRecord(record.toStudentRecordDTO(newCreatedId));
	}
	
	@Override
	public ReadStudentRecord readStudentRecord(ReadStudentRecordCommand readCommand) {
		StudentRecordIdPath recordIdPath = readCommand.getRecordIdPath();
		
		Course course = courseRepository.findByCourseId(recordIdPath.getCourseId());
		if (course == null) {
			return ReadStudentRecord.notFound(recordIdPath);
		}

		StudentRecord record = course.getStudentRecord(recordIdPath.getStudentId());
		if (record == null) {
			return ReadStudentRecord.notFound(recordIdPath);
		}
		
		return new ReadStudentRecord(record.toStudentRecordDTO(recordIdPath));
	}


	@Override
	public UpdatedStudentRecord updateStudentRecord(UpdateStudentRecordCommand updateCommand) {
		StudentRecordDTO categoryDTO = updateCommand.getDetails();
		StudentRecordIdPath recordIdPath = categoryDTO.getIdPath();
		
		Course course = courseRepository.findByCourseId(recordIdPath.getCourseId());
		
		StudentRecord record = StudentRecord.fromStudentRecordDTO(categoryDTO);
		
		StudentRecord updatedRecord = course.updateStudentRecord(record);
		
		course = courseRepository.save(course);
		return new UpdatedStudentRecord(updatedRecord.toStudentRecordDTO(recordIdPath));
	}

	@Override
	public DeletedStudentRecord deleteStudentRecord(DeleteStudentRecordCommand deleteCommand) {
		StudentRecordIdPath recordIdPath = deleteCommand.getRecordIdPath();
		Course course = courseRepository.findByCourseId(recordIdPath.getCourseId());
		if (course == null) {
			return DeletedStudentRecord.notFound(recordIdPath);
		}

		StudentRecord category = course.deleteStudentRecord(recordIdPath.getStudentId());
		if (category == null) {
			return DeletedStudentRecord.notFound(recordIdPath);
		}
		
		courseRepository.save(course);
		
		StudentRecordDTO details = category.toStudentRecordDTO(recordIdPath);
		return new DeletedStudentRecord(details);
	}
	
	@Override
	public QueriedStudentRecords queryStudentRecords(QueryStudentRecordsCommand queryCommand) {
		
		StudentRecordQueryCriteria criteria = queryCommand.getQueryCriteria();
		String courseId = criteria.getRecordIdPath().getCourseId();
		
		List<StudentRecordDTO> recordDTOs = new ArrayList<StudentRecordDTO>();

		Course course = courseRepository.findByCourseId(courseId);
		
		if (course != null) {
			for (StudentRecord record : course.getStudentRecords()) {
				StudentRecordIdPath recordIdPath = new StudentRecordIdPath(courseId, record.getStudentId());
				recordDTOs.add(record.toStudentRecordDTO(recordIdPath));
			}
		}
		
		return new QueriedStudentRecords(recordDTOs);
	}
}
