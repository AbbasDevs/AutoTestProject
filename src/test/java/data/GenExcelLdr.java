package data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GenExcelLdr {
	
	public static Object[][] getExcelData(String fileName, int expectedColumns) throws IOException{
		FileInputStream stream = null;
		
		String filePath = System.getProperty("user.dir") + "\\src\\test\\java\\Excel\\" + fileName;
		try {
			stream = new FileInputStream(filePath);
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + filePath);
			return new Object[0][0];
		}
		
		XSSFWorkbook workBook = new XSSFWorkbook(stream);
		XSSFSheet sheet = workBook.getSheetAt(0);
		
		int nRows = sheet.getLastRowNum() + 1;
		int nCols = expectedColumns;
		
		Object[][] data = new Object[nRows][nCols];
		
		for(int i = 0 ; i < nRows; ++i) {
			XSSFRow row = sheet.getRow(i);
			if (row != null) {
				for(int j = 0 ; j < nCols; ++j) {
					if (row.getCell(j) != null) {
						data[i][j] = row.getCell(j).toString();
					} else {
						data[i][j] = "";
					}
				}
			}
		}
		
		workBook.close();
		return data;
	}
}
