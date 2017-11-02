
import java.util.Map;
import java.util.Scanner;

import com.sg.Items.Items;
import com.sg.Items.ItemsFactory;
import com.sg.category.Category;
import com.sg.category.CategoryFactory;
import com.sg.discount.BrandFactory;
import com.sg.discount.IDiscount;
import com.sg.utils.Utils;


public class ProductMain {

	public static void main(String[] args) {
	
		String strMenwearFileName="menwearProperty.txt";
		String strWomenwearFileName="womenwearProperty.txt";
		String strBrandDiscountFileName="brandDiscountProperty.txt";
		String strInventoryFilename="inventoryProperty.txt";
		Items item=null;
		IDiscount brandDidscount=null;
		int iCategoryDiscount=0;
		int iParentCategoryDiscount=0;
		int iFinalDiscount=0;
		double dFinalItemPrice=0.0;
		//String tempItems="1,5";
		String tempAry[]=null;
		double dFinalPrice=0.0;
		
		Scanner sc=new Scanner(System.in); 
		System.out.println("Enter number of Inventory=");
		int iNumberInventory=sc.nextInt();
		
		//System.out.println("iNumberInventory="+iNumberInventory);
		System.out.println("Enter the Inventory IDs:");
		 
		String strItemAry[]=new String[iNumberInventory];
		for(int i=0; i<iNumberInventory; i++)
		{
			strItemAry[i]=sc.next();
		}
				
		sc.close();
		
		Map<String,Category> hmMenWearCategory=CategoryFactory.getCategoryDetails(0,strMenwearFileName);
		//System.out.println("hmMenWearCategory="+hmMenWearCategory);
		
		Map<String,Category> hmWomenWearCategory=CategoryFactory.getCategoryDetails(50,strWomenwearFileName);
		//System.out.println("hmWomenWearCategory="+hmWomenWearCategory);
		
		Map<String,IDiscount> hmBrandDiscount=BrandFactory.getBrandDetails(strBrandDiscountFileName);
		//System.out.println("hmBrandDiscount="+hmBrandDiscount);
		
		Map<String,Items> hmItem=ItemsFactory.getInventoryDetails(strInventoryFilename);
		//System.out.println("hmItem="+hmItem);

		System.out.println("Expected Output:");
		for(String tempItems:strItemAry)
		{
			tempAry=tempItems.split(",");
			dFinalPrice=0.0;
			
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
				//System.out.println("iCategoryDiscount=="+iCategoryDiscount);
				
				iParentCategoryDiscount=Utils.getParentCategoryDiscount(item.getCategoryName(), hmMenWearCategory, hmWomenWearCategory);
				//System.out.println("iParentCategoryDiscount=="+iParentCategoryDiscount);
				
				iFinalDiscount=Utils.getOverAllDiscount(iParentCategoryDiscount, brandDidscount.getDiscount(), iCategoryDiscount);
				//System.out.println("iFinalDiscount=="+iFinalDiscount);
				
				dFinalItemPrice=Utils.applyDiscount(item.getPrice(), iFinalDiscount);
				//System.out.println("dFinalItemPrice=="+dFinalItemPrice);
				
				dFinalPrice+=dFinalItemPrice;
			
			}//end-for-loop-strId
		
				System.out.println(dFinalPrice);
		}//end-for-loop-tempItems
		
	}//end-Main-Class

}//end-class
