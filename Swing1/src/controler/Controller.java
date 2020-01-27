package controler;

import java.util.List;

import gui.FormEvent;
import model.AgeCategory;
import model.Database;
import model.EmploymentCategory;
import model.Gender;
import model.Person;

public class Controller {

	Database db = new Database();
	
	public List<Person> getPeople(){
		return db.getPeople();
	}

	public void addPerson(FormEvent e) {
		String name = e.getName();
		String occupation = e.getOccupation();
		int ageCatId = e.getAgeCategory();
		boolean isUs = e.isUsCitizen();
		String empCat = e.getEmpCat();
		String gender = e.getGender();
		String taxId = e.getTaxId();

		AgeCategory ageCategory= null;

		switch (ageCatId) {
		case 0: ageCategory= AgeCategory.child;

			break;
		case 1:ageCategory= AgeCategory.adult;

			break;
		case 2:ageCategory= AgeCategory.senior;

			break;

		default:
			break;
		}
		
		EmploymentCategory emploCategory= null;
		
		switch (empCat) {
		case "employed":emploCategory= EmploymentCategory.employed;
			
			break;
		case "unemployed":emploCategory= EmploymentCategory.unemployed;
		
		break;
		case "self-employed":emploCategory= EmploymentCategory.selfEmployed;
		
		break;

		default:System.err.println(empCat);
			break;
		}
		
		Gender genderCat ;
		
		if (gender.equals("male")) { 
			
			genderCat= Gender.male;
			
		}else {
			genderCat= Gender.female;
		}

		Person person = new Person(name, occupation, ageCategory, emploCategory, taxId, isUs, genderCat);

		db.addPerson(person);
	}
}
