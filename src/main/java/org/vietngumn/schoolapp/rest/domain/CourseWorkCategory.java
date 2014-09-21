package org.vietngumn.schoolapp.rest.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.hateoas.ResourceSupport;
import org.vietngumn.schoolapp.event.courseWorkCategory.CourseWorkCategoryDTO;


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

	public static CourseWorkCategory fromCourseWorkCategoryDTO(CourseWorkCategoryDTO categoryDTO) {
		CourseWorkCategory courseWorkCategory = new CourseWorkCategory();
		courseWorkCategory.setCategoryId(categoryDTO.getCategoryId());
		courseWorkCategory.setName(categoryDTO.getName());
		courseWorkCategory.setDescription(categoryDTO.getDescription());
//		courseWork.add(linkTo(OrderQueriesController.class).slash(courseWork.getCourseWorkId()).withSelfRel());

		return courseWorkCategory;
	}
}
