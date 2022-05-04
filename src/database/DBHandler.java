package database;
import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;

import model.Applied;
import model.Job;
import model.JobProvider;
import model.JobSeeker;
import model.Resume;
import others.AlertClass;

public class DBHandler extends DBConfigs {

	Connection connection=null;
	
	public boolean getConnection() {
		try {
			Class.forName(dbDriver);
			connection=DriverManager.getConnection(url,dbUser,dbPass);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (connection!=null) {
			//System.out.println("Connection established");
			return true;
		}
		else {
			System.out.println("Connection not established");
			return false;
		}
	}
	
	public boolean validateSeeker(String email,String pass){
		if (getConnection()) {
			int count=0;
			
			try {
			String query="select * from jobseeker where email=? and password=?";
			PreparedStatement pst=connection.prepareStatement(query);
			pst.setString(1, email);
			pst.setString(2, pass);
			
			ResultSet rs=pst.executeQuery();
			while (rs.next()) {
				count++;
				rs.next();
			}
			}catch(Exception e) {e.printStackTrace();}
			
			if (count==1) return true;
			else	return false;
		}
		else {
			return false;
		}
	}
	
	public boolean validateProvider(String email,String pass) throws SQLException {
		if (getConnection()) {
			int count=0;
			try {
			String query="select * from jobprovider where email=? and password=?";
			PreparedStatement pst=connection.prepareStatement(query);
			pst.setString(1, email);
			pst.setString(2, pass);
			
			ResultSet rs=pst.executeQuery();
			
			while (rs.next()) {
				count++;
				rs.next();
			}
			}catch(Exception e) {e.printStackTrace();}
			if (count==1) return true;
			else	return false;
				
		}
		else {
			return false;
		}
	}
	
	public void registerJobseeker(JobSeeker seeker) throws SQLException {
		if (getConnection()) {
			try {
			String queryString="insert into jobseeker(name,mobile,city,email,dob,password,gender) values(?,?,?,?,?,?,?)";
			PreparedStatement pst=connection.prepareStatement(queryString);
			
			pst.setString(1, seeker.getName());
			pst.setString(2, seeker.getMobile());
			pst.setString(3, seeker.getCity());
			pst.setString(4, seeker.getEmail());
			pst.setString(5, seeker.getDob());
			pst.setString(6, seeker.getPassword());
			pst.setString(7, seeker.getGender());
			
			pst.executeUpdate();
			new AlertClass("Registration Success", 'C');
			}catch(Exception e) {new AlertClass("Registration Unsuccess", 'E');}
		}
	}
	
	public void registerJobprovider(JobProvider provider) throws SQLException {
		if (getConnection()) {
			try {
			String queryString="insert into jobprovider(name,email,city,type,password) values(?,?,?,?,?)";
			PreparedStatement pst=connection.prepareStatement(queryString);
			
			pst.setString(1, provider.getName());
			pst.setString(2, provider.getEmail());
			pst.setString(3, provider.getCity());
			pst.setString(4, provider.getType());
			pst.setString(5, provider.getPassword());
			
			pst.executeUpdate();
			new AlertClass("Registration Success", 'C');
			} catch (Exception e) {
				new AlertClass("Registration Unsuccess", 'E');
			}
		}
	}
	
	public void insertResume(Resume resume) {
		if (getConnection()) {
			try {
				String queryString="insert into resume(id,file) values(?,?)";
				PreparedStatement pst=connection.prepareStatement(queryString);
				
				pst.setInt(1, resume.getId());
				pst.setBinaryStream(2, resume.getFs());
				pst.executeUpdate();
				
				
			} catch (Exception e) {
				new AlertClass("Resume error", 'E');
				e.printStackTrace();
			}
		}
	}
	
	public int getId(String table,String email) {
		int returnid=-1;
		if (getConnection()) { 
			try {
			String qString="select id from "+table+" where email=?";
			PreparedStatement pst=connection.prepareStatement(qString);
			pst.setString(1, email);
			
			ResultSet rs=pst.executeQuery();
			
			int count =0;
			if (rs.next()) {
				count++;
			}
			if (count==1) returnid=rs.getInt(1);
			
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return returnid;
	}
	
	public void updateProfile(String table,String attribute,String value,int id) {
		if (getConnection()) {
			try {
				String query="update "+table+" set "+attribute+"=? where id="+id;
				PreparedStatement pst=connection.prepareStatement(query);
				pst.setString(1, value);
				pst.executeUpdate();
				new AlertClass("Updation successful", 'C');
				
			} catch (Exception e) {
				
			}
		}
	}
	
	public JobSeeker getSeekerObject(int id) {
		JobSeeker seeker=null;
		
		if (getConnection()) {
			try {
				String query="select name,mobile,city,email,dob,gender,avatar from jobseeker where id="+id;
				PreparedStatement pst=null;
				pst=connection.prepareStatement(query);
				ResultSet rSet=pst.executeQuery();
				if (rSet.next()) {
					seeker=new JobSeeker();
					seeker.setName(rSet.getString("name"));
					seeker.setMobile(rSet.getString("mobile"));
					seeker.setCity(rSet.getString("city"));
					seeker.setEmail(rSet.getString("email"));
					seeker.setDob(rSet.getString("dob"));
					seeker.setGender(rSet.getString("gender"));
					seeker.setAvatar(rSet.getBinaryStream("avatar"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return seeker;
	}
	
	public JobProvider getProviderObject(int id) {
		JobProvider provider=null;
		
		if (getConnection()) {
			try {
				String query="select name,city,email,type from jobprovider where id="+id;
				PreparedStatement pst=null;
				pst=connection.prepareStatement(query);
				ResultSet rSet=pst.executeQuery();
				if (rSet.next()) {
					provider=new JobProvider();
					provider.setName(rSet.getString("name"));
					provider.setCity(rSet.getString("city"));
					provider.setEmail(rSet.getString("email"));
					provider.setType(rSet.getString("type"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return provider;
	}
	
	public Resume getResumeObject(int id) {
		Resume resume=null;
		
		if (getConnection()) {
			try {
				String query="select school,stream,cgpa,file from resume where id="+id;
				PreparedStatement pst=null;
				pst=connection.prepareStatement(query);
				ResultSet rSet=pst.executeQuery();
				if (rSet.next()) {
					resume=new Resume();
					resume.setSchool(rSet.getString("school"));
					resume.setStream(rSet.getString("stream"));
					resume.setCgpa(rSet.getString("cgpa"));
					resume.setFs(rSet.getBinaryStream("file"));
				}
			} catch (Exception e) {	
				e.printStackTrace();
			}
		}
		
		return resume;
	}
	
	public void uploadImage(int id,FileInputStream fis) {
		if (getConnection()) {
			try {
				String query="update jobseeker"+" set avatar"+"=? where id="+id;
				PreparedStatement pst=connection.prepareStatement(query);
				pst.setBinaryStream(1, fis);
				pst.executeUpdate();
				new AlertClass("Updation successful", 'C');
				
				
			} catch (Exception e) {
				new AlertClass("Image upload error", 'E');
				e.printStackTrace();
			}
		}
	}
	
	public void postJob(Job job) {
		if (getConnection()) {
			try {
			String queryString="insert into job(title,description,location,cid,salary,experience) values(?,?,?,?,?,?)";
			PreparedStatement pst=connection.prepareStatement(queryString);
			
			pst.setString(1,job.getTitle());
			pst.setString(2,job.getDescription());
			pst.setString(3,job.getLocation());
			pst.setInt(4,job.getCid());
			pst.setFloat(5,job.getSalary());
			pst.setInt(6, job.getExperience());
			
			
			pst.executeUpdate();
			new AlertClass("Job posted Successully", 'C');
			} catch (Exception e) {
				e.printStackTrace();
				new AlertClass("Job post Unsuccess", 'E');
			}
		}
	}
	
	public ArrayList<Job> getPostJobs(String a,String filter){
		ArrayList<Job> filter_job=new ArrayList<>();
		
		if (getConnection()) {
			try {
				String query="";
				if(a==null && filter==null) {
					query="select title,description,location,salary,id,cid from job";
				}
				else if (!a.equals("salary")) {
					query="select title,description,location,salary,id,cid from job where "+a+"='"+filter+"'";
				}
				else {
					query="select title,description,location,salary,id,cid from job order by salary desc";
				}		
				PreparedStatement pst=connection.prepareStatement(query);
				ResultSet rs=pst.executeQuery();
				
				if (rs.next()) {
					do {
						Job job=new Job();
						job.setTitle(rs.getString("title"));
						job.setDescription(rs.getString("description"));
						job.setLocation(rs.getString("location"));
						job.setSalary(rs.getFloat("salary"));
						job.setId(rs.getInt("id"));   
						job.setCid(rs.getInt("cid"));
						filter_job.add(job);
					} while (rs.next());
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		return filter_job;
	}
	
	public void deleteJob(int id) {
		if (getConnection()) {
			try {
				String query="delete from job where id="+id;
				PreparedStatement pst=connection.prepareStatement(query);
				pst.executeUpdate();
				new AlertClass("Job deleted successfully", 'C');
			} catch (Exception e) {
				new AlertClass("Failed", 'E');
			}
		}
	}
	
	public void applyForJob(Applied applied) {
		if (getConnection()) {
			try {
				String query="insert into applied(seekerid,providerid,jobid,status,date) values(?,?,?,?,?)";
				PreparedStatement pst=connection.prepareStatement(query);
				pst.setInt(1, applied.getSeekerid());
				pst.setInt(2, applied.getProviderid());
				pst.setInt(3, applied.getJobid());
				pst.setString(4, applied.getStatus());
				pst.setDate(5,applied.getDate());
				
				pst.executeUpdate();
				new AlertClass("Applied Successfully", 'C');
			} catch (Exception e) {	
				e.printStackTrace();
				new AlertClass("Failed!!", 'E');
			}
		}
	}
	
	public ArrayList<Applied> getAppliedJobs(int id,char c,int jid){  //c=='S' for seeker  and 'P' for provider
		ArrayList<Applied> list=new ArrayList<>();
		if (getConnection()) {
			try {
				PreparedStatement pst;
				String query;
				ResultSet rs;
				if (c=='S') {
					query="select providerid,jobid,status,date from applied where seekerid="+id;
					pst=connection.prepareStatement(query);
					rs=pst.executeQuery();
					while (rs.next()) {
						list.add(new Applied(id, rs.getInt("providerid"), rs.getInt("jobid"), rs.getString("status"), rs.getDate("date")));
					}
				}
				else {
					query="select seekerid,status,date from applied where providerid=? and jobid=?";
					pst=connection.prepareStatement(query);
					pst.setString(1, Integer.toString(id));
					pst.setString(2, Integer.toString(jid));
					rs=pst.executeQuery();
					while (rs.next()) {
						list.add(new Applied(rs.getInt("seekerid"), id, jid, rs.getString("status"), rs.getDate("date")));
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}		
		}
		return list;
	}
	
	public int applicantCount(int jid,int pid) {
		int ret=0;
		if (getConnection()) {
			try {
				String query="select count(distinct seekerid) from applied where jobid=? and providerid=?";
				PreparedStatement pst=connection.prepareStatement(query);
				
				pst.setString(1, Integer.toString(jid));
				pst.setString(2, Integer.toString(pid));
				ResultSet rs=pst.executeQuery();
				if (rs.next())
					ret=rs.getInt(1);
			} catch (Exception e) {
				
			}
		}
		return ret;
	}
	
}
