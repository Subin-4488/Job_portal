package model;

import java.sql.Date;

public class Applied {
	
	int seekerid;
	int providerid;
	int jobid;
	String status;
	Date datetime;
	
	
	public Date getDate() {
		return datetime;
	}
	public void setDate(Date datetime) {
		this.datetime = datetime;
	}
	public int getSeekerid() {
		return seekerid;
	}
	public int getProviderid() {
		return providerid;
	}
	public int getJobid() {
		return jobid;
	}
	public String getStatus() {
		return status;
	}
	public void setSeekerid(int seekerid) {
		this.seekerid = seekerid;
	}
	public void setProviderid(int providerid) {
		this.providerid = providerid;
	}
	public void setJobid(int jobid) {
		this.jobid = jobid;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @param seekerid
	 * @param providerid
	 * @param jobid
	 * @param status
	 * @param date
	 */
	public Applied(int seekerid, int providerid, int jobid, String status,Date date) {
		super();
		this.seekerid = seekerid;
		this.providerid = providerid;
		this.jobid = jobid;
		this.status = status;
		this.datetime = date;
	}
}
