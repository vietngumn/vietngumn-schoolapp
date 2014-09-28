package org.vietngumn.schoolapp.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;
import org.vietngumn.schoolapp.event.studentGrade.CreateStudentGradeCommand;
import org.vietngumn.schoolapp.event.studentGrade.CreatedStudentGrade;
import org.vietngumn.schoolapp.event.studentGrade.DeleteStudentGradeCommand;
import org.vietngumn.schoolapp.event.studentGrade.DeletedStudentGrade;
import org.vietngumn.schoolapp.event.studentGrade.StudentGradeDTO;
import org.vietngumn.schoolapp.event.studentGrade.StudentGradeIdPath;
import org.vietngumn.schoolapp.event.studentGrade.UpdateStudentGradeCommand;
import org.vietngumn.schoolapp.event.studentGrade.UpdatedStudentGrade;
import org.vietngumn.schoolapp.rest.domain.StudentGrade;
import org.vietngumn.schoolapp.service.StudentGradeService;

@Controller
@RequestMapping("/aggregators/courses/{courseId}/studentrecords/{studentId}/grades")
public class StudentGradeCommandsController {

    private static Logger LOG = LoggerFactory.getLogger(StudentGradeCommandsController.class);

    @Autowired
    private StudentGradeService studentGradeService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<StudentGrade> createStudentGrade(@PathVariable String courseId, @PathVariable String studentId, @RequestBody StudentGrade studentGrade, UriComponentsBuilder builder) {
    	StudentGradeIdPath gradeIdPath = new StudentGradeIdPath(courseId, studentId, null, null);
    	StudentGradeDTO gradeDTO = studentGrade.toStudentGradeDTO();
    	
    	CreateStudentGradeCommand createCommand = new CreateStudentGradeCommand(gradeIdPath, gradeDTO);
    	
    	CreatedStudentGrade createdEvent = studentGradeService.createStudentGrade(createCommand);
    	
    	StudentGrade newGrade = StudentGrade.fromStudentGradeDTO(createdEvent.getDetails());
        
    	StudentGradeIdPath newCreatedId = createdEvent.getNewCreatedId();
    	HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path("/aggregators/courses/{courseId}/studentrecords/{studentId}/grades/categories/{categoryId}/works/{workId}")
                        .buildAndExpand(newCreatedId.getCourseId(), newCreatedId.getStudentId(), newCreatedId.getCategoryId(), newCreatedId.getWorkId()).toUri());
        return new ResponseEntity<StudentGrade>(newGrade, headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/categories/{categoryId}/works/{workId}")
    public ResponseEntity<StudentGrade> updateStudentGrade(@PathVariable String courseId, @PathVariable String studentId, @PathVariable String categoryId, @PathVariable String workId, @RequestBody StudentGrade studentGrade) {
    	StudentGradeIdPath gradeIdPath = new StudentGradeIdPath(courseId, studentId, categoryId, workId);
    	StudentGradeDTO gradeDTO = studentGrade.toStudentGradeDTO();
    	gradeDTO.setCategoryId(categoryId);
		gradeDTO.setWorkId(workId);
		
    	UpdateStudentGradeCommand updateCommand = new UpdateStudentGradeCommand(gradeIdPath, gradeDTO);
    	
    	UpdatedStudentGrade updatedEvent = studentGradeService.updateStudentGrade(updateCommand);
        if (!updatedEvent.isEntityFound()) {
            return new ResponseEntity<StudentGrade>(HttpStatus.NOT_FOUND);
        }

        StudentGrade updatedGrade = StudentGrade.fromStudentGradeDTO(updatedEvent.getDetails());
        if (updatedEvent.isUpdateCompleted()) {
            return new ResponseEntity<StudentGrade>(updatedGrade, HttpStatus.OK);
        }

        return new ResponseEntity<StudentGrade>(studentGrade, HttpStatus.FORBIDDEN);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/categories/{categoryId}/works/{workId}")
    public ResponseEntity<StudentGrade> deleteStudentGrade(@PathVariable String courseId, @PathVariable String studentId, @PathVariable String categoryId, @PathVariable String workId) {
    	StudentGradeIdPath gradeIdPath = new StudentGradeIdPath(courseId, studentId, categoryId, workId);
    	
    	DeleteStudentGradeCommand deleteCommand = new DeleteStudentGradeCommand(gradeIdPath);
    	
    	DeletedStudentGrade deletedEvent = studentGradeService.deleteStudentGrade(deleteCommand);

        if (!deletedEvent.isEntityFound()) {
            return new ResponseEntity<StudentGrade>(HttpStatus.NOT_FOUND);
        }

        StudentGrade record = StudentGrade.fromStudentGradeDTO(deletedEvent.getDetails());

        if (deletedEvent.isDeletionCompleted()) {
            return new ResponseEntity<StudentGrade>(record, HttpStatus.OK);
        }

        return new ResponseEntity<StudentGrade>(record, HttpStatus.FORBIDDEN);
    }

}
