package gat.common.generator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import gat.common.excelservice.ExcelReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class WikiGenerator {
	
	private static final Log log = LogFactory.getLog(WikiGenerator.class);
	private String caseFilePath = "D:\\Emma\\fitnesse\\DataFiles\\excel";
	private String wikiGetFilePath = "D:\\Emma\\fitnesse\\FitNesseRoot\\PopGetTestProject";
	private String wikiPostFilePath = "D:\\Emma\\fitnesse\\FitNesseRoot\\PopPostTestProject";
	
	public static String commonHeader = "!define TEST_SYSTEM {slim} \n" + "!path D:\\Emma\\fitnesse\\FitnessTest\\bin \n" 
	+ "!path D:\\Emma\\fitnesse\\GatCommon\\bin \n" + "!path D:\\Emma\\fitnesse\\FitnessTest\\lib\\httpclient-4.3.1.jar \n"
	+ "!path D:\\Emma\\fitnesse\\FitnessTest\\lib\\httpcore-4.3.jar \n" + "!path D:\\Emma\\fitnesse\\FitnessTest\\lib\\commons-logging-1.1.1.jar \n"
	+ "!path D:\\Emma\\fitnesse\\FitnessTest\\lib\\jackson-all-1.9.5.jar \n" + "!path D:\\Emma\\fitnesse\\FitnessTest\\lib\\jackson-annotations-2.8.0.jar \n"
	+ "!path D:\\Emma\\fitnesse\\FitnessTest\\lib\\jackson-core-2.8.1.jar \n" + "!path D:\\Emma\\fitnesse\\FitnessTest\\lib\\jackson-databind-2.6.7.jar \n"
	+ "!path D:\\Emma\\fitnesse\\FitnessTest\\lib\\poi-3.12.jar \n" + "!path D:\\Emma\\fitnesse\\FitnessTest\\lib\\poi-ooxml-3.12.jar \n"
	+ "!path D:\\Emma\\fitnesse\\FitnessTest\\lib\\poi-ooxml-schemas-3.12.jar \n" + "!path D:\\Emma\\fitnesse\\FitnessTest\\lib\\xmlbeans-2.3.0.jar \n\n"
	+ "|import| \n" + "|fitnesse.slim.test| \n"
	+ "|import| \n" + "|gat.http.helper| \n"
	+ "|import| \n" + "|gat.common.jsonhelper| \n"
	+ "|import| \n" + "|gat.common.excelservice| \n";
	
	/**
	    * 创建单个文件
	    * @param destFileName 文件名
	    * @return 创建成功返回true，否则返回false
	    */
	    public File createFile(String destFileName) {
	        File file = new File(destFileName);
	        if (file.exists()) {
	        	file.delete();
	        }
	        if (destFileName.endsWith(File.separator)) {
	            log.info("创建单个文件" + destFileName + "失败，目标不能是目录！");
	            return null;
	        }
	        if (!file.getParentFile().exists()) {
	            log.info("目标文件所在路径不存在，准备创建。。。");
	            if (!file.getParentFile().mkdirs()) {
	                log.info("创建目录文件所在的目录失败！");
	                return null;
	            }
	        }
	        // ´´½¨Ä¿±êÎÄ¼þ
	        try {
	            if (file.createNewFile()) {
	                log.info("创建单个文件" + destFileName + "成功！");
	                return file;
	            } else {
	                log.info("创建单个文件" + destFileName + "失败！");
	                return null;
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	            log.info("创建单个文件" + destFileName + "失败！");
	            return null;
	        }
	    }
	    
	    public List<String> generateWikiTableTitle(String fileName, String sheetName)
	    {
	    	List<String> wikiTableTitle = new ArrayList<String>();
	    	String filePath = caseFilePath + "/" + fileName;
	    	ExcelReader readerTmp = new ExcelReader();
	    	List<List<String>>  result = readerTmp.readAllData(filePath, sheetName);
	    	
	    	for (int i = 0; i < result.get(0).size(); i++)
			{
				wikiTableTitle.add(result.get(0).get(i));
			}
	    	
	    	return wikiTableTitle;
	    }
	    
	    public void generateWikiTable(String fileName, String sheetName)
	    {
	    	ExcelReader readerTmp = new ExcelReader();
	    	String filePath = caseFilePath + "/" + fileName;
	    	List<String> wikiPageContent = new ArrayList<String>();
	    	List<List<String>>  result = readerTmp.readAllData(filePath, sheetName);
	    	String suiteDestFileName = "";
			String testDestFileName = "";	
	    	
			String[] testPage = sheetName.split(" ");
			String[] suitePage = fileName.split("\\.");
			String suiteName = suitePage[0] + "Api";
			String suiteWikiContent = "---\n" + "Help: " + suitePage[0].toLowerCase() + " api\n" + "Suite\n" + "Suites: " + suitePage[0].toLowerCase() + "\n---\n" + "!1 Test suite X \n!contents -R2 -g -p -f -h";			
			String titleTmp = "---\n" + "Help: " + sheetName + "\n" + "Suites: " + testPage[0] + ", " + testPage[1] + "\n" + "Test\n---\n";
			String testName = "";
			for (int i = 0; i < testPage.length; i++)
			{			
				testName = testName +  testPage[i].substring(0, 1).toUpperCase() + testPage[i].substring(1);
			}
			
			if (testPage[0].equals("get"))
			{
				suiteDestFileName = wikiGetFilePath + "/" + suiteName + ".wiki";
				testDestFileName = wikiGetFilePath + "/" + suiteName + "/" + testName + ".wiki";	
			}
			else
			{
				suiteDestFileName = wikiPostFilePath + "/" + suiteName + ".wiki";
				testDestFileName = wikiPostFilePath + "/" + suiteName + "/" + testName + ".wiki";	
			}
				
			
			WriteStringToFile(suiteDestFileName, suiteWikiContent);
			
			String methodName = "|" + sheetName + "|";
			String titleLine = "|";
			
					
			for (int i = 0; i < result.get(0).size(); i++)
			{
				String content = result.get(0).get(i);
				if (content.contains("#"))
				{
					titleLine = titleLine + content.toLowerCase() + "|";
				}
								
				if (content.contains("&"))
				{
					titleLine = titleLine + content.replace("&", "").toLowerCase() + "|";
				}
				
				if (content.contains("$"))
				{
					titleLine = titleLine + content.replace("$", "") + "?" + "|";
				}
			}
			
			wikiPageContent.add(titleTmp);
			wikiPageContent.add(commonHeader);
			wikiPageContent.add(methodName);
			wikiPageContent.add(titleLine);
			
			
			for (int i = 1; i < result.size(); i++)
			{
				String contentLine = "|";
				
				for (int j = 0; j < result.get(0).size(); j++)
				{
					contentLine = contentLine + result.get(i).get(j) + "|";			
				}
				
				wikiPageContent.add(contentLine);
			}
			
			WriteListToFile(testDestFileName, wikiPageContent);
	    }
	    

	    public void WriteListToFile(String destFileName, List<String> content)
	    {	    
	    	
	    	try{
	    	File file = createFile(destFileName);
	    	
	    	// get the content in bytes
	    	OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(file),"utf-8");
	    	BufferedWriter bw = new BufferedWriter(write);
	    	
	    	for (int i = 0; i < content.size(); i++)
	    	{
	    		bw.write(content.get(i));	
	    		bw.write("\n");	
	    	} 	

	    	bw.close();
	    	}
	    	catch (IOException e) {
	    		   e.printStackTrace();
	    		  }
	    }
	    
	    public void WriteStringToFile(String destFileName, String content)
	    {	    
	    	
	    	try{
	    	File file = createFile(destFileName);
	    	
	    	// get the content in bytes
	    	FileWriter fw = new FileWriter(file.getAbsoluteFile());
	    	BufferedWriter bw = new BufferedWriter(fw);

	    	bw.write(content);	    		
	    	bw.close();
	    	}
	    	catch (IOException e) {
	    		   e.printStackTrace();
	    		  }
	    }
	
}
