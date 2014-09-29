package org.vietngumn.schoolapp.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.vietngumn.schoolapp.event.course.CourseDTO;
import org.vietngumn.schoolapp.event.course.CourseQueryCriteria;
import org.vietngumn.schoolapp.event.course.QueriedCourses;
import org.vietngumn.schoolapp.event.course.QueryCoursesCommand;
import org.vietngumn.schoolapp.event.course.ReadCourse;
import org.vietngumn.schoolapp.event.course.ReadCourseCommand;
import org.vietngumn.schoolapp.rest.domain.Course;
import org.vietngumn.schoolapp.service.CourseService;

@Controller
@RequestMapping("/aggregators/courses")
public class CourseQueriesController {

    private static Logger LOG = LoggerFactory.getLogger(CourseQueriesController.class);

    private static final String CURRENT_SCHOOL_YEAR_ID = "1415";
    
    @Autowired
    private CourseService courseService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Course> getCoursesForSchoolYear(@RequestParam(value = "schoolYearId", required = false) String schoolYearId) {
    	CourseQueryCriteria courseCriteria = new CourseQueryCriteria();
    	if (!StringUtils.isEmpty(schoolYearId)) {
    		courseCriteria.setSchoolYearId(schoolYearId);
    	} else {
    		courseCriteria.setSchoolYearId(CURRENT_SCHOOL_YEAR_ID);
    	}
    	
    	QueriedCourses queriedCourses = courseService.queryCourses(new QueryCoursesCommand(courseCriteria));
    	
        List<Course> courses = new ArrayList<Course>();
        for (CourseDTO dto : queriedCourses.getCourses()) {
        	courses.add(Course.fromQueriedCourseDTO(dto));
        }
        return courses;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{courseId}")
    public ResponseEntity<Course> readCourse(@PathVariable String courseId) {
    	ReadCourseCommand request = new ReadCourseCommand(courseId);
        ReadCourse response = courseService.readCourse(request);

        if (!response.isEntityFound()) {
            return new ResponseEntity<Course>(HttpStatus.NOT_FOUND);
        }

        Course course = Course.fromCourseDetails(response.getCourseDetails());
    	
        return new ResponseEntity<Course>(course, HttpStatus.OK);
    }

}
