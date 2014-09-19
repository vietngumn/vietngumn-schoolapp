package org.vietngumn.schoolapp.event;

public class DeletedEvent {
	protected boolean entityFound = true;
	protected boolean deletionCompleted = true;

	public boolean isEntityFound() {
		return entityFound;
	}

	public boolean isDeletionCompleted() {
		return deletionCompleted;
	}
}
