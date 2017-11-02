package com.sg.category;

import com.sg.discount.Discount;
import com.sg.discount.IDiscount;
import com.sg.utils.Utils;

public class Category extends ACategory {

	 String name=null;
	 IDiscount discount=null;
	 Category subCategory=null;
	
	 public Category(int iMainParentCategory, String strFullDetails)
	 {
		 super(iMainParentCategory);
		 
		 if(strFullDetails!=null)
			{
				String strItem[]=strFullDetails.split(",");
				if(strItem.length==4)
				{
					this.name = strItem[0];
					int iDiscount=0;
					
					if(strItem[2].length() ==0)
						 iDiscount=Utils.getIntDiscount(strItem[1]);
					else
						 iDiscount=Utils.getIntDiscount(strItem[3]);
					
					int iParent=Utils.getIntDiscount(strItem[1]);
					this.discount=new Discount(iParent,iDiscount,strItem[0]);
					
					if(strItem[2].length() >0){ //Sub Subcategory
						IDiscount subDiscount=new Discount(iParent,iDiscount,strItem[2]);
						subCategory=new Category(iMainParentCategory,strItem[2],subDiscount);
					}
					
				}
			}
	 }
	 
	public Category(int iMainParentCategory,String name, IDiscount discount) {
		super(iMainParentCategory);
		this.name = name;
		this.discount = discount;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public IDiscount getDiscount() {
		return discount;
	}
	@Override
	public void setDiscount(IDiscount discount) {
		this.discount = discount;
	}
	public Category getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(Category subCategory) {
		this.subCategory = subCategory;
	}

	@Override
	public String toString() {
		return "Category [name=" + name + ", discount=" + discount
				+ ", subCategory=" + subCategory + "]";
	}

	 
}
