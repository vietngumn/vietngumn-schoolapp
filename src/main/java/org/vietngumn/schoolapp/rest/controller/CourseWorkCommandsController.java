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
import org.vietngumn.schoolapp.event.courseWorkCategory.CreateCourseWorkCategoryCommand;
import org.vietngumn.schoolapp.event.courseWorkCategory.CreatedCourseWorkCategory;
import org.vietngumn.schoolapp.event.courseWorkCategory.DeleteCourseWorkCategoryCommand;
import org.vietngumn.schoolapp.event.courseWorkCategory.DeletedCourseWorkCategory;
import org.vietngumn.schoolapp.event.courseWorkCategory.UpdateCourseWorkCategoryCommand;
import org.vietngumn.schoolapp.event.courseWorkCategory.UpdatedCourseWorkCategory;
import org.vietngumn.schoolapp.rest.domain.CourseWorkCategory;
import org.vietngumn.schoolapp.service.CourseWorkService;

@Controller
@RequestMapping("/aggregators/courses/{courseId}/categories")
public class CourseWorkCommandsController {

    private static Logger LOG = LoggerFactory.getLogger(CourseWorkCommandsController.class);

    @Autowired
    private CourseWorkService courseWorkService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CourseWorkCategory> createCourseWorkCategory(@PathVariable String courseId, @RequestBody CourseWorkCategory courseWorkCategory, UriComponentsBuilder builder) {
    	CourseWorkCategoryDTO categoryDTO = courseWorkCategory.toCourseWorkCategoryDTO();
    	categoryDTO.setCourseId(courseId);
    	CreateCourseWorkCategoryCommand createCommand = new CreateCourseWorkCategoryCommand(categoryDTO);
    	CreatedCourseWorkCategory createdEvent = courseWorkService.createCourseWorkCategory(createCommand);
    	CourseWorkCategory newCategory = CourseWorkCategory.fromCourseWorkCategoryDTO(createdEvent.getDetails());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path("/aggregators/courses/{courseId}/categories/{categoryId}")
                        .buildAndExpand(courseId, createdEvent.getNewCategoryId().toString()).toUri());
        return new ResponseEntity<CourseWorkCategory>(newCategory, headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/{categoryId}")
    public ResponseEntity<CourseWorkCategory> updateCourse(@PathVariable String courseId, @PathVariable String categoryId, @RequestBody CourseWorkCategory courseWorkCategory) {
    	CourseWorkCategoryDTO categoryDTO = courseWorkCategory.toCourseWorkCategoryDTO();
    	categoryDTO.setCourseId(courseId);
    	categoryDTO.setCategoryId(categoryId);
    	UpdateCourseWorkCategoryCommand updateCommand = new UpdateCourseWorkCategoryCommand(courseId, categoryId, categoryDTO);
    	
    	UpdatedCourseWorkCategory updatedEvent = courseWorkService.updateCourseWorkCategory(updateCommand);
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
    public ResponseEntity<CourseWorkCategory> deleteCourse(@PathVariable String courseId, @PathVariable String categoryId) {

    	DeleteCourseWorkCategoryCommand deleteCommand = new DeleteCourseWorkCategoryCommand(courseId, categoryId);
    	DeletedCourseWorkCategory deletedEvent = courseWorkService.deleteCourseWorkCategory(deleteCommand);

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
