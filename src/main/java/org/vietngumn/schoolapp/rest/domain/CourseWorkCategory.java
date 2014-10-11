package org.vietngumn.schoolapp.rest.domain;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.hateoas.ResourceSupport;
import org.vietngumn.schoolapp.event.courseWorkCategory.CourseWorkCategoryDTO;
import org.vietngumn.schoolapp.event.courseWorkCategory.CourseWorkCategoryIdPath;
import org.vietngumn.schoolapp.rest.controller.CourseQueriesController;
import org.vietngumn.schoolapp.rest.controller.CourseWorkCategoryQueriesController;
import org.vietngumn.schoolapp.rest.controller.CourseWorkQueriesController;


@XmlRootElement
public class CourseWorkCategory extends ResourceSupport implements Serializable {
	private static final long serialVersionUID = 1L;

	private String categoryId;
	private String name;
	private String description;

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

	public CourseWorkCategoryDTO toCourseWorkCategoryDTO(final CourseWorkCategoryIdPath idPath) {
		CourseWorkCategoryDTO categoryDTO = new CourseWorkCategoryDTO();
		categoryDTO.setIdPath(idPath);
		categoryDTO.setCategoryId(idPath.getCategoryId());
		categoryDTO.setName(name);
		categoryDTO.setDescription(description);
		return categoryDTO;
	}

	public static CourseWorkCategory fromCourseWorkCategoryDTO(final CourseWorkCategoryDTO dto) {
		CourseWorkCategory workCategory = new CourseWorkCategory();
		workCategory.setCategoryId(dto.getCategoryId());
		workCategory.setName(dto.getName());
		workCategory.setDescription(dto.getDescription());
		
		final CourseWorkCategoryIdPath idPath = dto.getIdPath();
		workCategory.add(linkTo(CourseWorkCategoryQueriesController.class, idPath.getCourseId()).slash(idPath.getCategoryId()).withSelfRel());
		workCategory.add(linkTo(CourseQueriesController.class).slash(idPath.getCourseId()).withRel("Course"));
		workCategory.add(linkTo(CourseWorkQueriesController.class, idPath.getCourseId(), idPath.getCategoryId()).withRel("CourseWorks"));
		return workCategory;
	}
	
	public static CourseWorkCategory fromQueriedWorkCategoryDTO(final CourseWorkCategoryDTO dto) {
		CourseWorkCategory workCategory = new CourseWorkCategory();
		workCategory.setCategoryId(dto.getCategoryId());
		workCategory.setName(dto.getName());
		workCategory.setDescription(dto.getDescription());
		workCategory.add(linkTo(CourseWorkCategoryQueriesController.class, dto.getIdPath().getCourseId()).slash(dto.getCategoryId()).withSelfRel());
		return workCategory;
	}
}
