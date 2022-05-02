package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Job {
	String title="";
	String description="";
	String location="";
	String company="";
	float salary=0.0f;
	int experience=0;
	
	/**
	 * @param title
	 * @param description
	 * @param location
	 * @param company
	 * @param salary
	 * @param experience
	 * @param cid
	 */
	public Job(String title, String description, String location, float salary, int experience,int cid, String company) {
		super();
		this.title = title;
		this.description = description;
		this.location = location;
		this.company = company;
		this.salary = salary;
		this.experience = experience;
		this.cid = cid;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	int cid=0;
	
	

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getLocation() {
		return location;
	}

	public float getSalary() {
		return salary;
	}

	public int getExperience() {
		return experience;
	}

	public int getCid() {
		return cid;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public Job() {
		// TODO Auto-generated constructor stub
	}

	public static ObservableList<String>jobs=FXCollections.observableArrayList("Software Engineer",
			"Sales Associate",
			"Sales Representative",
			"Sales Manager",
			"Computer Scientist",
			"SQL Developer",
			"Web Designer",
			"Web Developer",
			"DevOps Engineer",
			"Computer Programmer",
			"Network Administrator",
			"Information Security Analyst",
			"Artificial Intelligence Engineer",
			"Cloud Architect",
			"IT Manager",
			"Technical Specialist");

}
