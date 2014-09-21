package org.vietngumn.schoolapp.domain;

import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Transient;
import org.vietngumn.schoolapp.event.courseWork.CourseWorkDTO;
import org.vietngumn.schoolapp.helper.ListItem;

public class CourseWork implements ListItem {

	@Transient
	private String courseId;
	@Transient
	private String categoryId;
	
	private String workId;
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
	
	public String getWorkId() {
		return workId;
	}
	
	@Override
	public String getListItemId() {
		return getWorkId();
	}

	public void setWorkId(String workId) {
		this.workId = workId;
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

	public CourseWorkDTO toCourseWorkDTO() {
		CourseWorkDTO workDTO = new CourseWorkDTO();
		BeanUtils.copyProperties(this, workDTO);
		return workDTO;
	}

	public static CourseWork fromCourseWorkDTO(CourseWorkDTO workDTO) {
		CourseWork work = new CourseWork();
		BeanUtils.copyProperties(workDTO, work);
		return work;
	}

}
