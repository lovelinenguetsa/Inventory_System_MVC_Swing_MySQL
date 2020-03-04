package model1;

import java.util.Date;

public class Users {
	
	private int id;
	private String name;
	private char[] pwd;
	private Date birthdate;
	private Gender gender;
	private static int count= 1;
	public Users(int id,String name, char[] pwd, Date birthdate2, Gender gender) {
		this(name,pwd, birthdate2, gender);
		this.id= id;
		
	}
	public Users(String name, char[] pwd, Date birthdate2, Gender gender) {
	   
		this.name = name;
		this.pwd = pwd;
		this.birthdate = birthdate2;
		this.gender = gender;
		this.id= count;
		count++;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public char[] getPwd() {
		return pwd;
	}
	public void setPwd(char[] pwd) {
		this.pwd = pwd;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	

}
