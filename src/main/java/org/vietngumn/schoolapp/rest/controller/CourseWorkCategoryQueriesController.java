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
import org.vietngumn.schoolapp.event.course.CourseDTO;
import org.vietngumn.schoolapp.event.course.QueryCoursesCommand;
import org.vietngumn.schoolapp.event.courseWorkCategory.CourseWorkCategoryDTO;
import org.vietngumn.schoolapp.event.courseWorkCategory.QueriedWorkCategories;
import org.vietngumn.schoolapp.event.courseWorkCategory.QueryWorkCategoriesCommand;
import org.vietngumn.schoolapp.event.courseWorkCategory.ReadCourseWorkCategory;
import org.vietngumn.schoolapp.event.courseWorkCategory.ReadCourseWorkCategoryCommand;
import org.vietngumn.schoolapp.event.courseWorkCategory.WorkCategoryQueryCriteria;
import org.vietngumn.schoolapp.rest.domain.Course;
import org.vietngumn.schoolapp.rest.domain.CourseWorkCategory;
import org.vietngumn.schoolapp.service.CourseWorkCategoryService;

@Controller
@RequestMapping("/aggregators/courses/{courseId}/workcategories")
public class CourseWorkCategoryQueriesController {

    private static Logger LOG = LoggerFactory.getLogger(CourseWorkCategoryQueriesController.class);

    @Autowired
    private CourseWorkCategoryService workCategoryService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<CourseWorkCategory> getAllCourseWorkCategories(@PathVariable String courseId) {
    	WorkCategoryQueryCriteria queryCriteria = new WorkCategoryQueryCriteria();
    	queryCriteria.setCourseId(courseId);
    	
    	QueriedWorkCategories queriedCategories = workCategoryService.queryCourseWorkCategories(new QueryWorkCategoriesCommand(queryCriteria));
    	
        List<CourseWorkCategory> categories = new ArrayList<CourseWorkCategory>();
        for (CourseWorkCategoryDTO dto : queriedCategories.getWorkCategories()) {
        	categories.add(CourseWorkCategory.fromQueriedWorkCategoryDTO(dto));
        }
        return categories;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{categoryId}")
    public ResponseEntity<CourseWorkCategory> readCourseWorkCategory(@PathVariable String courseId, @PathVariable String categoryId) {
    	ReadCourseWorkCategoryCommand readCommand = new ReadCourseWorkCategoryCommand(courseId, categoryId);
        ReadCourseWorkCategory response = workCategoryService.readCourseWorkCategory(readCommand);

        if (!response.isEntityFound()) {
            return new ResponseEntity<CourseWorkCategory>(HttpStatus.NOT_FOUND);
        }

        CourseWorkCategoryDTO details = response.getDetails();
        
        CourseWorkCategory category = CourseWorkCategory.fromCourseWorkCategoryDTO(details);
        return new ResponseEntity<CourseWorkCategory>(category, HttpStatus.OK);
    }

}
