package gat.common.excelservice;

import java.util.List;
import java.util.ArrayList;

public class GatExcel {
	
	
/*	public static void main(String[] args) throws ParseException, IOException{
	
		getRequestBody("D:\\Emma\\fitnesse\\DataFiles\\excel\\customer.xlsx", "updateCustomer", "testUpdateCustomer1");

}*/
	
	public static String getRequestBody(String filePath, String sheetName, String id)
	{
		ExcelReader reader = new ExcelReader();
		
		String requestBody = null;
		
		List<List<String>> allVlaue = reader.readAllData(filePath, sheetName);
		List<String> rowValue = new ArrayList<String>();

		for (int i = 0; i < allVlaue.size(); i++)
		{
			rowValue = allVlaue.get(i);
			if (rowValue.get(0).equalsIgnoreCase(id) )
			{		
				requestBody = rowValue.get(1);
			}		
		}
		
		return requestBody;
	}
}
