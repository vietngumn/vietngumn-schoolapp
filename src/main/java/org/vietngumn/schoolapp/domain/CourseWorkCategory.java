package org.vietngumn.schoolapp.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Transient;
import org.vietngumn.schoolapp.event.courseWorkCategory.CourseWorkCategoryDTO;
import org.vietngumn.schoolapp.event.courseWorkCategory.CourseWorkCategoryIdPath;
import org.vietngumn.schoolapp.helper.ListItem;
import org.vietngumn.schoolapp.helper.ListItemsCrudHelper;

public class CourseWorkCategory implements ListItem {

	@Transient
	private String courseId;
	private String categoryId;
	private String name;
	private String description;
	private Integer weight;
	
	@Transient
	private ListItemsCrudHelper<CourseWork> courseWorksCrudHelper;
	private List<CourseWork> courseWorks = new ArrayList<CourseWork>();
	
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
	
	@Override
	public String getListItemId() {
		return getCategoryId();
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
	
	private ListItemsCrudHelper<CourseWork> getCourseWorksCrudHelper() {
		if (this.courseWorksCrudHelper == null) {
			if (this.courseWorks == null) {
				this.courseWorks = new ArrayList<CourseWork>();
			}
			this.courseWorksCrudHelper = new ListItemsCrudHelper<CourseWork>(this.courseWorks);
		}
		return this.courseWorksCrudHelper;
	}
	
	public CourseWork getCourseWork(String workId) {
		return getCourseWorksCrudHelper().getItem(workId);
	}
	
	public CourseWork addCourseWork(CourseWork work) {
		return this.getCourseWorksCrudHelper().addItem(work);
	}
	
	public CourseWork updateCourseWork(CourseWork work) {
		return this.getCourseWorksCrudHelper().replaceItem(work);
	}
	
	public CourseWork deleteCourseWork(String workId) {
		return this.getCourseWorksCrudHelper().deleteItem(workId);
	}
	
	public List<CourseWork> getCourseWorks() {
		return Collections.unmodifiableList(this.courseWorks);
	}
	
	public List<CourseWork> getCourseWorks(List<String> workIds, List<String> schoolDateIds) {
		List<CourseWork> filteredByIdWorks = this.getCourseWorksCrudHelper().getItemsByIds(workIds);
		if (schoolDateIds == null) {
			return Collections.unmodifiableList(filteredByIdWorks);
		}
		
		List<CourseWork> filteredWorks = new ArrayList<CourseWork>();
		for (CourseWork work : filteredByIdWorks) {
			if (work.getSchoolDateId() == null || schoolDateIds.contains(work.getSchoolDateId())) {
				filteredWorks.add(work);
			}
		}
		return Collections.unmodifiableList(filteredWorks);
	}
	
	public CourseWorkCategoryDTO toCourseWorkCategoryDTO(final CourseWorkCategoryIdPath idPath) {
		CourseWorkCategoryDTO categoryDTO = new CourseWorkCategoryDTO();
		categoryDTO.setIdPath(idPath);
		BeanUtils.copyProperties(this, categoryDTO);
		return categoryDTO;
	}

	public static CourseWorkCategory fromCourseWorkCategoryDTO(CourseWorkCategoryDTO categoryDTO) {
		CourseWorkCategory category = new CourseWorkCategory();
		BeanUtils.copyProperties(categoryDTO, category);
		return category;
	}

}
