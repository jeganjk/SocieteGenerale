package com.sg.discount;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.sg.utils.Utils;


public class BrandFactory {

	private static Map<String,IDiscount> hmItems=new HashMap<String,IDiscount>();
	
	
	private static void loadValues(String fileName)
	{
		if(fileName!=null)
		{
			List<String> listBrand  = new ArrayList<String>();
			String strValue[]=null;
			URL path = ClassLoader.getSystemResource(fileName);
			
			//read file into stream, try-with-resources
			try (Stream<String> stream = Files.lines(Paths.get(path.toURI()))) {
				
				listBrand=stream.filter(line -> !line.startsWith("#")).collect(Collectors.toList());
			} catch (IOException e) {
				e.printStackTrace();
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			}
			
			for(String single:listBrand)
			{
				strValue=single.split(",");
				//int discount=new Integer(strValue[1].replaceAll("%", ""));
				int iDiscount=Utils.getIntDiscount(strValue[1]);
				IDiscount objDiscount=new Discount(0,iDiscount,strValue[0]);
				hmItems.put(strValue[0], objDiscount);
			}
				

		}
	}
	
	public static Map<String,IDiscount> getBrandDetails(String fileName)
	{
		loadValues(fileName);
		return hmItems;
	}
}
