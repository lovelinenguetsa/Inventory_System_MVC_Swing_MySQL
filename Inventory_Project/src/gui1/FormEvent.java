package gui1;
import java.util.EventObject;

public class FormEvent extends EventObject {

	private int ProductCat;
	private int quantity;
	private double originalPrice;
	private double Profit;
	private double SellingPrice;
	private String Suppliername;
	private String supplierContact;
	
	

	public FormEvent(Object source) {
		super(source);
	}

	public FormEvent(Object source, int ProductCat, int quantity, double originalPrice, double Profit,
			double SellingPrice, String Suppliername,String supplierContact ) {
		
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

	public double getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(double originalPrice) {
		this.originalPrice = originalPrice;
	}

	public double getProfit() {
		return Profit;
	}

	public void setProfit(double profit) {
		Profit = profit;
	}

	public double getSellingPrice() {
		return SellingPrice;
	}

	public void setSellingPrice(double sellingPrice) {
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