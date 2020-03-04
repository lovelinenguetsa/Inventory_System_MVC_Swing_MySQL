package gui1;

import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.EventObject;

public class ActionEventUser extends ActionEvent {
	private int id;
	private String name;
	private char[] pwd;
	private Date birthdate;
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

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	int id1= 1;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	String command = "";

	public ActionEventUser(Object source, String name, char[] pwd, Date birthdate, String gender) {
		super(source, name.length(), name );
		this.name = name;
		this.pwd = pwd;
		this.birthdate = birthdate;
		this.gender = gender;
	}

	public ActionEventUser(Object source, int id, String command) {
		  
		super(source, id, command );
		
	}
	
}
