package com.sg.test;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.sg.Items.Items;
import com.sg.Items.ItemsFactory;
import com.sg.category.Category;
import com.sg.category.CategoryFactory;
import com.sg.discount.BrandFactory;
import com.sg.discount.IDiscount;
import com.sg.utils.Utils;

public class SGCTest {
	
	String strMenwearFileName=null;
	String strWomenwearFileName=null;
	String strBrandDiscountFileName=null;
	String strInventoryFilename=null;
	Items item=null;
	IDiscount brandDidscount=null;
	int iCategoryDiscount=0;
	int iParentCategoryDiscount=0;
	int iFinalDiscount=0;
	double dFinalItemPrice=0.0;
	//String tempItems="1,5";
	String tempAry[]=null;
	double dFinalPrice=0.0;
	Map<String,Category> hmMenWearCategory=null;
	Map<String,Category> hmWomenWearCategory=null;
	Map<String,IDiscount> hmBrandDiscount=null;
	Map<String,Items> hmItem=null;

	public SGCTest() {
	}

	@Before
	public void setUp() throws Exception {
		  strMenwearFileName="menwearProperty.txt";
		  strWomenwearFileName="womenwearProperty.txt";
		  strBrandDiscountFileName="brandDiscountProperty.txt";
		  strInventoryFilename="inventoryProperty.txt";
		  
		  hmMenWearCategory=CategoryFactory.getCategoryDetails(0,strMenwearFileName); //0% discount in parent category for Men
		  
		  hmWomenWearCategory=CategoryFactory.getCategoryDetails(50,strWomenwearFileName);  //50% discount in parent category for Women
		  
		  hmBrandDiscount=BrandFactory.getBrandDetails(strBrandDiscountFileName);
		  
		  hmItem=ItemsFactory.getInventoryDetails(strInventoryFilename);
	}

	@Test
	public void test_MenWearCategory(){	
		hmMenWearCategory=CategoryFactory.getCategoryDetails(0,strMenwearFileName); //0% discount in parent category for Men
		assertEquals(30, hmMenWearCategory.get("Casuals").getDiscount().getDiscount());
	}
	
	@Test
	public void test_WomenWearCategory(){	
		hmWomenWearCategory=CategoryFactory.getCategoryDetails(50,strWomenwearFileName);  //50% discount in parent category for Women
		assertEquals(0, hmWomenWearCategory.get("Footwear").getDiscount().getDiscount());
	
	}
	
	@Test
	public void test_BrandWearCategory(){	
		hmBrandDiscount=BrandFactory.getBrandDetails(strBrandDiscountFileName);
		assertEquals(5, hmBrandDiscount.get("Adidas").getDiscount());
		
	}
	
	@Test
	public void test_InventoryList(){	
		  hmItem=ItemsFactory.getInventoryDetails(strInventoryFilename);
		  assertEquals("Provogue", hmItem.get("3").getBrandName());
	}
	
	@Test
	public void test_Inventory_1(){

		String tempAryValue="1,2,3,4";
		assertEquals(3860.0, getTemp(tempAryValue),0.0);
	}
	

	@Test
	public void test_Inventory_2(){
		
		String tempAryValue="1,5";
		assertEquals(2140.0, getTemp(tempAryValue),0.0);
	}
	
	private double getTemp(String tempAryValue)
	{
		String tempAry[]=tempAryValue.split(",");
		
		for(String strId:tempAry)
		{
			item=hmItem.get(strId);
			
			if(item==null)
				continue;
			//System.out.println("item="+item);
			brandDidscount=hmBrandDiscount.get(item.getBrandName());
			
			if(brandDidscount==null)
				continue;
			//System.out.println("brandDidscount="+brandDidscount.getDiscount());
			
			iCategoryDiscount=Utils.getCategoryDiscount(item.getCategoryName(), hmMenWearCategory, hmWomenWearCategory);
			
			iParentCategoryDiscount=Utils.getParentCategoryDiscount(item.getCategoryName(), hmMenWearCategory, hmWomenWearCategory);
			
			iFinalDiscount=Utils.getOverAllDiscount(iParentCategoryDiscount, brandDidscount.getDiscount(), iCategoryDiscount);
			
			dFinalItemPrice=Utils.applyDiscount(item.getPrice(), iFinalDiscount);
			
			dFinalPrice+=dFinalItemPrice;
		
		}
		
		return dFinalPrice;
	}
}
