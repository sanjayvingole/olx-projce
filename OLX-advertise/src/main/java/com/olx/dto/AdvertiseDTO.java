package com.olx.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class AdvertiseDTO {
	private long id;
	private String title;
	private String description;
	private double price;
	private int categories;
	private int status;
	private LocalDate createdDate;
	private LocalDate modifiedDate;
	private String username;
	private String postedBy;

	public AdvertiseDTO() {
		super();
	}

	public AdvertiseDTO(long id, String title, String description, double price, int categories,int status, LocalDate createdDate,
			LocalDate modifiedDate, String username, String postedBy) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.price = price;
		this.categories = categories;
		this.status=status;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.username = username;
		this.postedBy = postedBy;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getCategories() {
		return categories;
	}

	public void setCategories(int categories) {
		this.categories = categories;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDate getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDate modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "AdvertiseDTO [id=" + id + ", title=" + title + ", description=" + description + ", price=" + price
				+ ", categories=" + categories + ", status=" + status + ", createdDate=" + createdDate
				+ ", modifiedDate=" + modifiedDate + ", username=" + username + ", postedBy=" + postedBy + "]";
	}

}
