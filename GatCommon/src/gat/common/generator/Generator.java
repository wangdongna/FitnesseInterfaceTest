package gat.common.generator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import gat.common.excelservice.ExcelReader;
import gat.common.excelservice.FilterResponse;
import gat.common.generator.WikiGenerator;
import gat.common.generator.CodeGenerator;
import gat.common.generator.ReadTestCases;

public class Generator {
	
	private static final Log log = LogFactory.getLog(Generator.class);
	private String testCasePath = "D:\\Emma\\fitnesse\\DataFiles\\excel";
	
	public void filterExcel()
	{
		ExcelReader excelTmp = new ExcelReader();
		List<String> fileNames = new ArrayList<String>();
		List<String> sheetNames = new ArrayList<String>();
		fileNames = ReadTestCases.traverseFolder(testCasePath);
		String fileName = "";
		String filePath = "";
		String sheetName = "";
		
		for (int i = 0; i < fileNames.size(); i++)
		{		
			fileName = fileNames.get(i);
			//System.out.printf(fileName + "===\n");
			filePath = testCasePath + "\\" + fileName;
			sheetNames = excelTmp.showExcelSheets(filePath);
			
			for (int  j = 0; j < sheetNames.size(); j++)
			{
				sheetName = sheetNames.get(j);
				//System.out.printf(sheetName + "***\n");
				FilterResponse.filterRequestBody(filePath, sheetName);
			}
		}
		
	}
	
	public void generateCodeAndWiki()
	{
		CodeGenerator genetatorTmp = new CodeGenerator();
		WikiGenerator genetatorWikiTmp = new WikiGenerator();
		ExcelReader excelTmp = new ExcelReader();
		
		List<String> fileNames = new ArrayList<String>();
		List<String> sheetNames = new ArrayList<String>();
		fileNames = ReadTestCases.traverseFolder(testCasePath);
		String fileName = "";
		String filePath = "";
		String sheetName = "";
		
		for (int i = 0; i < fileNames.size(); i++)
		{
			fileName = fileNames.get(i);
			filePath = testCasePath + "\\" + fileName;
			sheetNames = excelTmp.showExcelSheets(filePath);
			
			for (int  j = 0; j < sheetNames.size(); j++)
			{
				sheetName = sheetNames.get(j);
				genetatorWikiTmp.generateWikiTable(fileName, sheetName);
				genetatorTmp.generateCode(fileName, sheetName);
			}
		}
	}
	
	public void generateCode()
	{
		CodeGenerator genetatorTmp = new CodeGenerator();
		//WikiGenerator genetatorWikiTmp = new WikiGenerator();
		ExcelReader excelTmp = new ExcelReader();
		
		List<String> fileNames = new ArrayList<String>();
		List<String> sheetNames = new ArrayList<String>();
		fileNames = ReadTestCases.traverseFolder(testCasePath);
		String fileName = "";
		String filePath = "";
		String sheetName = "";
		
		for (int i = 0; i < fileNames.size(); i++)
		{
			fileName = fileNames.get(i);
			filePath = testCasePath + "\\" + fileName;
			sheetNames = excelTmp.showExcelSheets(filePath);
			
			for (int  j = 0; j < sheetNames.size(); j++)
			{
				sheetName = sheetNames.get(j);
				//genetatorWikiTmp.generateWikiTable(fileName, sheetName);
				genetatorTmp.generateCode(fileName, sheetName);
			}
		}
	}
	
	public void generateWiki()
	{
		//CodeGenerator genetatorTmp = new CodeGenerator();
		WikiGenerator genetatorWikiTmp = new WikiGenerator();
		ExcelReader excelTmp = new ExcelReader();
		
		List<String> fileNames = new ArrayList<String>();
		List<String> sheetNames = new ArrayList<String>();
		fileNames = ReadTestCases.traverseFolder(testCasePath);
		String fileName = "";
		String filePath = "";
		String sheetName = "";
		
		for (int i = 0; i < fileNames.size(); i++)
		{
			fileName = fileNames.get(i);
			filePath = testCasePath + "\\" + fileName;
			sheetNames = excelTmp.showExcelSheets(filePath);
			
			for (int  j = 0; j < sheetNames.size(); j++)
			{
				sheetName = sheetNames.get(j);
				genetatorWikiTmp.generateWikiTable(fileName, sheetName);
				//genetatorTmp.generateCode(fileName, sheetName);
			}
		}
	}
	
	
}
