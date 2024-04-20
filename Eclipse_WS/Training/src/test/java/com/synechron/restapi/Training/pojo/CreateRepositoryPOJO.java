package com.synechron.restapi.Training.pojo;

public class CreateRepositoryPOJO {

	private String name;
	private String description;
	private String homePage;
	private Boolean type;
	
	
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
	
	public String getHomePage() {
		return homePage;
	}
	
	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}
	
	public Boolean getType() {
		return type;
	}
	
	public void setType(Boolean type) {
		this.type = type;
	}
	
}
