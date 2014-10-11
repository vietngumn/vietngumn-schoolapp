package org.vietngumn.schoolapp.rest.domain;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.hateoas.ResourceSupport;
import org.vietngumn.schoolapp.event.courseWork.CourseWorkDTO;
import org.vietngumn.schoolapp.event.courseWork.CourseWorkIdPath;
import org.vietngumn.schoolapp.rest.controller.CourseWorkCategoryQueriesController;
import org.vietngumn.schoolapp.rest.controller.CourseWorkQueriesController;


@XmlRootElement
public class CourseWork extends ResourceSupport implements Serializable {
	private static final long serialVersionUID = 1L;

	private String workId;
	private String name;
	private String description;

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

	public CourseWorkDTO toCourseWorkDTO(final CourseWorkIdPath idPath) {
		CourseWorkDTO workDTO = new CourseWorkDTO();
		workDTO.setIdPath(idPath);
		workDTO.setWorkId(idPath.getWorkId());
		workDTO.setName(name);
		workDTO.setDescription(description);
		return workDTO;
	}

	public static CourseWork fromCourseWorkDTO(final CourseWorkDTO dto) {
		CourseWork courseWork = new CourseWork();
		courseWork.setWorkId(dto.getWorkId());
		courseWork.setName(dto.getName());
		courseWork.setDescription(dto.getDescription());

		final CourseWorkIdPath idPath = dto.getIdPath();
		courseWork.add(linkTo(CourseWorkQueriesController.class, idPath.getCourseId(), idPath.getCategoryId()).slash(dto.getWorkId()).withSelfRel());
		courseWork.add(linkTo(CourseWorkCategoryQueriesController.class, idPath.getCourseId()).slash(idPath.getCategoryId()).withRel("CourseWorkCategory"));
		return courseWork;
	}
	
	public static CourseWork fromQueriedCourseWorkDTO(final CourseWorkDTO dto) {
		CourseWork courseWork = new CourseWork();
		courseWork.setWorkId(dto.getWorkId());
		courseWork.setName(dto.getName());
		courseWork.setDescription(dto.getDescription());

		final CourseWorkIdPath idPath = dto.getIdPath();
		courseWork.add(linkTo(CourseWorkQueriesController.class, idPath.getCourseId(), idPath.getCategoryId()).slash(idPath.getWorkId()).withSelfRel());
		return courseWork;
	}
	
}
