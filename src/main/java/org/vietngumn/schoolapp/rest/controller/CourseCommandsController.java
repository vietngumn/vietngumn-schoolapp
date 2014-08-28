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
import org.vietngumn.schoolapp.event.course.CourseDetails;
import org.vietngumn.schoolapp.event.course.CreateCourseRequest;
import org.vietngumn.schoolapp.event.course.CreateCourseResponse;
import org.vietngumn.schoolapp.event.course.DeleteCourseRequest;
import org.vietngumn.schoolapp.event.course.DeleteCourseResponse;
import org.vietngumn.schoolapp.event.course.UpdateCourseRequest;
import org.vietngumn.schoolapp.event.course.UpdateCourseResponse;
import org.vietngumn.schoolapp.rest.domain.Course;
import org.vietngumn.schoolapp.service.CourseService;

@Controller
@RequestMapping("/aggregators/courses")
public class CourseCommandsController {

    private static Logger LOG = LoggerFactory.getLogger(CourseCommandsController.class);

    @Autowired
    private CourseService courseService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Course> createCourse(@RequestBody Course course, UriComponentsBuilder builder) {

    	CreateCourseResponse courseCreated = courseService.createCourse(new CreateCourseRequest(course.toCourseDetails()));

    	Course newCourse = Course.fromCourseDetails(courseCreated.getCourseDetails());

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path("/aggregators/courses/{courseId}")
                        .buildAndExpand(courseCreated.getNewCourseId().toString()).toUri());

        return new ResponseEntity<Course>(newCourse, headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/{courseId}")
    public ResponseEntity<Course> updateCourse(@PathVariable String courseId, @RequestBody Course course) {
    	CourseDetails courseDetails = course.toCourseDetails();
    	UpdateCourseRequest updateRequest = new UpdateCourseRequest(courseId, courseDetails);
    	
    	UpdateCourseResponse updateResponse = courseService.updateCourse(updateRequest);

        if (!updateResponse.isEntityFound()) {
            return new ResponseEntity<Course>(HttpStatus.NOT_FOUND);
        }

        Course updatedCourse = Course.fromCourseDetails(updateResponse.getCourseDetails());

        if (updateResponse.isUpdateCompleted()) {
            return new ResponseEntity<Course>(updatedCourse, HttpStatus.OK);
        }

        return new ResponseEntity<Course>(course, HttpStatus.FORBIDDEN);
    }
    
    @RequestMapping(method = RequestMethod.DELETE, value = "/{courseId}")
    public ResponseEntity<Course> deleteCourse(@PathVariable String courseId) {

    	DeleteCourseRequest deleteRequest = new DeleteCourseRequest(courseId);
    	DeleteCourseResponse deleteResponse = courseService.deleteCourse(deleteRequest);

        if (!deleteResponse.isEntityFound()) {
            return new ResponseEntity<Course>(HttpStatus.NOT_FOUND);
        }

        Course course = Course.fromCourseDetails(deleteResponse.getCourseDetails());

        if (deleteResponse.isDeletionCompleted()) {
            return new ResponseEntity<Course>(course, HttpStatus.OK);
        }

        return new ResponseEntity<Course>(course, HttpStatus.FORBIDDEN);
    }

}
