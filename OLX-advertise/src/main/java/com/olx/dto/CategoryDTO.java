package com.olx.dto;

public class CategoryDTO {
	private long id;
	private String category;

	public CategoryDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CategoryDTO(long id, String category) {
		super();
		this.id = id;
		this.category = category;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "CategoryDTO [id=" + id + ", category=" + category + "]";
	}

}
