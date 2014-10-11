package org.vietngumn.schoolapp.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vietngumn.schoolapp.domain.Course;
import org.vietngumn.schoolapp.domain.StudentGrade;
import org.vietngumn.schoolapp.domain.StudentRecord;
import org.vietngumn.schoolapp.event.studentGrade.CreateStudentGradeCommand;
import org.vietngumn.schoolapp.event.studentGrade.CreatedStudentGrade;
import org.vietngumn.schoolapp.event.studentGrade.DeleteStudentGradeCommand;
import org.vietngumn.schoolapp.event.studentGrade.DeletedStudentGrade;
import org.vietngumn.schoolapp.event.studentGrade.QueriedStudentGrades;
import org.vietngumn.schoolapp.event.studentGrade.QueryStudentGradesCommand;
import org.vietngumn.schoolapp.event.studentGrade.ReadStudentGrade;
import org.vietngumn.schoolapp.event.studentGrade.ReadStudentGradeCommand;
import org.vietngumn.schoolapp.event.studentGrade.StudentGradeDTO;
import org.vietngumn.schoolapp.event.studentGrade.StudentGradeIdPath;
import org.vietngumn.schoolapp.event.studentGrade.StudentGradeQueryCriteria;
import org.vietngumn.schoolapp.event.studentGrade.UpdateStudentGradeCommand;
import org.vietngumn.schoolapp.event.studentGrade.UpdatedStudentGrade;
import org.vietngumn.schoolapp.repository.CourseRepository;

@Service
public class StudentGradeServiceImpl implements StudentGradeService {
	
	private static Logger LOG = LoggerFactory.getLogger(StudentGradeServiceImpl.class);
	
	private CourseRepository courseRepository;
	
	@Autowired
	public StudentGradeServiceImpl(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}
	
	@Override
	public CreatedStudentGrade createStudentGrade(CreateStudentGradeCommand createCommand) {
		StudentGradeDTO gradeDTO = createCommand.getDetails();
		StudentGradeIdPath gradeIdPath = gradeDTO.getIdPath();
		
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
		return new CreatedStudentGrade(grade.toStudentGradeDTO(newCreatedId));
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
		
		return new ReadStudentGrade(grade.toStudentGradeDTO(gradeIdPath));
	}


	@Override
	public UpdatedStudentGrade updateStudentGrade(UpdateStudentGradeCommand updateCommand) {
		StudentGradeDTO gradeDTO = updateCommand.getDetails();
		StudentGradeIdPath gradeIdPath = gradeDTO.getIdPath();
		
		Course course = courseRepository.findByCourseId(gradeIdPath.getCourseId());
		if (course == null) {
			LOG.debug("Could not find Course with ID [" + gradeIdPath.getCourseId() + "]");
		}
		
		StudentRecord record = course.getStudentRecord(gradeIdPath.getStudentId());
		if (record == null) {
			LOG.debug("Could not find Student Record with ID [" + gradeIdPath.getStudentId() + "]");
		}
		
		StudentGrade grade = StudentGrade.fromStudentGradeDTO(gradeDTO);
		
		StudentGrade updatedGrade = record.updateStudentGrade(grade);
		if (updatedGrade == null) {
			LOG.debug("Could not find Student Grade with Work Category ID [" + gradeIdPath.getCategoryId() + "] and Work ID [" + gradeIdPath.getWorkId() + "]");
		}
		
		course = courseRepository.save(course);
		
		return new UpdatedStudentGrade(updatedGrade.toStudentGradeDTO(gradeIdPath));
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
		
		StudentGradeDTO details = grade.toStudentGradeDTO(gradeIdPath);
		return new DeletedStudentGrade(details);
	}
	
	@Override
	public QueriedStudentGrades queryCourseWorks(QueryStudentGradesCommand queryCommand) {
		
		StudentGradeQueryCriteria criteria = queryCommand.getQueryCriteria();
		String courseId = criteria.getStudentGradeIdPath().getCourseId();
		String studentId = criteria.getStudentGradeIdPath().getStudentId();
		
		List<StudentGradeDTO> gradeDTOs = new ArrayList<StudentGradeDTO>();

		Course course = courseRepository.findByCourseId(courseId);
		
		StudentRecord record = course.getStudentRecord(studentId);
		
		if (record != null) {
			for (StudentGrade grade : record.getStudentGrades()) {
				StudentGradeIdPath idPath = new StudentGradeIdPath(courseId, studentId, grade.getCategoryId(), grade.getWorkId());
				gradeDTOs.add(grade.toStudentGradeDTO(idPath));
			}
		}
		
		return new QueriedStudentGrades(gradeDTOs);
	}
}
