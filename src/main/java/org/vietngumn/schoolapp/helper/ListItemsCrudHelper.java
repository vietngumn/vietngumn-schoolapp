package org.vietngumn.schoolapp.helper;

import java.util.List;

public class ListItemsCrudHelper<T extends ListItem> {
	
	final private List<T> sourceItems;
	
	public ListItemsCrudHelper(List<T> items) {
		this.sourceItems = items;
	}
	
	public T getItem(String itemId) {
		for (T item : this.sourceItems) {
			if (item.getListItemId().equalsIgnoreCase(itemId)) {
				return item;
			}
		}
		return null;
	}
	
	public void addItem(T item) {
		this.sourceItems.add(item);
	}
	
	public T replaceItem(T newItem) {
		for (int i = 0; i < this.sourceItems.size(); i++) {
			T item = this.sourceItems.get(i);
			if (item.getListItemId().equalsIgnoreCase(newItem.getListItemId())) {
				this.sourceItems.set(i, newItem);
				return newItem;
			}
		}
		return null;
	}
	
	public T deleteItem(String itemId) {
		T itemToBeDeleted = null;
		for (T item : this.sourceItems) {
			if (item.getListItemId().equalsIgnoreCase(itemId)) {
				itemToBeDeleted = item;
				break;
			}
		}
		
		if (itemToBeDeleted != null) {
			this.sourceItems.remove(itemToBeDeleted);
		}
		return itemToBeDeleted;
	}
	
}
