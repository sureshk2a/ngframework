package com.uiautomation.utils;

import com.uiautomation.constants.FrameworkConstants;
import com.uiautomation.exceptions.FrameworkExceptions;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public final class ExcelUtils {

	private ExcelUtils() {
	}

	public static Sheet getSheet(String sheetName) throws IOException {
		// Create an object of File class to open xlsx file
		File file = new File(FrameworkConstants.getExcelPath());

		Sheet sheet;
		try (Workbook wb = WorkbookFactory.create(file)) {

			// Creating a Sheet object using the sheet Name
			sheet = wb.getSheet(sheetName);
		}
		return sheet;
	}

	public static Sheet getSheet(String excelPath, String sheetName) throws IOException {
		// Create an object of File class to open xlsx file
		File file = new File(excelPath);

		Sheet sheet;
		try (Workbook wb = WorkbookFactory.create(file)) {

			// Creating a Sheet object using the sheet Name
			sheet = wb.getSheet(sheetName);
		}
		return sheet;
	}

	public static List<Map<String, String>> getTestDetails(String sheetName) throws IOException {

		File file = new File(FrameworkConstants.getExcelPath());
		Sheet sheet = null;
		Workbook wb = null;

		try {
			wb = WorkbookFactory.create(file);
			sheet = wb.getSheet(sheetName);
		}catch(FileNotFoundException e) {
			throw new FrameworkExceptions("Excel file is not found");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (Objects.nonNull(wb)) {
				wb.close();
			}
		}

		Map<String, String> map = null;
		List<Map<String, String>> list = new ArrayList<>();
		DataFormatter formatter = new DataFormatter();

		int lastRowNum = sheet.getLastRowNum();
		int lastColumnNum = sheet.getRow(0).getLastCellNum();

		for (int i = 1; i <= lastRowNum; i++) {
			map = new HashMap<>();
			for (int j = 0; j < lastColumnNum; j++) {
				String key = sheet.getRow(0).getCell(j).getStringCellValue();
				String value = formatter.formatCellValue(sheet.getRow(i).getCell(j));
				map.put(key, value);
			}
			list.add(map);
		}

		return list;
	}

	public static void writeToExcel(String bookPath,String sheetName,Map<String, Object[]> data) throws EncryptedDocumentException, IOException {
		
		XSSFSheet sheet = null;
		File book = null;

		XSSFWorkbook workbook = new XSSFWorkbook();
		// Writing the workbook
		book = new File(System.getProperty("user.dir") + bookPath);
		// Creating a blank Excel sheet
		sheet = (XSSFSheet) workbook.createSheet(sheetName);
		
		
		// Iterating over data and writing it to sheet
		Set<String> keyset = data.keySet();

		int rownum = 0;

		for (String key : keyset) {

			// Creating a new row in the sheet
			Row row = sheet.createRow(rownum++);

			Object[] objArr = data.get(key);

			int cellnum = 0;

			for (Object obj : objArr) {

				// This line creates a cell in the next
				// column of that row
				Cell cell = row.createCell(cellnum++);

				if (obj instanceof String)
					cell.setCellValue((String) obj);

				else if (obj instanceof Integer)
					cell.setCellValue((Integer) obj);
			}
		}

		
		// Try block to check for exceptions
		try(FileOutputStream out = new FileOutputStream(book)) {
			
			workbook.write(out);
			System.out.println(bookPath+" written successfully on disk.");
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	} 

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		// Creating an empty TreeMap of string and Object][]
		// type
		Map<String, Object[]> data = new TreeMap<String, Object[]>();

		// Writing data to Object[]
		// using put() method
		data.put("1", new Object[] { "ID", "NAME", "LASTNAME" });
		data.put("2", new Object[] { 1, "Pankaj", "Kumar" });
		data.put("3", new Object[] { 2, "Prakashni", "Yadav" });
		data.put("4", new Object[] { 3, "Ayan", "Mondal" });
		data.put("5", new Object[] { 4, "Virat", "kohli" });
		
		writeToExcel("/src/test/resources/excel/"+"WritenDemo"+".xlsx","student Details",data);
	}

}
