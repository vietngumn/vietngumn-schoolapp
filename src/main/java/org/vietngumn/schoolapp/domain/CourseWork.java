package org.vietngumn.schoolapp.domain;

import org.springframework.beans.BeanUtils;
import org.vietngumn.schoolapp.event.courseWork.CourseWorkDTO;
import org.vietngumn.schoolapp.event.courseWork.CourseWorkIdPath;
import org.vietngumn.schoolapp.helper.ListItem;

public class CourseWork implements ListItem {

	private String workId;
	private String name;
	private String schoolDateId;
	private String description;
	private Integer weight;
	
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

	public String getSchoolDateId() {
		return schoolDateId;
	}

	public void setSchoolDateId(String schoolDateId) {
		this.schoolDateId = schoolDateId;
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

	public CourseWorkDTO toCourseWorkDTO(CourseWorkIdPath idPath) {
		CourseWorkDTO workDTO = new CourseWorkDTO();
		workDTO.setIdPath(idPath);
		BeanUtils.copyProperties(this, workDTO);
		return workDTO;
	}

	public static CourseWork fromCourseWorkDTO(CourseWorkDTO workDTO) {
		CourseWork work = new CourseWork();
		BeanUtils.copyProperties(workDTO, work);
		return work;
	}

}
