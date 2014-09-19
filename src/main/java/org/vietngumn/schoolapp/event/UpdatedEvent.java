package org.vietngumn.schoolapp.event;

public class UpdatedEvent {
	protected boolean entityFound = true;
	protected boolean updateCompleted;

	public boolean isEntityFound() {
		return entityFound;
	}

	public boolean isUpdateCompleted() {
		return updateCompleted;
	}
}
