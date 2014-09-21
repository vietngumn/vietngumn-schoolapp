package org.vietngumn.schoolapp.rest.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.hateoas.ResourceSupport;
import org.vietngumn.schoolapp.event.courseWork.CourseWorkDTO;


@XmlRootElement
public class CourseWork extends ResourceSupport implements Serializable {
	private static final long serialVersionUID = 1L;

	private String courseId;
	private String categoryId;
	private String workId;
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

	public String getWorkId() {
		return workId;
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

	public CourseWorkDTO toCourseWorkDTO() {
		CourseWorkDTO workDTO = new CourseWorkDTO();
		workDTO.setCourseId(courseId);
		workDTO.setCategoryId(categoryId);
		workDTO.setName(name);
		workDTO.setDescription(description);
		return workDTO;
	}

	public static CourseWork fromCourseWorkDTO(CourseWorkDTO workDTO) {
		CourseWork courseWork = new CourseWork();
		courseWork.setCourseId(workDTO.getCourseId());
		courseWork.setCategoryId(workDTO.getCategoryId());
		courseWork.setWorkId(workDTO.getWorkId());
		courseWork.setName(workDTO.getName());
		courseWork.setDescription(workDTO.getDescription());
//		courseWork.add(linkTo(OrderQueriesController.class).slash(courseWork.getCourseWorkId()).withSelfRel());

		return courseWork;
	}
	
}
