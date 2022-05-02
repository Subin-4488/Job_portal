package model;

public class JobProvider {
	String name;
	String email;
	String city;
	String type;
	String password;
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getCity() {
		return city;
	}
	public String getType() {
		return type;
	}
	public String getPassword() {
		return password;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @param name
	 * @param email
	 * @param city
	 * @param type
	 * @param password
	 */
	public JobProvider(String name, String email, String city, String type, String password) {
		super();
		this.name = name;
		this.email = email;
		this.city = city;
		this.type = type;
		this.password = password;
	}
	
	public JobProvider() {
		
	}
	
}
