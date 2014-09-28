package org.vietngumn.schoolapp.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.vietngumn.schoolapp.event.studentGrade.ReadStudentGrade;
import org.vietngumn.schoolapp.event.studentGrade.ReadStudentGradeCommand;
import org.vietngumn.schoolapp.event.studentGrade.StudentGradeDTO;
import org.vietngumn.schoolapp.event.studentGrade.StudentGradeIdPath;
import org.vietngumn.schoolapp.rest.domain.StudentGrade;
import org.vietngumn.schoolapp.service.StudentGradeService;

@Controller
@RequestMapping("/aggregators/courses/{courseId}/studentrecords")
public class StudentGradeQueriesController {

    private static Logger LOG = LoggerFactory.getLogger(StudentGradeQueriesController.class);

    @Autowired
    private StudentGradeService studentGradeService;

    @RequestMapping(method = RequestMethod.GET, value = "/{studentId}/grades/categories/{categoryId}/works/{workId}")
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
