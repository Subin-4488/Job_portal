package model;

import java.io.InputStream;

public class JobSeeker {
	
	String name;
	String mobile;
	String city;
	String email;
	String dob;
	String password;
	String gender;
	InputStream avatar;
	
	
	
	
	/**
	 * @param name
	 * @param mobile
	 * @param city
	 * @param email
	 * @param dob
	 * @param password
	 * @param gender
	 * @param avatar
	 */
	public JobSeeker(String name, String mobile, String city, String email, String dob, String password, String gender,
			InputStream avatar) {
		super();
		this.name = name;
		this.mobile = mobile;
		this.city = city;
		this.email = email;
		this.dob = dob;
		this.password = password;
		this.gender = gender;
		this.avatar = avatar;
	}




	public String getName() {
		return name;
	}




	public String getMobile() {
		return mobile;
	}




	public String getCity() {
		return city;
	}




	public String getEmail() {
		return email;
	}




	public String getDob() {
		return dob;
	}




	public String getPassword() {
		return password;
	}




	public String getGender() {
		return gender;
	}




	public InputStream getAvatar() {
		return avatar;
	}




	public void setName(String name) {
		this.name = name;
	}




	public void setMobile(String mobile) {
		this.mobile = mobile;
	}




	public void setCity(String city) {
		this.city = city;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public void setDob(String dob) {
		this.dob = dob;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	public void setGender(String gender) {
		this.gender = gender;
	}




	public void setAvatar(InputStream avatar) {
		this.avatar = avatar;
	}




	public JobSeeker() {
		// TODO Auto-generated constructor stub
	}
}
