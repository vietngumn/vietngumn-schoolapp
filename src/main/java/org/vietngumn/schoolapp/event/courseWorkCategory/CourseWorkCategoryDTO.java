package org.vietngumn.schoolapp.event.courseWorkCategory;

public class CourseWorkCategoryDTO {

	private CourseWorkCategoryIdPath idPath;
	private String categoryId;
	private String name;
	private String description;

	public CourseWorkCategoryIdPath getIdPath() {
		return idPath;
	}

	public void setIdPath(CourseWorkCategoryIdPath idPath) {
		this.idPath = idPath;
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

}
