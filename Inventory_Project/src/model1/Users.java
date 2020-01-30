package model1;



public class Users {
	
	private String name;
	private char[] pwd;
	private String birthdate;
	private Gender gender;
	public Users(String name, char[] pwd, String birthdate, Gender gender) {
	
		this.name = name;
		this.pwd = pwd;
		this.birthdate = birthdate;
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
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	

}
