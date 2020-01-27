import java.util.EventObject;

public class FormEvent extends EventObject {
	
	private String name;
	private String occupation;
	private int AgeCategory ;
	private String empCat;
	private String taxId;
	private boolean usCitizen;
	private String gender;
	
	
	
	public String getGender() {
		return gender;
	}

	public String getEmpCat() {
		return empCat;
	}

	public int getAgeCategory() {
		return AgeCategory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public FormEvent(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}

	public FormEvent(Object source, String name ,String occupation, int ageCat, String empCat,
			String taxid, boolean usCitizen, String gender) {
		this(source);
		this.name= name;
		this.occupation= occupation;
		this.AgeCategory= ageCat;
		this.empCat= empCat;
		this.taxId=taxid;
		this.usCitizen= usCitizen;
			this.gender= gender;	
	}

	public String getTaxId() {
		return taxId;
	}

	public boolean isUsCitizen() {
		return usCitizen;
	}

}
