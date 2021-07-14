package com.olx.dto;

import javax.persistence.Id;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "olx model holds the info about the cateegories")
public class CategoryDTO {
	@Id
	@ApiModelProperty(value = "id of category")
	private Integer id;
	@ApiModelProperty(value = "Name of category")
	private String name;
	private String description;

	public CategoryDTO() {
		// TODO Auto-generated constructor stub
	}


	public CategoryDTO(Integer id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}



	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public String toString() {
		return "CategoryDTO [id=" + id + ", name=" + name + ", description=" + description + "]";
	}

}
