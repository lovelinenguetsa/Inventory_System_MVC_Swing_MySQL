package model1;

import java.io.Serializable;

public class Product implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int count= 0;
	private int id;
	
	private ProductCategory productCategory ;
	
	private int quantity;
	private String originalPrice;
	private String Profit;
	private String SellingPrice;
	private String supplierContact;
      private SupplierCategory supCat;
	
	
	
	public Product(ProductCategory productCategory,int quantity, String originalPrice, String SellingPrice
			, String Profit,SupplierCategory supCat,  String supplierContact)
			{
		this.productCategory= productCategory;
		this.quantity= quantity;
		this.originalPrice= originalPrice;
		this.SellingPrice= SellingPrice;
		this.Profit= Profit;
		this.supCat= supCat;
		this.supplierContact= supplierContact;
		this.id= count;
		count++;
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
