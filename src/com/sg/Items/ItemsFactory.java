package com.sg.Items;
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


public class ItemsFactory {

	private static Map<String,Items> hmItems=new HashMap<String,Items>();
	String fileName;
	
	
	private static void loadValues(String fileName)
	{
		if(fileName!=null)
		{
			List<Items> listItems  = new ArrayList<Items>();
			URL path = ClassLoader.getSystemResource(fileName);
			
			//read file into stream, try-with-resources
			try (Stream<String> stream = Files.lines(Paths.get(path.toURI()))) {

				listItems=stream.filter(line -> !line.startsWith("#")).map(s-> new Items(s)).collect(Collectors.toList());

			} catch (IOException e) {
				e.printStackTrace();
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			}
			
			if(listItems!=null && listItems.size()>0)
				hmItems=listItems.stream().collect(Collectors.toMap(item-> item.getId(), item->item));

		}
	}
	
	public static Map<String,Items> getInventoryDetails(String fileName)
	{
		loadValues(fileName);
		return hmItems;
	}
}
