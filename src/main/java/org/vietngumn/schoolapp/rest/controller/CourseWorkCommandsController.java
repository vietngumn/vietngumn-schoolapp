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
import org.vietngumn.schoolapp.event.courseWork.CourseWorkDTO;
import org.vietngumn.schoolapp.event.courseWork.CourseWorkIdPath;
import org.vietngumn.schoolapp.event.courseWork.CreateCourseWorkCommand;
import org.vietngumn.schoolapp.event.courseWork.CreatedCourseWork;
import org.vietngumn.schoolapp.event.courseWork.DeleteCourseWorkCommand;
import org.vietngumn.schoolapp.event.courseWork.DeletedCourseWork;
import org.vietngumn.schoolapp.event.courseWork.UpdateCourseWorkCommand;
import org.vietngumn.schoolapp.event.courseWork.UpdatedCourseWork;
import org.vietngumn.schoolapp.rest.domain.CourseWork;
import org.vietngumn.schoolapp.service.CourseWorkService;

@Controller
@RequestMapping("/aggregators/courses/{courseId}/workcategories/{categoryId}/works")
public class CourseWorkCommandsController {

    private static Logger LOG = LoggerFactory.getLogger(CourseWorkCommandsController.class);

    @Autowired
    private CourseWorkService courseWorkService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CourseWork> createCourseWork(@PathVariable String courseId, @PathVariable String categoryId, @RequestBody CourseWork courseWork, UriComponentsBuilder builder) {
    	CourseWorkIdPath workIdPath = new CourseWorkIdPath(courseId, categoryId, null);
    	CourseWorkDTO workDTO = courseWork.toCourseWorkDTO();
    	
    	CreateCourseWorkCommand createCommand = new CreateCourseWorkCommand(workIdPath, workDTO);
    	
    	CreatedCourseWork createdEvent = courseWorkService.createCourseWork(createCommand);
    	
    	CourseWork newWork = CourseWork.fromCourseWorkDTO(createdEvent.getDetails());
        
    	CourseWorkIdPath newCreatedId = createdEvent.getNewCreatedId();
    	HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path("/aggregators/courses/{courseId}/workcategories/{categoryId}/works/{workId}")
                        .buildAndExpand(newCreatedId.getCourseId(), newCreatedId.getCategoryId(), newCreatedId.getWorkId()).toUri());
        return new ResponseEntity<CourseWork>(newWork, headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/{workId}")
    public ResponseEntity<CourseWork> updateCourseWork(@PathVariable String courseId, @PathVariable String categoryId, @PathVariable String workId, @RequestBody CourseWork courseWork) {
    	CourseWorkIdPath workIdPath = new CourseWorkIdPath(courseId, categoryId, workId);
    	CourseWorkDTO workDTO = courseWork.toCourseWorkDTO();
    	workDTO.setWorkId(workId);
    	
    	UpdateCourseWorkCommand updateCommand = new UpdateCourseWorkCommand(workIdPath, workDTO);
    	
    	UpdatedCourseWork updatedEvent = courseWorkService.updateCourseWork(updateCommand);
        if (!updatedEvent.isEntityFound()) {
            return new ResponseEntity<CourseWork>(HttpStatus.NOT_FOUND);
        }

        CourseWork updatedCategory = CourseWork.fromCourseWorkDTO(updatedEvent.getDetails());
        if (updatedEvent.isUpdateCompleted()) {
            return new ResponseEntity<CourseWork>(updatedCategory, HttpStatus.OK);
        }

        return new ResponseEntity<CourseWork>(courseWork, HttpStatus.FORBIDDEN);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{workId}")
    public ResponseEntity<CourseWork> deleteCourseWork(@PathVariable String courseId, @PathVariable String categoryId, @PathVariable String workId) {
    	CourseWorkIdPath workIdPath = new CourseWorkIdPath(courseId, categoryId, workId);
    	
    	DeleteCourseWorkCommand deleteCommand = new DeleteCourseWorkCommand(workIdPath);
    	
    	DeletedCourseWork deletedEvent = courseWorkService.deleteCourseWork(deleteCommand);

        if (!deletedEvent.isEntityFound()) {
            return new ResponseEntity<CourseWork>(HttpStatus.NOT_FOUND);
        }

        CourseWork work = CourseWork.fromCourseWorkDTO(deletedEvent.getDetails());

        if (deletedEvent.isDeletionCompleted()) {
            return new ResponseEntity<CourseWork>(work, HttpStatus.OK);
        }

        return new ResponseEntity<CourseWork>(work, HttpStatus.FORBIDDEN);
    }

}
