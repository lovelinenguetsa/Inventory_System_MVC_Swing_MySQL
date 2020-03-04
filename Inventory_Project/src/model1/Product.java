package model1;

import java.io.Serializable;

public class Product implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int count= 1;
	private int id;
	
	private ProductCategory productCategory ;
	
	private int quantity;
	private double originalPrice;
	private double Profit;
	private double SellingPrice;
	private String supplierContact;
      private SupplierCategory supCat;
	
	
	
	public Product(ProductCategory productCategory,int quantity, double originalPrice2, double sellingPrice2
			, double profit2,SupplierCategory supCat,  String supplierContact)
			{
		this.productCategory= productCategory;
		this.quantity= quantity;
		this.originalPrice= originalPrice2;
		this.SellingPrice= sellingPrice2;
		this.Profit= profit2;
		this.supCat= supCat;
		this.supplierContact= supplierContact;
		this.id= count;
		count++;
	}
	
	public String toString() {
		return id+":"+productCategory;
	}
	
	public Product(int id,ProductCategory productCategory,int quantity, double name1, double name2
			, double name3,SupplierCategory supCat,  String supplierContact)
			{
		this(productCategory, quantity, name1, name2,name3,supCat,supplierContact);
		this.id= id;
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
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

	public String getSupplierContact() {
		return supplierContact;
	}

	public void setSupplierContact(String supplierContact) {
		this.supplierContact = supplierContact;
	}

	public SupplierCategory getSupCat() {
		return supCat;
	}

	public void setSupCat(SupplierCategory supCat) {
		this.supCat = supCat;
	}
	
}
