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
import org.vietngumn.schoolapp.event.courseWork.CourseWorkDTO;
import org.vietngumn.schoolapp.event.courseWork.ReadCourseWork;
import org.vietngumn.schoolapp.event.courseWork.ReadCourseWorkCommand;
import org.vietngumn.schoolapp.rest.domain.CourseWork;
import org.vietngumn.schoolapp.service.CourseWorkService;

@Controller
@RequestMapping("/aggregators/courses/{courseId}/categories/{categoryId}/works")
public class CourseWorkQueriesController {

    private static Logger LOG = LoggerFactory.getLogger(CourseWorkQueriesController.class);

    @Autowired
    private CourseWorkService courseWorkService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<CourseWork> getAllCourseWorkCategories(@PathVariable String courseID) {
        List<CourseWork> categories = new ArrayList<CourseWork>();
//        for (CourseDetails detail : courseService.readAllCourseWorkCategories(new RequestAllOrdersEvent()).getOrdersDetails()) {
//            orders.add(Order.fromOrderDetails(detail));
//        }
        return categories;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{workId}")
    public ResponseEntity<CourseWork> readCourseWork(@PathVariable String courseId, @PathVariable String categoryId, @PathVariable String workId) {
    	ReadCourseWorkCommand readCommand = new ReadCourseWorkCommand(courseId, categoryId, workId);
    	
        ReadCourseWork response = courseWorkService.readCourseWork(readCommand);

        if (!response.isEntityFound()) {
            return new ResponseEntity<CourseWork>(HttpStatus.NOT_FOUND);
        }

        CourseWorkDTO details = response.getDetails();
        
        CourseWork work = CourseWork.fromCourseWorkDTO(details);
        
        return new ResponseEntity<CourseWork>(work, HttpStatus.OK);
    }

}
