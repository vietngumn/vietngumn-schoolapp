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
		StudentGradeIdPath gradeIdPath = createCommand.getGradeIdPath();
		StudentGradeDTO gradeDTO = createCommand.getDetails();
		
		Course course = courseRepository.findByCourseId(gradeIdPath.getCourseId());
		
		StudentRecord record = course.getStudentRecord(gradeIdPath.getStudentId());
		
		StudentGrade grade = StudentGrade.fromStudentGradeDTO(gradeDTO);
		
		StudentGrade addedGrade = record.addStudentGrade(grade);
//		if (addedGrade == null) {
//			return CreateStudentGradeCommand.existing(gradeIdPath);
//		}
		
		course = courseRepository.save(course);
		
		StudentGradeIdPath newCreatedId = new StudentGradeIdPath(
				gradeIdPath.getCourseId(), gradeIdPath.getStudentId(), addedGrade.getCategoryId(), addedGrade.getWorkId());
		return new CreatedStudentGrade(newCreatedId, grade.toStudentGradeDTO());
	}
	
	@Override
	public ReadStudentGrade readStudentGrade(ReadStudentGradeCommand readCommand) {
		StudentGradeIdPath gradeIdPath = readCommand.getGradeIdPath();
		
		Course course = courseRepository.findByCourseId(gradeIdPath.getCourseId());
		if (course == null) {
			return ReadStudentGrade.notFound(gradeIdPath);
		}

		StudentRecord record = course.getStudentRecord(gradeIdPath.getStudentId());
		if (record == null) {
			return ReadStudentGrade.notFound(gradeIdPath);
		}
		
		StudentGrade grade = record.getStudentGrade(gradeIdPath.getCategoryId(), gradeIdPath.getWorkId());
		if (grade == null) {
			return ReadStudentGrade.notFound(gradeIdPath);
		}
		
		return new ReadStudentGrade(gradeIdPath, grade.toStudentGradeDTO());
	}


	@Override
	public UpdatedStudentGrade updateStudentGrade(UpdateStudentGradeCommand updateCommand) {
		StudentGradeIdPath gradeIdPath = updateCommand.getGradeIdPath();
		StudentGradeDTO gradeDTO = updateCommand.getDetails();
		
		Course course = courseRepository.findByCourseId(gradeIdPath.getCourseId());
		
		StudentRecord record = course.getStudentRecord(gradeIdPath.getStudentId());
		
		StudentGrade grade = StudentGrade.fromStudentGradeDTO(gradeDTO);
		
		StudentGrade updatedGrade = record.updateStudentGrade(grade);
//		if (updatedGrade == null) {
//			return UpdatedStudentGrade.notFound(gradeIdPath);
//		}
		
		course = courseRepository.save(course);
		
		return new UpdatedStudentGrade(gradeIdPath, updatedGrade.toStudentGradeDTO());
	}

	@Override
	public DeletedStudentGrade deleteStudentGrade(DeleteStudentGradeCommand deleteCommand) {
		StudentGradeIdPath gradeIdPath = deleteCommand.getGradeIdPath();
		
		Course course = courseRepository.findByCourseId(gradeIdPath.getCourseId());
		if (course == null) {
			return DeletedStudentGrade.notFound(gradeIdPath);
		}

		StudentRecord record = course.getStudentRecord(gradeIdPath.getStudentId());
		if (record == null) {
			return DeletedStudentGrade.notFound(gradeIdPath);
		}
		
		StudentGrade grade = record.deleteStudentGrade(gradeIdPath.getCategoryId(), gradeIdPath.getWorkId());
		if (grade == null) {
			return DeletedStudentGrade.notFound(gradeIdPath);
		}
		
		courseRepository.save(course);
		
		StudentGradeDTO details = grade.toStudentGradeDTO();
		return new DeletedStudentGrade(gradeIdPath, details);
	}
}
