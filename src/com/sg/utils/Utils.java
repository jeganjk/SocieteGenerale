package com.sg.utils;

import java.util.Map;

import com.sg.category.Category;

public class Utils {
	
	private static Utils _instance;
	
	static
	{
		if (_instance == null) {
			_instance = new Utils();
		}
	}
	
	public static Utils getInstanceDC() { 
		if (_instance == null) // Single Checked 
		{ 
			synchronized (Utils.class) 
			{
				if (_instance == null) { // Double checked 
					_instance = new Utils(); 
				} 
			} 
		} 
		   return _instance; 
	 }
	
	public static int getIntDiscount(String strDiscountValue)
	{
		int iDiscount=0;
		strDiscountValue=strDiscountValue.trim();
		
		if(strDiscountValue!=null && !(strDiscountValue.equalsIgnoreCase("none") || strDiscountValue.equalsIgnoreCase("null")))
			iDiscount=new Integer(strDiscountValue.replaceAll("%", ""));
		
		return iDiscount;
	}
	
	public static int getOverAllDiscount(int iParent,int iBrandDiscount, int iCategoryDiscount)
	{
		return Math.max(iParent,Math.max(iBrandDiscount,iCategoryDiscount));
	}

	
	public static double applyDiscount(double dOriginalPrice, int iDiscount)
	{
		//System.out.println("Apply Discount dOriginalPrice="+dOriginalPrice+" iDiscount="+iDiscount);
		double dAfterDiscount=0.0;
		//System.out.println(dOriginalPrice*(iDiscount/100));
		if(dOriginalPrice>=0 && iDiscount>=0 )
			dAfterDiscount=	dOriginalPrice-((dOriginalPrice*iDiscount)/100);	
		
		return dAfterDiscount;
	}

	
	public static int getCategoryDiscount(String strCategoryName,
			                              Map<String,Category> hmMenWearCategory,
			                              Map<String,Category> hmWomenWearCategory)
	{
		int iDiscount=0;
		
		if(hmMenWearCategory.containsKey(strCategoryName)){
			iDiscount=((Category)hmMenWearCategory.get(strCategoryName)).getDiscount().getDiscount();
		}else if(hmWomenWearCategory.containsKey(strCategoryName)){
			iDiscount=((Category)hmWomenWearCategory.get(strCategoryName)).getDiscount().getDiscount();
		}
		
		return iDiscount;
	}
	
	public static int getParentCategoryDiscount(String strCategoryName,
									            Map<String,Category> hmMenWearCategory,
									            Map<String,Category> hmWomenWearCategory)
	{
		int iDiscount=0;
		
		if(hmMenWearCategory.containsKey(strCategoryName)){
			iDiscount=((Category)hmMenWearCategory.get(strCategoryName)).getMainParentCategory();
		}else if(hmWomenWearCategory.containsKey(strCategoryName)){
			iDiscount=((Category)hmWomenWearCategory.get(strCategoryName)).getMainParentCategory();
		}
	
	  return iDiscount;
	}
}
