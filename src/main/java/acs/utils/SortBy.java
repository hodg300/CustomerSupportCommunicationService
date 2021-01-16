package acs.utils;

public enum SortBy {
	ID("id"),
	EMAIL("email"),
	NAME("name"),
	CREATED_TIMESTAMP("createdTimeStamp"),
	CLOSING_TIMESTAMP("closingTimeStamp");

	private final String sortBy;

	SortBy(final String sortBy){
		this.sortBy=sortBy;
	}

	@Override
	public String toString() {
		return sortBy;
	}
}
