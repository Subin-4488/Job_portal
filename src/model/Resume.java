package model;

import java.io.FileInputStream;
import java.io.InputStream;

public class Resume {
	int id;
	InputStream fs=null;
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

	public InputStream getFs() {
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

	public void setFs(InputStream fs) {
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
	public Resume(int id, InputStream fs, String school, String stream, String cgpa) {
		super();
		this.id = id;
		this.fs = fs;
		this.school = school;
		this.stream = stream;
		this.cgpa = cgpa;
	}
	
}
