package org.vietngumn.schoolapp.event.studentGrade;

import org.vietngumn.schoolapp.event.DeletedEvent;

public class DeletedStudentGrade extends DeletedEvent {

	private StudentGradeIdPath studentGradeId;
	private StudentGradeDTO details;
	
	private DeletedStudentGrade(final StudentGradeIdPath studentGradeId) {
		this.studentGradeId = studentGradeId;
	}

	public DeletedStudentGrade(final StudentGradeIdPath studentGradeId, StudentGradeDTO details) {
		this(studentGradeId);
		this.details = details;
		this.deletionCompleted = true;
	}

	public StudentGradeIdPath getStudentGradeId() {
		return studentGradeId;
	}
	
	public StudentGradeDTO getDetails() {
		return details;
	}

	@Override
	public boolean isDeletionCompleted() {
		return deletionCompleted;
	}

	public static DeletedStudentGrade deletionForbidden(final StudentGradeIdPath studentGradeId, final StudentGradeDTO details) {
		DeletedStudentGrade response = new DeletedStudentGrade(studentGradeId, details);
		response.entityFound = true;
		response.deletionCompleted = false;
		return response;
	}

	public static DeletedStudentGrade notFound(final StudentGradeIdPath studentGradeId) {
		DeletedStudentGrade response = new DeletedStudentGrade(studentGradeId);
		response.entityFound = false;
		return response;
	}
}
