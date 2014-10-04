package org.vietngumn.schoolapp.rest.domain;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.hateoas.ResourceSupport;
import org.vietngumn.schoolapp.event.courseWork.CourseWorkDTO;
import org.vietngumn.schoolapp.rest.controller.CourseWorkCategoryQueriesController;
import org.vietngumn.schoolapp.rest.controller.CourseWorkQueriesController;


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
		workDTO.setName(name);
		workDTO.setDescription(description);
		return workDTO;
	}

	public static CourseWork fromCourseWorkDTO(CourseWorkDTO dto) {
		CourseWork courseWork = new CourseWork();
		courseWork.setWorkId(dto.getWorkId());
		courseWork.setName(dto.getName());
		courseWork.setDescription(dto.getDescription());

		courseWork.add(linkTo(CourseWorkQueriesController.class, dto.getIdPath().getCourseId(), dto.getIdPath().getCategoryId()).slash(dto.getWorkId()).withSelfRel());
		courseWork.add(linkTo(CourseWorkCategoryQueriesController.class, dto.getIdPath().getCourseId()).slash(dto.getIdPath().getCategoryId()).withRel("CourseWorkCategory"));
		return courseWork;
	}
	
	public static CourseWork fromQueriedCourseWorkDTO(CourseWorkDTO dto) {
		CourseWork courseWork = new CourseWork();
		courseWork.setWorkId(dto.getWorkId());
		courseWork.setName(dto.getName());
		courseWork.setDescription(dto.getDescription());

		courseWork.add(linkTo(CourseWorkQueriesController.class, dto.getIdPath().getCourseId(), dto.getIdPath().getCategoryId()).slash(dto.getWorkId()).withSelfRel());
		return courseWork;
	}
	
}
