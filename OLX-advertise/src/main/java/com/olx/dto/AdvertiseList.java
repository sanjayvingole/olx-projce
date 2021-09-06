package com.olx.dto;

import java.util.List;

public class AdvertiseList {

	private List<AdvertiseDTO> advertises;

	public AdvertiseList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdvertiseList(List<AdvertiseDTO> advertises) {
		super();
		this.advertises = advertises;
	}

	public List<AdvertiseDTO> getAdvertises() {
		return advertises;
	}

	public void setAdvertises(List<AdvertiseDTO> advertises) {
		this.advertises = advertises;
	}

	@Override
	public String toString() {
		return "AdvertiseList [advertises=" + advertises + "]";
	}

}
