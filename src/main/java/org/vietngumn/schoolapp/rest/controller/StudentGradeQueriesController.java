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
import org.vietngumn.schoolapp.event.studentGrade.ReadStudentGrade;
import org.vietngumn.schoolapp.event.studentGrade.ReadStudentGradeCommand;
import org.vietngumn.schoolapp.event.studentGrade.StudentGradeDTO;
import org.vietngumn.schoolapp.event.studentGrade.StudentGradeIdPath;
import org.vietngumn.schoolapp.event.studentGrade.StudentGradeQueryCriteria;
import org.vietngumn.schoolapp.rest.domain.StudentGrade;
import org.vietngumn.schoolapp.service.StudentGradeService;

@Controller
@RequestMapping("/aggregators/courses/{courseId}/studentrecords/{studentId}/grades")
public class StudentGradeQueriesController {

    private static Logger LOG = LoggerFactory.getLogger(StudentGradeQueriesController.class);

    @Autowired
    private StudentGradeService studentGradeService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<StudentGrade> getAllStudentGrades(@PathVariable String courseId, @PathVariable String studentId) {
    	StudentGradeQueryCriteria queryCriteria = new StudentGradeQueryCriteria();
    	queryCriteria.setStudentGradeIdPath(new StudentGradeIdPath(courseId, studentId, null, null));
    	
    	QueriedStudentGrades queriedGrades = studentGradeService.queryCourseWorks(new QueryStudentGradesCommand(queryCriteria));
    	
        List<StudentGrade> grades = new ArrayList<StudentGrade>();
        for (StudentGradeDTO dto : queriedGrades.getStudentGrades()) {
        	grades.add(StudentGrade.fromQueriedStudentGradeDTO(dto));
        }
        return grades;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/categories/{categoryId}/works/{workId}")
    public ResponseEntity<StudentGrade> readStudentGrade(@PathVariable String courseId, @PathVariable String studentId, @PathVariable String categoryId, @PathVariable String workId) {
    	StudentGradeIdPath studentGradeID = new StudentGradeIdPath(courseId, studentId, categoryId, workId);
    	
    	ReadStudentGradeCommand readCommand = new ReadStudentGradeCommand(studentGradeID);
    	
        ReadStudentGrade response = studentGradeService.readStudentGrade(readCommand);

        if (!response.isEntityFound()) {
            return new ResponseEntity<StudentGrade>(HttpStatus.NOT_FOUND);
        }

        StudentGradeDTO details = response.getDetails();
        
        StudentGrade record = StudentGrade.fromStudentGradeDTO(details);
        return new ResponseEntity<StudentGrade>(record, HttpStatus.OK);
    }

}
