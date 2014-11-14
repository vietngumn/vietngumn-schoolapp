package org.vietngumn.schoolapp.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.vietngumn.schoolapp.event.studentGrade.QueriedStudentGrades;
import org.vietngumn.schoolapp.event.studentGrade.QueryStudentGradesCommand;
import org.vietngumn.schoolapp.event.studentGrade.ReadAllStudentGrades;
import org.vietngumn.schoolapp.event.studentGrade.ReadAllStudentGradesCommand;
import org.vietngumn.schoolapp.event.studentGrade.ReadStudentGrade;
import org.vietngumn.schoolapp.event.studentGrade.ReadStudentGradeCommand;
import org.vietngumn.schoolapp.event.studentGrade.StudentGradeDTO;
import org.vietngumn.schoolapp.event.studentGrade.StudentGradeIdPath;
import org.vietngumn.schoolapp.event.studentGrade.StudentGradeQueryCriteria;
import org.vietngumn.schoolapp.event.studentRecord.StudentRecordDTO;
import org.vietngumn.schoolapp.rest.domain.StudentGrade;
import org.vietngumn.schoolapp.rest.domain.StudentRecord;
import org.vietngumn.schoolapp.service.StudentGradeService;

@Controller
@RequestMapping("/aggregators/courses/{courseId}")
public class StudentGradeQueriesController {

    private static Logger LOG = LoggerFactory.getLogger(StudentGradeQueriesController.class);
    
    private static final String GRADE_COLUMN_SPACE = "20";

    @Autowired
    private StudentGradeService studentGradeService;

    @RequestMapping(method = RequestMethod.GET, value = "/studentrecords/{studentId}/grades")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<StudentGrade> getAllStudentGrades(@PathVariable String courseId, @PathVariable String studentId) {
    	StudentGradeIdPath studentGradeIdPath = new StudentGradeIdPath(courseId, studentId, null, null);
    	ReadAllStudentGradesCommand readAllCommand = new ReadAllStudentGradesCommand(studentGradeIdPath);
    	
    	ReadAllStudentGrades studentAllGrades = studentGradeService.readAllStudentGrades(readAllCommand);
    	
        List<StudentGrade> grades = new ArrayList<StudentGrade>();
        for (StudentGradeDTO dto : studentAllGrades.getStudentGrades()) {
        	grades.add(StudentGrade.fromQueriedStudentGradeDTO(dto));
        }
        return grades;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/studentrecords/{studentId}/grades/categories/{categoryId}/works/{workId}")
    public ResponseEntity<StudentGrade> readStudentGrade(@PathVariable String courseId, @PathVariable String studentId, @PathVariable String categoryId, @PathVariable String workId) {
    	StudentGradeIdPath studentGradeIdPath = new StudentGradeIdPath(courseId, studentId, categoryId, workId);
    	
    	ReadStudentGradeCommand readCommand = new ReadStudentGradeCommand(studentGradeIdPath);
    	
        ReadStudentGrade response = studentGradeService.readStudentGrade(readCommand);

        if (!response.isEntityFound()) {
            return new ResponseEntity<StudentGrade>(HttpStatus.NOT_FOUND);
        }

        StudentGradeDTO details = response.getDetails();
        
        StudentGrade record = StudentGrade.fromStudentGradeDTO(details);
        return new ResponseEntity<StudentGrade>(record, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/grades")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<StudentRecord> queryStudentGrades(@PathVariable String courseId) {
    	StudentGradeQueryCriteria queryCriteria = new StudentGradeQueryCriteria();
    	queryCriteria.setCourseId(courseId);

    	QueriedStudentGrades queriedGrades = studentGradeService.queryStudentGrades(new QueryStudentGradesCommand(queryCriteria));
    	
        List<StudentRecord> recordGrades = new ArrayList<StudentRecord>();
        
        StudentRecord headerRecord = this.buildQueryResultHeader(queriedGrades.getStudentRecordGrades().get(0));
        recordGrades.add(headerRecord);
    	
        for (StudentRecordDTO recordDTO : queriedGrades.getStudentRecordGrades()) {
        	StudentRecord record = StudentRecord.fromQueriedStudentRecordDTO(recordDTO);
        	
        	List<StudentGrade> grades = new ArrayList<StudentGrade>();
            for (StudentGradeDTO gradeDTO : recordDTO.getStudentGrades()) {
            	grades.add(StudentGrade.fromQueriedStudentGradeDTO(gradeDTO));
            }
            record.setStudentGrades(grades);
            
        	recordGrades.add(record);
        }
        return recordGrades;
    }
    
    private StudentRecord buildQueryResultHeader(StudentRecordDTO modelStudentRecord) {
    	StudentRecord headerRecord = new StudentRecord();
        headerRecord.setStudentId("studentId");
        headerRecord.setStudentName("studentName");
        
        List<StudentGrade> headerGrades = new ArrayList<StudentGrade>();
        for (StudentGradeDTO modelGrade : modelStudentRecord.getStudentGrades()) {
        	StudentGrade headerGrade = new StudentGrade();
        	headerGrade.setCategoryId(modelGrade.getCategoryId());
        	headerGrade.setWorkId(modelGrade.getWorkId());
        	headerGrade.setColumnName(modelGrade.getWorkName());
        	headerGrade.setColumnSpace(GRADE_COLUMN_SPACE);
        	headerGrades.add(headerGrade);
        }
        headerRecord.setStudentGrades(headerGrades);
        return headerRecord;
    }
}
