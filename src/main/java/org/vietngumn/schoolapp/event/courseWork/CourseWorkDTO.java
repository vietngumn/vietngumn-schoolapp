package org.vietngumn.schoolapp.event.courseWork;


public class CourseWorkDTO {
	private CourseWorkIdPath idPath;
	private String workId;
	private String name;
	private String description;
	
	public CourseWorkIdPath getIdPath() {
		return idPath;
	}

	public void setIdPath(CourseWorkIdPath idPath) {
		this.idPath = idPath;
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

}
