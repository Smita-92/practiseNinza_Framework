package com.ninza.hrm.api.genericUtility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Excelutility {

	String testData = "";

	public String getDataFromExcel(String sheet, int row, int cell) throws Throwable {
		FileInputStream fis = new FileInputStream(testData);
		Workbook wb = WorkbookFactory.create(fis);
		String data = wb.getSheet(sheet).getRow(row).getCell(cell).getStringCellValue();
		wb.close();
		return data;
	}

	public int getRowcount(String sheetName) throws Throwable {
		FileInputStream fis = new FileInputStream(testData);
		Workbook wb = WorkbookFactory.create(fis);
		int rowCount = wb.getSheet(sheetName).getLastRowNum();
		wb.close();
		return rowCount;

	}

	public void setDataIntoExcel(String sheetName, int rowNum, int cellNum) throws Throwable, IOException {
		FileInputStream fis = new FileInputStream(testData);
		Workbook wb = WorkbookFactory.create(fis);
		wb.getSheet(sheetName).getRow(rowNum).createCell(cellNum);
		FileOutputStream fos = new FileOutputStream(testData);
		wb.write(fos);
		wb.close();
	}

}
