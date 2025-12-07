package ExcelTesting;


import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelSheet {

	public static void main(String[] args) throws IOException {
		
		
		String excelPath = System.getProperty("user.dir") + "/src/main/resources/TestingExcel.xlsx";

		FileInputStream fis = new FileInputStream(excelPath);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		// we will get the number of sheets in excel sheet
		int sheets = wb.getNumberOfSheets();
		
		// iterate the loop over all sheet which we get and compare that on of the sheet
		for(int i = 0; i < sheets; i++) {
			if(wb.getSheetName(i).equalsIgnoreCase("testdata")) {
				XSSFSheet sheet = wb.getSheetAt(i);
				System.out.println("Added one line");
			}
		}
		
	}
}
