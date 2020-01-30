package gui1;

import java.util.EventObject;

public class FormEventUser extends EventObject {
	
	private String name;
	private char[] pwd;
	private String birthdate;
	private String gender;

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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public FormEventUser(Object source, String name, char[] pwd, String birthdate, String gender) {
		super(source);
		this.name = name;
		this.pwd = pwd;
		this.birthdate = birthdate;
		this.gender = gender;
	}

	public FormEventUser(Object source) {
		super(source);
		
	}
	
}
