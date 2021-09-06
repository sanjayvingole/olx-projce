package com.olx.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.olx.dto.CategoryDTO;

@Entity
@Table(name = "advertises")

public class AdvertiseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String title;
	private String description;
	private double price;
	private int category;
	private int status;
	@Column(name = "created_date")
	private LocalDate createdDate;
	@Column(name = "modified_date")
	private LocalDate modifiedDate;
	@Column(name = "username")
	private String username;

	public AdvertiseEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdvertiseEntity(String title, String description, double price, int category,int status, LocalDate createdDate,
			LocalDate modifiedDate, String username) {
		super();
		this.title = title;
		this.description = description;
		this.price = price;
		this.category = category;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.username = username;
		this.status=status;
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

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
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
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "AdvertiseEntity [id=" + id + ", title=" + title + ", description=" + description + ", price=" + price
				+ ", category=" + category + ", status=" + status + ", createdDate=" + createdDate + ", modifiedDate="
				+ modifiedDate + ", username=" + username + "]";
	}

}
