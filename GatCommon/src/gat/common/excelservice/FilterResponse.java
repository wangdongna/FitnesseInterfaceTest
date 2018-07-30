package gat.common.excelservice;

import java.util.List;
import java.util.ArrayList;

public class FilterResponse {
	
	public static void filterRequestBody(String filePath, String sheetName)
	{
		//System.out.printf("in filterRequestBody ++++\n");
		ExcelReader reader = new ExcelReader();		
		List<List<String>> result = reader.readAllData(filePath, sheetName);

		for (int i = 1; i < result.size(); i++)
		{			
			for (int j = 0; j < result.get(0).size(); j++)
			{
				String contentLine = result.get(i).get(j);		
				//System.out.printf("in for j ###\n");
				if (contentLine.contains("Version"))
				{				
					String tmp = contentLine.replaceAll(",\"Version\":\\d{7}", "");
					//System.out.printf("in for j @@@\n");
					reader.writeData(filePath, sheetName, i, j, tmp);
				}
			}
			
		}
		
	}
}
