package org.vietngumn.schoolapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vietngumn.schoolapp.domain.Course;
import org.vietngumn.schoolapp.domain.StudentRecord;
import org.vietngumn.schoolapp.event.studentRecord.CreateStudentRecordCommand;
import org.vietngumn.schoolapp.event.studentRecord.CreatedStudentRecord;
import org.vietngumn.schoolapp.event.studentRecord.DeleteStudentRecordCommand;
import org.vietngumn.schoolapp.event.studentRecord.DeletedStudentRecord;
import org.vietngumn.schoolapp.event.studentRecord.ReadStudentRecord;
import org.vietngumn.schoolapp.event.studentRecord.ReadStudentRecordCommand;
import org.vietngumn.schoolapp.event.studentRecord.StudentRecordDTO;
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
		
		Course course = courseRepository.findByCourseId(recordDTO.getCourseId());
		
		StudentRecord record = StudentRecord.fromStudentRecordDTO(recordDTO);
		course.addStudentRecord(record);
		
		course = courseRepository.save(course);
		return new CreatedStudentRecord(record.getStudentId(), record.toStudentRecordDTO());
	}
	
	@Override
	public ReadStudentRecord readStudentRecord(ReadStudentRecordCommand readCommand) {

		Course course = courseRepository.findByCourseId(readCommand.getCourseId());
		if (course == null) {
			return ReadStudentRecord.notFound(readCommand.getCourseId(), readCommand.getStudentId());
		}

		StudentRecord record = course.getStudentRecord(readCommand.getStudentId());
		if (record == null) {
			return ReadStudentRecord.notFound(readCommand.getCourseId(), readCommand.getStudentId());
		}
		
		return new ReadStudentRecord(readCommand.getCourseId(), readCommand.getStudentId(), record.toStudentRecordDTO());
	}


	@Override
	public UpdatedStudentRecord updateStudentRecord(UpdateStudentRecordCommand updateCommand) {
		StudentRecordDTO categoryDTO = updateCommand.getDetails();
		Course course = courseRepository.findByCourseId(categoryDTO.getCourseId());
		
		StudentRecord record = StudentRecord.fromStudentRecordDTO(categoryDTO);
		record.setStudentId(updateCommand.getStudentId());
		
		StudentRecord updatedRecord = course.updateStudentRecord(record);
		
		course = courseRepository.save(course);
		return new UpdatedStudentRecord(updateCommand.getStudentId(), updatedRecord.toStudentRecordDTO());
	}

	@Override
	public DeletedStudentRecord deleteStudentRecord(DeleteStudentRecordCommand deleteCommand) {
		Course course = courseRepository.findByCourseId(deleteCommand.getCourseId());
		if (course == null) {
			return DeletedStudentRecord.notFound(deleteCommand.getCourseId(), deleteCommand.getStudentId());
		}

		StudentRecord category = course.deleteStudentRecord(deleteCommand.getStudentId());
		if (category == null) {
			return DeletedStudentRecord.notFound(deleteCommand.getCourseId(), deleteCommand.getStudentId());
		}
		
		StudentRecordDTO details = category.toStudentRecordDTO();
		courseRepository.save(course);
		return new DeletedStudentRecord(deleteCommand.getCourseId(), deleteCommand.getStudentId(), details);
	}
}
