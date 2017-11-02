package com.sg.category;

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

public class CategoryFactory {
	
	private static Map<String,Category> hmItems=null;
	//String fileName;
	
	private static void loadValues(int iMainParentCategory,String fileName)
	{
		if(fileName!=null)
		{
			List<String> listRowsCategory  = new ArrayList<String>();
			hmItems=new HashMap<String,Category>();
			URL path = ClassLoader.getSystemResource(fileName);
			
			//read file into stream, try-with-resources
			try (Stream<String> stream = Files.lines(Paths.get(path.toURI()))) {
				listRowsCategory=stream.filter(line -> !line.startsWith("#")).collect(Collectors.toList());
				//listCategory=stream.map(s-> new Category(s)).collect(Collectors.toList());
			} catch (IOException e) {
				e.printStackTrace();
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			}
			
			for (String strRowCategory : listRowsCategory)
			{
				Category category=new Category(iMainParentCategory,strRowCategory);
				
				if(category.getSubCategory()!=null && category.getSubCategory().getName().length()>0)
				{
					hmItems.put(category.getSubCategory().getName(), category.getSubCategory());
				}else
				{
					hmItems.put(category.getName(), category);
				}
				
			}	

		}
	}
	
	public static Map<String,Category> getCategoryDetails(int iMainParentCategory,String fileName)
	{
		loadValues(iMainParentCategory,fileName);
		return hmItems;
	}

}
