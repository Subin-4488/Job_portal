package model;

import java.io.FileInputStream;

public class Resume {
	int id;
	FileInputStream fs=null;
	String school;
	String stream;
	String cgpa;
	
	/**
	 * @param id
	 * @param fs
	 */
	public Resume() {
		
	}

	public int getId() {
		return id;
	}

	public FileInputStream getFs() {
		return fs;
	}

	public String getSchool() {
		return school;
	}

	public String getStream() {
		return stream;
	}

	public String getCgpa() {
		return cgpa;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setFs(FileInputStream fs) {
		this.fs = fs;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}

	public void setCgpa(String cgpa) {
		this.cgpa = cgpa;
	}

	/**
	 * @param id
	 * @param fs
	 * @param school
	 * @param stream
	 * @param cgpa
	 */
	public Resume(int id, FileInputStream fs, String school, String stream, String cgpa) {
		super();
		this.id = id;
		this.fs = fs;
		this.school = school;
		this.stream = stream;
		this.cgpa = cgpa;
	}
	
}
