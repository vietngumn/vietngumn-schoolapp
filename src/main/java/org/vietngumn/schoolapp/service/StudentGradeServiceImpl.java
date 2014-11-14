package org.vietngumn.schoolapp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vietngumn.schoolapp.domain.Course;
import org.vietngumn.schoolapp.domain.CourseWork;
import org.vietngumn.schoolapp.domain.CourseWorkCategory;
import org.vietngumn.schoolapp.domain.StudentGrade;
import org.vietngumn.schoolapp.domain.StudentRecord;
import org.vietngumn.schoolapp.event.studentGrade.CreateStudentGradeCommand;
import org.vietngumn.schoolapp.event.studentGrade.CreatedStudentGrade;
import org.vietngumn.schoolapp.event.studentGrade.DeleteStudentGradeCommand;
import org.vietngumn.schoolapp.event.studentGrade.DeletedStudentGrade;
import org.vietngumn.schoolapp.event.studentGrade.QueriedStudentGrades;
import org.vietngumn.schoolapp.event.studentGrade.QueryStudentGradesCommand;
import org.vietngumn.schoolapp.event.studentGrade.ReadAllStudentGrades;
import org.vietngumn.schoolapp.event.studentGrade.ReadAllStudentGradesCommand;
import org.vietngumn.schoolapp.event.studentGrade.ReadStudentGrade;
import org.vietngumn.schoolapp.event.studentGrade.ReadStudentGradeCommand;
import org.vietngumn.schoolapp.event.studentGrade.StudentGradeDTO;
import org.vietngumn.schoolapp.event.studentGrade.StudentGradeIdPath;
import org.vietngumn.schoolapp.event.studentGrade.StudentGradeQueryCriteria;
import org.vietngumn.schoolapp.event.studentGrade.UpdateStudentGradeCommand;
import org.vietngumn.schoolapp.event.studentGrade.UpdatedStudentGrade;
import org.vietngumn.schoolapp.event.studentRecord.StudentRecordDTO;
import org.vietngumn.schoolapp.event.studentRecord.StudentRecordIdPath;
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
	public ReadAllStudentGrades readAllStudentGrades(ReadAllStudentGradesCommand readAllCommand) {
		
		StudentGradeIdPath gradeIdPath = readAllCommand.getGradeIdPath();
		String courseId = gradeIdPath.getCourseId();
		String studentId = gradeIdPath.getStudentId();
		
		List<StudentGradeDTO> gradeDTOs = new ArrayList<StudentGradeDTO>();

		Course course = courseRepository.findByCourseId(courseId);
		
		StudentRecord record = course.getStudentRecord(studentId);
		
		if (record != null) {
			for (StudentGrade grade : record.getStudentGrades()) {
				StudentGradeIdPath idPath = new StudentGradeIdPath(courseId, studentId, grade.getCategoryId(), grade.getWorkId());
				gradeDTOs.add(grade.toStudentGradeDTO(idPath));
			}
		}
		
		return new ReadAllStudentGrades(gradeDTOs);
	}
	
	@Override
	public QueriedStudentGrades queryStudentGrades(QueryStudentGradesCommand queryCommand) {
		StudentGradeQueryCriteria criteria = queryCommand.getQueryCriteria();
		String courseId = criteria.getCourseId();
		
		Course course = courseRepository.findByCourseId(courseId);
		List<StudentRecord> records = course.getStudentRecords();
		List<CourseWorkCategory> categories = course.getCourseWorkCategories(criteria.getWorkCategoryIds());
		
		Map<String, StudentRecordDTO> recordsMap = new HashMap<String, StudentRecordDTO>();
		
		for (CourseWorkCategory category : categories) {
			List<CourseWork> works = category.getCourseWorks(criteria.getCourseWorkIds(), criteria.getSchoolDateIds());
			for (CourseWork work : works) {
				for (StudentRecord record : records) {
					StudentRecordDTO recordDTO = recordsMap.get(record.getStudentId());
					if (recordDTO == null) {
						StudentRecordIdPath recordIdPath = new StudentRecordIdPath(courseId, record.getStudentId());
						recordDTO = record.toStudentRecordDTO(recordIdPath);
						recordsMap.put(record.getStudentId(), recordDTO);
					}
					
					StudentGrade grade = record.getStudentGrade(category.getCategoryId(), work.getWorkId());
					if (grade == null) {
						grade = new StudentGrade(category.getCategoryId(), work.getWorkId());
					}
					StudentGradeIdPath idPath = new StudentGradeIdPath(courseId, record.getStudentId(), grade.getCategoryId(), grade.getWorkId());
					StudentGradeDTO gradeDTO = grade.toStudentGradeDTO(idPath);
					gradeDTO.setWorkName(work.getName());
					recordDTO.addStudentGrade(gradeDTO);
				}
			}
		}
		
		List<StudentRecordDTO> studentRecordDTOs = new ArrayList<StudentRecordDTO>(recordsMap.values());
		return new QueriedStudentGrades(studentRecordDTOs);
	}
	
}
