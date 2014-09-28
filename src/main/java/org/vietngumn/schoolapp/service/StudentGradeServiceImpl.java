package org.vietngumn.schoolapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vietngumn.schoolapp.domain.Course;
import org.vietngumn.schoolapp.domain.StudentGrade;
import org.vietngumn.schoolapp.domain.StudentRecord;
import org.vietngumn.schoolapp.event.studentGrade.CreateStudentGradeCommand;
import org.vietngumn.schoolapp.event.studentGrade.CreatedStudentGrade;
import org.vietngumn.schoolapp.event.studentGrade.DeleteStudentGradeCommand;
import org.vietngumn.schoolapp.event.studentGrade.DeletedStudentGrade;
import org.vietngumn.schoolapp.event.studentGrade.ReadStudentGrade;
import org.vietngumn.schoolapp.event.studentGrade.ReadStudentGradeCommand;
import org.vietngumn.schoolapp.event.studentGrade.StudentGradeDTO;
import org.vietngumn.schoolapp.event.studentGrade.StudentGradeIdPath;
import org.vietngumn.schoolapp.event.studentGrade.UpdateStudentGradeCommand;
import org.vietngumn.schoolapp.event.studentGrade.UpdatedStudentGrade;
import org.vietngumn.schoolapp.repository.CourseRepository;

@Service
public class StudentGradeServiceImpl implements StudentGradeService {
	
	private CourseRepository courseRepository;
	
	@Autowired
	public StudentGradeServiceImpl(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}
	
	@Override
	public CreatedStudentGrade createStudentGrade(CreateStudentGradeCommand createCommand) {
		StudentGradeIdPath studentGradeId = createCommand.getStudentGradeId();
		StudentGradeDTO gradeDTO = createCommand.getDetails();
		
		Course course = courseRepository.findByCourseId(studentGradeId.getCourseId());
		
		StudentRecord record = course.getStudentRecord(studentGradeId.getStudentId());
		
		StudentGrade grade = StudentGrade.fromStudentGradeDTO(gradeDTO);
		
		StudentGrade addedGrade = record.addStudentGrade(grade);
//		if (addedGrade == null) {
//		return UpdatedStudentGrade.notFound(studentGradeId);
//	}
		
		course = courseRepository.save(course);
		
		StudentGradeIdPath newCreatedId = new StudentGradeIdPath(
				studentGradeId.getCourseId(), studentGradeId.getStudentId(), addedGrade.getCategoryId(), addedGrade.getWorkId());
		return new CreatedStudentGrade(newCreatedId, grade.toStudentGradeDTO());
	}
	
	@Override
	public ReadStudentGrade readStudentGrade(ReadStudentGradeCommand readCommand) {
		StudentGradeIdPath studentGradeId = readCommand.getStudentGradeId();
		
		Course course = courseRepository.findByCourseId(studentGradeId.getCourseId());
		if (course == null) {
			return ReadStudentGrade.notFound(studentGradeId);
		}

		StudentRecord record = course.getStudentRecord(studentGradeId.getStudentId());
		if (record == null) {
			return ReadStudentGrade.notFound(studentGradeId);
		}
		
		StudentGrade grade = record.getStudentGrade(studentGradeId.getCategoryId(), studentGradeId.getWorkId());
		if (grade == null) {
			return ReadStudentGrade.notFound(studentGradeId);
		}
		
		return new ReadStudentGrade(studentGradeId, grade.toStudentGradeDTO());
	}


	@Override
	public UpdatedStudentGrade updateStudentGrade(UpdateStudentGradeCommand updateCommand) {
		StudentGradeIdPath studentGradeId = updateCommand.getStudentGradeId();
		StudentGradeDTO gradeDTO = updateCommand.getDetails();
		
		Course course = courseRepository.findByCourseId(studentGradeId.getCourseId());
		
		StudentRecord record = course.getStudentRecord(studentGradeId.getStudentId());
		
		StudentGrade grade = StudentGrade.fromStudentGradeDTO(gradeDTO);
		
		StudentGrade updatedGrade = record.updateStudentGrade(grade);
//		if (updatedGrade == null) {
//			return UpdatedStudentGrade.notFound(studentGradeId);
//		}
		
		course = courseRepository.save(course);
		
		return new UpdatedStudentGrade(studentGradeId, updatedGrade.toStudentGradeDTO());
	}

	@Override
	public DeletedStudentGrade deleteStudentGrade(DeleteStudentGradeCommand deleteCommand) {
		StudentGradeIdPath studentGradeId = deleteCommand.getStudentGradeId();
		
		Course course = courseRepository.findByCourseId(studentGradeId.getCourseId());
		if (course == null) {
			return DeletedStudentGrade.notFound(studentGradeId);
		}

		StudentRecord record = course.getStudentRecord(studentGradeId.getStudentId());
		if (record == null) {
			return DeletedStudentGrade.notFound(studentGradeId);
		}
		
		StudentGrade grade = record.deleteStudentGrade(studentGradeId.getCategoryId(), studentGradeId.getWorkId());
		if (grade == null) {
			return DeletedStudentGrade.notFound(studentGradeId);
		}
		
		courseRepository.save(course);
		
		StudentGradeDTO details = grade.toStudentGradeDTO();
		return new DeletedStudentGrade(studentGradeId, details);
	}
}
