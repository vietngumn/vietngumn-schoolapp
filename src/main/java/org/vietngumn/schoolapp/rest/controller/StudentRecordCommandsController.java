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
import org.vietngumn.schoolapp.event.studentRecord.CreateStudentRecordCommand;
import org.vietngumn.schoolapp.event.studentRecord.CreatedStudentRecord;
import org.vietngumn.schoolapp.event.studentRecord.DeleteStudentRecordCommand;
import org.vietngumn.schoolapp.event.studentRecord.DeletedStudentRecord;
import org.vietngumn.schoolapp.event.studentRecord.StudentRecordDTO;
import org.vietngumn.schoolapp.event.studentRecord.UpdateStudentRecordCommand;
import org.vietngumn.schoolapp.event.studentRecord.UpdatedStudentRecord;
import org.vietngumn.schoolapp.rest.domain.StudentRecord;
import org.vietngumn.schoolapp.service.StudentRecordService;

@Controller
@RequestMapping("/aggregators/courses/{courseId}/studentrecords")
public class StudentRecordCommandsController {

    private static Logger LOG = LoggerFactory.getLogger(StudentRecordCommandsController.class);

    @Autowired
    private StudentRecordService studentRecordService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<StudentRecord> createStudentRecord(@PathVariable String courseId, @RequestBody StudentRecord studentRecord, UriComponentsBuilder builder) {
    	StudentRecordDTO courseDTO = studentRecord.toStudentRecordDTO();
    	courseDTO.setCourseId(courseId);
    	
    	CreateStudentRecordCommand createCommand = new CreateStudentRecordCommand(courseDTO);
    	
    	CreatedStudentRecord createdEvent = studentRecordService.createStudentRecord(createCommand);
    	
    	StudentRecord newRecord = StudentRecord.fromStudentRecordDTO(createdEvent.getDetails());
        
    	HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path("/aggregators/courses/{courseId}/studentrecords/{studentId}")
                        .buildAndExpand(courseId, createdEvent.getNewCreatedId().toString()).toUri());
        return new ResponseEntity<StudentRecord>(newRecord, headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/{studentId}")
    public ResponseEntity<StudentRecord> updateStudentRecord(@PathVariable String courseId, @PathVariable String studentId, @RequestBody StudentRecord studentRecord) {
    	StudentRecordDTO recordDTO = studentRecord.toStudentRecordDTO();
    	recordDTO.setCourseId(courseId);
    	recordDTO.setStudentId(studentId);
    	
    	UpdateStudentRecordCommand updateCommand = new UpdateStudentRecordCommand(courseId, studentId, recordDTO);
    	
    	UpdatedStudentRecord updatedEvent = studentRecordService.updateStudentRecord(updateCommand);
        if (!updatedEvent.isEntityFound()) {
            return new ResponseEntity<StudentRecord>(HttpStatus.NOT_FOUND);
        }

        StudentRecord updatedRecord = StudentRecord.fromStudentRecordDTO(updatedEvent.getDetails());
        if (updatedEvent.isUpdateCompleted()) {
            return new ResponseEntity<StudentRecord>(updatedRecord, HttpStatus.OK);
        }

        return new ResponseEntity<StudentRecord>(studentRecord, HttpStatus.FORBIDDEN);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{studentId}")
    public ResponseEntity<StudentRecord> deleteStudentRecord(@PathVariable String courseId, @PathVariable String studentId) {

    	DeleteStudentRecordCommand deleteCommand = new DeleteStudentRecordCommand(courseId, studentId);
    	DeletedStudentRecord deletedEvent = studentRecordService.deleteStudentRecord(deleteCommand);

        if (!deletedEvent.isEntityFound()) {
            return new ResponseEntity<StudentRecord>(HttpStatus.NOT_FOUND);
        }

        StudentRecord record = StudentRecord.fromStudentRecordDTO(deletedEvent.getDetails());

        if (deletedEvent.isDeletionCompleted()) {
            return new ResponseEntity<StudentRecord>(record, HttpStatus.OK);
        }

        return new ResponseEntity<StudentRecord>(record, HttpStatus.FORBIDDEN);
    }

}
