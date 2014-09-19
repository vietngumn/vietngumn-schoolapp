package org.vietngumn.schoolapp.domain;

import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Transient;
import org.vietngumn.schoolapp.event.courseWorkCategory.CourseWorkCategoryDTO;

public class CourseWorkCategory {

	@Transient
	private String courseId;
	private String categoryId;
	private String name;
	private String description;
	private Integer weight;
	
	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryId() {
		return this.categoryId;
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

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public boolean canBeDeleted() {
		return true;
	}

	public CourseWorkCategoryDTO toCourseWorkCategoryDTO() {
		CourseWorkCategoryDTO dto = new CourseWorkCategoryDTO();
		dto.setCourseId(courseId);
		dto.setCategoryId(categoryId);
		dto.setName(name);
		dto.setDescription(description);
		return dto;
	}

	public static CourseWorkCategory fromCourseWorkCategoryDTO(CourseWorkCategoryDTO categoryDTO) {
		CourseWorkCategory category = new CourseWorkCategory();

		BeanUtils.copyProperties(categoryDTO, category);

		return category;
	}
}
