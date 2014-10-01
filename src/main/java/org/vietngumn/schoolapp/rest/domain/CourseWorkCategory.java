package org.vietngumn.schoolapp.rest.domain;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.hateoas.ResourceSupport;
import org.vietngumn.schoolapp.event.course.CourseDTO;
import org.vietngumn.schoolapp.event.courseWorkCategory.CourseWorkCategoryDTO;
import org.vietngumn.schoolapp.rest.controller.CourseQueriesController;
import org.vietngumn.schoolapp.rest.controller.CourseWorkCategoryQueriesController;
import org.vietngumn.schoolapp.rest.controller.StudentRecordQueriesController;


@XmlRootElement
public class CourseWorkCategory extends ResourceSupport implements Serializable {
	private static final long serialVersionUID = 1L;

	private String courseId;
	private String categoryId;
	private String name;
	private String description;

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CourseWorkCategoryDTO toCourseWorkCategoryDTO() {
		CourseWorkCategoryDTO categoryDTO = new CourseWorkCategoryDTO();
		categoryDTO.setCourseId(courseId);
		categoryDTO.setCategoryId(categoryId);
		categoryDTO.setName(name);
		categoryDTO.setDescription(description);
		return categoryDTO;
	}

	public static CourseWorkCategory fromCourseWorkCategoryDTO(CourseWorkCategoryDTO dto) {
		CourseWorkCategory workCategory = new CourseWorkCategory();
		workCategory.setCategoryId(dto.getCategoryId());
		workCategory.setName(dto.getName());
		workCategory.setDescription(dto.getDescription());
		
		workCategory.add(linkTo(CourseWorkCategoryQueriesController.class, dto.getCourseId()).slash(dto.getCategoryId()).withSelfRel());
		workCategory.add(linkTo(CourseQueriesController.class).slash(dto.getCourseId()).withRel("Course"));
		workCategory.add(linkTo(CourseWorkCategoryQueriesController.class, dto.getCourseId()).withRel("CourseWorks"));
		return workCategory;
	}
	
	public static CourseWorkCategory fromQueriedWorkCategoryDTO(CourseWorkCategoryDTO dto) {
		CourseWorkCategory workCategory = new CourseWorkCategory();
		workCategory.setCategoryId(dto.getCategoryId());
		workCategory.setName(dto.getName());
		workCategory.setDescription(dto.getDescription());
		workCategory.add(linkTo(CourseWorkCategoryQueriesController.class, dto.getCourseId()).slash(dto.getCategoryId()).withSelfRel());
		return workCategory;
	}
}
