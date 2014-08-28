package org.vietngumn.schoolapp.event;

public class UpdateResponse {
	protected boolean entityFound = true;
	protected boolean updateCompleted;

	public boolean isEntityFound() {
		return entityFound;
	}

	public boolean isUpdateCompleted() {
		return updateCompleted;
	}
}
