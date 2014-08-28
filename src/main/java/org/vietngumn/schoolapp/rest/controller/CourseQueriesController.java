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
import org.vietngumn.schoolapp.event.course.CourseDetails;
import org.vietngumn.schoolapp.event.course.ReadCourseRequest;
import org.vietngumn.schoolapp.event.course.ReadCourseResponse;
import org.vietngumn.schoolapp.rest.domain.Course;
import org.vietngumn.schoolapp.service.CourseService;

@Controller
@RequestMapping("/aggregators/courses")
public class CourseQueriesController {

    private static Logger LOG = LoggerFactory.getLogger(CourseQueriesController.class);

    @Autowired
    private CourseService courseService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<Course>();
//        for (CourseDetails detail : courseService.requestAllOrders(new RequestAllOrdersEvent()).getOrdersDetails()) {
//            orders.add(Order.fromOrderDetails(detail));
//        }
        return courses;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{courseId}")
    public ResponseEntity<Course> readCourse(@PathVariable String courseId) {
    	ReadCourseRequest request = new ReadCourseRequest(courseId);
        ReadCourseResponse response = courseService.readCourse(request);

        if (!response.isEntityFound()) {
            return new ResponseEntity<Course>(HttpStatus.NOT_FOUND);
        }

        CourseDetails courseDetails = response.getCourseDetails();
    	Course course = new Course();
    	course.setCourseId(courseDetails.getCourseId());
    	course.setCourseName(courseDetails.getCourseName());
        
        return new ResponseEntity<Course>(course, HttpStatus.OK);
    }

}
