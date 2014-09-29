package org.vietngumn.schoolapp.event.course;

import org.vietngumn.schoolapp.event.QueryCommand;

public class CourseQueryCriteria extends QueryCommand {
	private String schoolYearId;

	public void setSchoolYearId(String schoolYearId) {
		this.schoolYearId = schoolYearId;
	}

	public String getSchoolYearId() {
		return schoolYearId;
	}
}
