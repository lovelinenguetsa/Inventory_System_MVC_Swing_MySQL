package gui1;
import java.util.EventObject;

public class FormEvent extends EventObject {

	private int ProductCat;
	private int quantity;
	private String originalPrice;
	private String Profit;
	private String SellingPrice;
	private String Suppliername;
	private String supplierContact;
	
	

	public FormEvent(Object source) {
		super(source);
	}

	public FormEvent(Object source, int ProductCat, int quantity, String originalPrice, String Profit,
			String SellingPrice, String Suppliername,String supplierContact ) {
		
		super(source);

		this.ProductCat= ProductCat;
		this.quantity= quantity;
		this.originalPrice= originalPrice;
		this.Profit= Profit;
		this.SellingPrice= SellingPrice;
		this.Suppliername= Suppliername;
		this.supplierContact= supplierContact;
		
	}

	public int getProductname() {
		return ProductCat;
	}

	public void setProductname(int productname) {
		ProductCat = productname;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(String originalPrice) {
		this.originalPrice = originalPrice;
	}

	public String getProfit() {
		return Profit;
	}

	public void setProfit(String profit) {
		Profit = profit;
	}

	public String getSellingPrice() {
		return SellingPrice;
	}

	public void setSellingPrice(String sellingPrice) {
		SellingPrice = sellingPrice;
	}

	public String getSuppliername() {
		return Suppliername;
	}

	public void setSuppliername(String suppliername) {
		Suppliername = suppliername;
	}

	public String getSupplierContact() {
		return supplierContact;
	}

	public void setSupplierContact(String supplierContact) {
		this.supplierContact = supplierContact;
	}
	
	
}