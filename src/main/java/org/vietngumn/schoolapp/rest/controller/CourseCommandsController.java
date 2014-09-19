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
import org.vietngumn.schoolapp.event.course.CourseDTO;
import org.vietngumn.schoolapp.event.course.CreateCourseCommand;
import org.vietngumn.schoolapp.event.course.CreatedCourse;
import org.vietngumn.schoolapp.event.course.DeleteCourseCommand;
import org.vietngumn.schoolapp.event.course.DeletedCourse;
import org.vietngumn.schoolapp.event.course.UpdateCourseCommand;
import org.vietngumn.schoolapp.event.course.UpdatedCourse;
import org.vietngumn.schoolapp.rest.domain.Course;
import org.vietngumn.schoolapp.service.CourseService;
import org.vietngumn.schoolapp.service.CourseWorkService;

@Controller
@RequestMapping("/aggregators/courses")
public class CourseCommandsController {

    private static Logger LOG = LoggerFactory.getLogger(CourseCommandsController.class);

    @Autowired
    private CourseService courseService;
    
    @Autowired
    private CourseWorkService courseWorkService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Course> createCourse(@RequestBody Course course, UriComponentsBuilder builder) {

    	CreatedCourse courseCreated = courseService.createCourse(new CreateCourseCommand(course.toCourseDetails()));

    	Course newCourse = Course.fromCourseDetails(courseCreated.getCourseDetails());

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path("/aggregators/courses/{courseId}")
                        .buildAndExpand(courseCreated.getNewCourseId().toString()).toUri());

        return new ResponseEntity<Course>(newCourse, headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/{courseId}")
    public ResponseEntity<Course> updateCourse(@PathVariable String courseId, @RequestBody Course course) {
    	CourseDTO courseDetails = course.toCourseDetails();
    	UpdateCourseCommand updateRequest = new UpdateCourseCommand(courseId, courseDetails);
    	
    	UpdatedCourse updateResponse = courseService.updateCourse(updateRequest);

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

    	DeleteCourseCommand deleteRequest = new DeleteCourseCommand(courseId);
    	DeletedCourse deleteResponse = courseService.deleteCourse(deleteRequest);

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
