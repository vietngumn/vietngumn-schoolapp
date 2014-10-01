package org.vietngumn.schoolapp.event.studentGrade;

import org.vietngumn.schoolapp.event.QueryCommand;

public class StudentGradeQueryCriteria extends QueryCommand {
	private String schoolYearId;

	public void setSchoolYearId(String schoolYearId) {
		this.schoolYearId = schoolYearId;
	}

	public String getSchoolYearId() {
		return schoolYearId;
	}
}
