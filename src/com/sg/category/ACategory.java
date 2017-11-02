package com.sg.category;

import com.sg.discount.IDiscount;

public abstract class ACategory {
	
	int iMainParentCategory=0;
	
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
