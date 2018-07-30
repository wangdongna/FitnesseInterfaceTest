package gat.common.excelservice;

import java.util.List;
import java.util.ArrayList;
import gat.common.excelservice.ExtensionType;
import java.util.Iterator;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.xmlbeans.impl.regex.ParseException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {

	private Integer sheetCounts=0;
	
	public List<String> showExcelSheets(String filePath){
		Object workBook = getWorkBook(filePath, ExtensionType.XLSX);
		String name = "";
		List<String> sheetNames = new ArrayList<String>();
		
		for (int i = 0; i < sheetCounts; i++)
		{
			name = getWorkSheetName(workBook, i, ExtensionType.XLSX);
			sheetNames.add(name);
		}
		
		return sheetNames;
        
    }
    
    /**
     * 
     * @param filePath
     * @param sheetName
     * @param rowIndex
     * @return
     */
	public void writeData(String filePath, String sheetName, Integer rowIndex, Integer columnIndex, String inputText)
    {
		XSSFWorkbook workBook = (XSSFWorkbook)getWorkBook(filePath, ExtensionType.XLSX);
		
        try 
        {
        	XSSFSheet workSheet=(XSSFSheet)this.getWorkSheet(workBook, sheetName,ExtensionType.XLSX);
    		XSSFRow row =workSheet.getRow(rowIndex);
    		XSSFCell cell  = row.getCell(columnIndex);
    		System.out.printf(cell.getStringCellValue());
    		System.out.printf("\n");
    		System.out.printf(inputText);
    		cell.setCellValue(inputText);		
		}
       catch (Exception e)
       {
    	   e.printStackTrace();
       }
        
       FileOutputStream out = null;
       
       try {
    	   System.out.printf("\n" + filePath + "\n");
           out = new FileOutputStream(filePath);
           System.out.printf("\n ^^^" + out + "\n");
           workBook.write(out);
       } 
       catch (IOException e) {
           e.printStackTrace();
       } 
       finally {
           try {
        	   out.flush();
               out.close();
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
   } 

    
    /**
     * 
     * @param filePath
     * @param sheetName
     * @param rowIndex
     * @return
     */
	public List<String> readData(String filePath,String sheetName,Integer rowIndex)
    {
        ArrayList<String> rowValue=new ArrayList<String>();
        try 
        {
        	Object workBook=getWorkBook(filePath, ExtensionType.XLSX);  
        	XSSFSheet workSheet=(XSSFSheet)this.getWorkSheet(workBook, sheetName,ExtensionType.XLSX);
    		XSSFRow row =workSheet.getRow(rowIndex);
   		    for (Iterator<Cell> cit = row.cellIterator(); cit.hasNext();)
		        {
		            XSSFCell cell = (XSSFCell) cit.next();
		            String value = getValue(cell);
		            rowValue.add(value);
		        }
		}
       catch (Exception e)
       {
    	   
       }
       return rowValue;
   }

	
	/**
	 * 
	 * @param filePath
	 * @param sheetName
	 * @return
	 */
	public List<List<String>> readAllData(String filePath,String sheetName)
	{
		ArrayList<List<String>> result=new ArrayList<List<String>>();
        try 
        {
        	Object workBook=getWorkBook(filePath, ExtensionType.XLSX);  
        	XSSFSheet workSheet=(XSSFSheet)this.getWorkSheet(workBook, sheetName,ExtensionType.XLSX);
        	for (Iterator<Row> rit = workSheet.rowIterator(); rit.hasNext();)
        	{
                ArrayList<String> rowValue=new ArrayList<String>();
                XSSFRow row = (XSSFRow) rit.next();

   		    for (Integer i=0;i<row.getPhysicalNumberOfCells();i++ )
		        {
		            XSSFCell cell = (XSSFCell)row.getCell(i);
		            String value = getValue(cell);
		            rowValue.add(value);
		        }
   		        result.add(rowValue);
        	}
		}
       catch (Exception e)
       {
    	   e.printStackTrace();
	   }	    
       return result;
		
	}
	
	public Integer getSheetCounts()
	{
		return sheetCounts;
	}
	
	/**
	 * 
	 * @param filePath
	 * @param extType  .xlsx,xls
	 * @return
	 */
	public Object getWorkBook(String filePath,ExtensionType extType)
    {
    	Object workBook=null;
    	try 
    	{
    		if(extType==ExtensionType.XLS)
        	{
        		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(filePath));
                workBook = new HSSFWorkbook(fs);
                this.sheetCounts=((HSSFWorkbook)workBook).getNumberOfSheets();
        	}
    		else 
    		{		
    			workBook = new XSSFWorkbook(filePath);
    			this.sheetCounts=((XSSFWorkbook)workBook).getNumberOfSheets();
			}
		} 
    	catch (Exception e) 
    	{
			
		}
    	return workBook;
    }

	/**
	 * 
	 * @param workBook
	 * @param sheetName
	 * @param extType .xlsx,xls
	 * @return
	 */
    public Object getWorkSheet(Object workBook,String sheetName,ExtensionType extType)
    {
    	Object workSheet=null;
    	try 
    	{
    		if(extType==ExtensionType.XLS)
        	{
    			HSSFWorkbook  targetBook =(HSSFWorkbook)workBook;
                workSheet=targetBook.getSheet(sheetName);
        	}
    		else 
    		{
    			XSSFWorkbook  targetBook =(XSSFWorkbook)workBook;
                workSheet=targetBook.getSheet(sheetName);
			}
		} 
    	catch (Exception e) 
    	{
			
		}
    	return workSheet;
    	
    }
    
    /**
     * 
     * @param workBook
     * @param sheetIndex
     * @param extType .xlsx,xls
     * @return
     */
    public Object getWorkSheet(Object workBook,Integer sheetIndex,ExtensionType extType)
    {
    	Object workSheet=null;
    	try 
    	{
    		if(extType==ExtensionType.XLS)
        	{
    			HSSFWorkbook  targetBook =(HSSFWorkbook)workBook;
                workSheet=targetBook.getSheetAt(sheetIndex);
        	}
    		else 
    		{
    			XSSFWorkbook  targetBook =(XSSFWorkbook)workBook;
    			workSheet=targetBook.getSheetAt(sheetIndex);
			}
		} 
    	catch (Exception e) 
    	{
			
		}
    	return workSheet;
    	
    }
    
    public String getWorkSheetName(Object workBook,Integer sheetIndex,ExtensionType extType)
    {
    	Object workSheet=null;
    	String name = "";
    	
    	try 
    	{
    		if(extType==ExtensionType.XLS)
        	{
    			HSSFWorkbook  targetBook =(HSSFWorkbook)workBook;
                workSheet=targetBook.getSheetAt(sheetIndex);
                name = targetBook.getSheetName(sheetIndex);
        	}
    		else 
    		{
    			XSSFWorkbook  targetBook =(XSSFWorkbook)workBook;
    			workSheet=targetBook.getSheetAt(sheetIndex);
    			name = targetBook.getSheetName(sheetIndex);
			}
		} 
    	catch (Exception e) 
    	{
			
		}
    	return name;
    	
    }
    
    
    /**

     
     *

     * @param filePath



     * @param tableName



     */

    
    /**
     * 
     * @param filePath
     * @throws Exception
     */
	public void readXLSExcel(String filePath) throws Exception {

        try {

            HSSFWorkbook workBook = (HSSFWorkbook)getWorkBook(filePath,ExtensionType.XLS);

            for (int i =0; i < this.getSheetCounts(); i++) {

                // create workbook

                HSSFSheet sheet = workBook.getSheetAt(i);

                int rows = sheet.getPhysicalNumberOfRows(); // get row counts

                if (rows >0 ) {

                    sheet.getMargin(HSSFSheet.TopMargin);

                    for (int r = 0; r < rows; r++) { // 

                        HSSFRow row = sheet.getRow(r);

                        if (row != null && r !=0 ) {// 

                            int cells = row.getLastCellNum();// 


                            for (int c =0 ; c < cells; c++) { 

                                HSSFCell cell = row.getCell(c);

                                if (cell != null) {

                                    @SuppressWarnings("unused")
									String value = getValue(cell);
                                }

                            }

                            

                        }

                    }

                } else {

                    break;

                }

            }

 

        } catch (Exception ex) {

            ex.printStackTrace();

            System.out.println("readExcel exception" + ex);

            throw ex;

        }

    }

 

    private String getValue(XSSFCell cell) 
    {
        String value = "";
        switch (cell.getCellType())
        {
        case Cell.CELL_TYPE_STRING:
            value=cell.getRichStringCellValue().getString();
            break;
        case Cell.CELL_TYPE_NUMERIC:
            if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell))
            {
                java.util.Date date = cell.getDateCellValue();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                value = format.format(date);
            }
            else 
            {
                //value = String.valueOf(cell.getNumericCellValue());
                value = cell.getRawValue();
            }
            break;
        case Cell.CELL_TYPE_BOOLEAN:
            value = " " + cell.getBooleanCellValue();
            break;
        case Cell.CELL_TYPE_FORMULA:
            value = cell.getCellFormula();
            break;
        default:

        }
        return value;

    }

 
    

    /**
     *

     * @param cell

     * @return

     * @throws ParseException

     */
	public String getValue(HSSFCell cell) throws ParseException {

        String value = null;

        switch (cell.getCellType()) {

        case HSSFCell.CELL_TYPE_NUMERIC: 

            if (HSSFDateUtil.isCellDateFormatted(cell)) {
            	java.util.Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

                value = format.format(date);

            } else {

                value = String.valueOf(cell.getNumericCellValue());

            }

            break;



        case HSSFCell.CELL_TYPE_STRING: 

            value = cell.getStringCellValue();

            break;

        case HSSFCell.CELL_TYPE_FORMULA:

            

            value = String.valueOf(cell.getNumericCellValue());

            if (value.equals("NaN")) {

                value = cell.getStringCellValue().toString();

            }

            cell.getCellFormula();

            break;

        case HSSFCell.CELL_TYPE_BOOLEAN:

            value = " " + cell.getBooleanCellValue();

            break;



        case HSSFCell.CELL_TYPE_BLANK: 

            value = "";

            break;

        case HSSFCell.CELL_TYPE_ERROR:

            value = "";

            break;

        default:

            value = cell.getStringCellValue().toString();

        }

        return value;

    }
}
