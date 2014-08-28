package org.vietngumn.schoolapp.domain;

public class CourseId {
	private String level;
	private String section;
	private String schoolYear;
	
	public CourseId(String level, String section, String schoolYear) {
		this.level = level;
		this.section = section;
		this.schoolYear = schoolYear;
	}

	public String getId() {
		return level + section + schoolYear;
	}
	
	public String getName() {
		StringBuilder sb = new StringBuilder();
		sb.append("Grade ").append(level);
		sb.append(" - ");
		sb.append("Section ").append(section);
		sb.append(" - ");
		sb.append("School Year ").append(schoolYear);
		return sb.toString();
	}
	
	public String getLevel() {
		return level;
	}

	public String getSection() {
		return section;
	}

	public String getSchoolYear() {
		return schoolYear;
	}
	
}
