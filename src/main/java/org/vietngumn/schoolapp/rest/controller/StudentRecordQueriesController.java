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
import org.vietngumn.schoolapp.event.studentRecord.ReadStudentRecord;
import org.vietngumn.schoolapp.event.studentRecord.ReadStudentRecordCommand;
import org.vietngumn.schoolapp.event.studentRecord.StudentRecordDTO;
import org.vietngumn.schoolapp.rest.domain.StudentRecord;
import org.vietngumn.schoolapp.service.StudentRecordService;

@Controller
@RequestMapping("/aggregators/courses/{courseId}/studentrecords")
public class StudentRecordQueriesController {

    private static Logger LOG = LoggerFactory.getLogger(StudentRecordQueriesController.class);

    @Autowired
    private StudentRecordService StudentRecordService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<StudentRecord> getAllStudentRecords(@PathVariable String courseID) {
        List<StudentRecord> categories = new ArrayList<StudentRecord>();
//        for (CourseDetails detail : courseService.readAllCourseWorkCategories(new RequestAllOrdersEvent()).getOrdersDetails()) {
//            orders.add(Order.fromOrderDetails(detail));
//        }
        return categories;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{studentId}")
    public ResponseEntity<StudentRecord> readStudentRecord(@PathVariable String courseId, @PathVariable String studentId) {
    	ReadStudentRecordCommand readCommand = new ReadStudentRecordCommand(courseId, studentId);
        ReadStudentRecord response = StudentRecordService.readStudentRecord(readCommand);

        if (!response.isEntityFound()) {
            return new ResponseEntity<StudentRecord>(HttpStatus.NOT_FOUND);
        }

        StudentRecordDTO details = response.getDetails();
        
        StudentRecord record = StudentRecord.fromStudentRecordDTO(details);
        return new ResponseEntity<StudentRecord>(record, HttpStatus.OK);
    }

}
