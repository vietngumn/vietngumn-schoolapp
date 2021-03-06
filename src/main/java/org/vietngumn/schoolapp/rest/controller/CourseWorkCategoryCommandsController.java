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
import org.vietngumn.schoolapp.event.courseWorkCategory.CourseWorkCategoryDTO;
import org.vietngumn.schoolapp.event.courseWorkCategory.CourseWorkCategoryIdPath;
import org.vietngumn.schoolapp.event.courseWorkCategory.CreateCourseWorkCategoryCommand;
import org.vietngumn.schoolapp.event.courseWorkCategory.CreatedCourseWorkCategory;
import org.vietngumn.schoolapp.event.courseWorkCategory.DeleteCourseWorkCategoryCommand;
import org.vietngumn.schoolapp.event.courseWorkCategory.DeletedCourseWorkCategory;
import org.vietngumn.schoolapp.event.courseWorkCategory.UpdateCourseWorkCategoryCommand;
import org.vietngumn.schoolapp.event.courseWorkCategory.UpdatedCourseWorkCategory;
import org.vietngumn.schoolapp.rest.domain.CourseWorkCategory;
import org.vietngumn.schoolapp.service.CourseWorkCategoryService;

@Controller
@RequestMapping("/aggregators/courses/{courseId}/workcategories")
public class CourseWorkCategoryCommandsController {

    private static Logger LOG = LoggerFactory.getLogger(CourseWorkCategoryCommandsController.class);

    @Autowired
    private CourseWorkCategoryService courseWorkCategoryService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CourseWorkCategory> createCourseWorkCategory(@PathVariable String courseId, @RequestBody CourseWorkCategory courseWorkCategory, UriComponentsBuilder builder) {
    	CourseWorkCategoryIdPath categoryIdPath = new CourseWorkCategoryIdPath(courseId, null);
    	CourseWorkCategoryDTO categoryDTO = courseWorkCategory.toCourseWorkCategoryDTO(categoryIdPath);
    	
    	CreateCourseWorkCategoryCommand createCommand = new CreateCourseWorkCategoryCommand(categoryDTO);
    	
    	CreatedCourseWorkCategory createdEvent = courseWorkCategoryService.createCourseWorkCategory(createCommand);
    	
    	CourseWorkCategoryDTO createdCategoryDTO = createdEvent.getDetails();
    	CourseWorkCategory newCategory = CourseWorkCategory.fromCourseWorkCategoryDTO(createdCategoryDTO);
        
    	CourseWorkCategoryIdPath newCreatedId = createdCategoryDTO.getIdPath();
    	HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path("/aggregators/courses/{courseId}/workcategories/{categoryId}")
                        .buildAndExpand(newCreatedId.getCourseId(), newCreatedId.getCategoryId()).toUri());
        return new ResponseEntity<CourseWorkCategory>(newCategory, headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/{categoryId}")
    public ResponseEntity<CourseWorkCategory> updateCourseWorkCategory(@PathVariable String courseId, @PathVariable String categoryId, @RequestBody CourseWorkCategory courseWorkCategory) {
    	CourseWorkCategoryIdPath categoryIdPath = new CourseWorkCategoryIdPath(courseId, categoryId);
    	CourseWorkCategoryDTO categoryDTO = courseWorkCategory.toCourseWorkCategoryDTO(categoryIdPath);
    	
    	UpdateCourseWorkCategoryCommand updateCommand = new UpdateCourseWorkCategoryCommand(categoryDTO);
    	
    	UpdatedCourseWorkCategory updatedEvent = courseWorkCategoryService.updateCourseWorkCategory(updateCommand);
        if (!updatedEvent.isEntityFound()) {
            return new ResponseEntity<CourseWorkCategory>(HttpStatus.NOT_FOUND);
        }

        CourseWorkCategory updatedCategory = CourseWorkCategory.fromCourseWorkCategoryDTO(updatedEvent.getDetails());
        if (updatedEvent.isUpdateCompleted()) {
            return new ResponseEntity<CourseWorkCategory>(updatedCategory, HttpStatus.OK);
        }

        return new ResponseEntity<CourseWorkCategory>(courseWorkCategory, HttpStatus.FORBIDDEN);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{categoryId}")
    public ResponseEntity<CourseWorkCategory> deleteCourseWorkCategory(@PathVariable String courseId, @PathVariable String categoryId) {
    	CourseWorkCategoryIdPath categoryIdPath = new CourseWorkCategoryIdPath(courseId, categoryId);
    	DeleteCourseWorkCategoryCommand deleteCommand = new DeleteCourseWorkCategoryCommand(categoryIdPath);
    	
    	DeletedCourseWorkCategory deletedEvent = courseWorkCategoryService.deleteCourseWorkCategory(deleteCommand);

        if (!deletedEvent.isEntityFound()) {
            return new ResponseEntity<CourseWorkCategory>(HttpStatus.NOT_FOUND);
        }

        CourseWorkCategory category = CourseWorkCategory.fromCourseWorkCategoryDTO(deletedEvent.getDetails());

        if (deletedEvent.isDeletionCompleted()) {
            return new ResponseEntity<CourseWorkCategory>(category, HttpStatus.OK);
        }

        return new ResponseEntity<CourseWorkCategory>(category, HttpStatus.FORBIDDEN);
    }

}
