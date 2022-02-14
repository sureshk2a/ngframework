package com.uiautomation.utils;

import com.uiautomation.constants.FrameworkConstants;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.IOException;
import java.util.*;

public final class ExcelUtils {

    private ExcelUtils(){}

    public static Sheet getSheet(String sheetName) throws IOException {
        //Create an object of File class to open xlsx file
        File file = new File(FrameworkConstants.getExcelPath());

        Sheet sheet;
        try (Workbook wb = WorkbookFactory.create(file)) {

            //Creating a Sheet object using the sheet Name
            sheet = wb.getSheet(sheetName);
        }
        return sheet;
    }

    public static Sheet getSheet(String excelPath,String sheetName) throws IOException {
        //Create an object of File class to open xlsx file
        File file = new File(excelPath);

        Sheet sheet;
        try (Workbook wb = WorkbookFactory.create(file)) {

            //Creating a Sheet object using the sheet Name
            sheet = wb.getSheet(sheetName);
        }
        return sheet;
    }

    public static List<Map<String,String>> getTestDetails(String sheetName) throws IOException {

        File file = new File(FrameworkConstants.getExcelPath());
        Sheet sheet = null;
        Workbook wb = null;

        try{
            wb = WorkbookFactory.create(file);
            sheet = wb.getSheet(sheetName);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (Objects.nonNull(wb)){
                wb.close();
            }
        }

        Map<String,String> map = null;
        List<Map<String,String>> list = new ArrayList<>();
        DataFormatter formatter = new DataFormatter();

        int lastRowNum = sheet.getLastRowNum();
        int lastColumnNum = sheet.getRow(0).getLastCellNum();

        for(int i=1;i<=lastRowNum;i++){
            map = new HashMap<>();
            for (int j=0;j<lastColumnNum;j++){
                String key = sheet.getRow(0).getCell(j).getStringCellValue();
                String value = formatter.formatCellValue(sheet.getRow(i).getCell(j));
                map.put(key,value);
            }
            list.add(map);
        }

        return list;
    }





}
