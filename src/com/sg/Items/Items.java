package com.sg.Items;

public class Items {

	String id;
	String brandName;
	String categoryName;
	double price;
	double discountPrice;
 
	public Items(String strFullDetails)
	{
		if(strFullDetails!=null)
		{
			String strItem[]=strFullDetails.split(",");
			if(strItem.length==4)
			{
				this.id = strItem[0].trim();
				this.brandName = strItem[1].trim();
				this.categoryName = strItem[2].trim();
				this.price = new Double(strItem[3].trim());
			}
		}
	}
	public Items(String id, String brandName, String categoryName, double price) {
		super();
		this.id = id;
		this.brandName = brandName;
		this.categoryName = categoryName;
		this.price = price;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public double getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(double discountPrice) {
		this.discountPrice = discountPrice;
	}
	
	@Override
	public String toString() {
		return "Items [id=" + id + ", brandName=" + brandName
				+ ", categoryName=" + categoryName + ", price=" + price
				+ ", discountPrice=" + discountPrice + "]";
	}
	
	
}
