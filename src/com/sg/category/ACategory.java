package com.sg.category;

import com.sg.discount.IDiscount;

public abstract class ACategory {
	
	int iMainParentCategory;
	
	ACategory(int iMainParentCategory)
	{
		this.iMainParentCategory=iMainParentCategory;
	}
	
	public int getMainParentCategory()
	{
		return iMainParentCategory;
	}
	abstract void setDiscount(IDiscount discount);	

}
