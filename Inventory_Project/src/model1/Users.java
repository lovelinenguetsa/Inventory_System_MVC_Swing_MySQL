package model1;

import java.util.Date;

public class Users {
	
	private String name;
	private char[] pwd;
	private Date birthdate;
	private Gender gender;
	public Users(String name, char[] pwd, Date birthdate2, Gender gender) {
	
		this.name = name;
		this.pwd = pwd;
		this.birthdate = birthdate2;
		this.gender = gender;
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
