package data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataFiller {

	public static void fillFile(String fileName, String[] rowData) {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\java\\Excel\\" + fileName;
		try {
			FileInputStream fis = new FileInputStream(filePath);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(0);
			
			XSSFRow row = sheet.getRow(0);
			if (row == null) {
				row = sheet.createRow(0);
			}
			
			for (int i = 0; i < rowData.length; i++) {
				row.createCell(i).setCellValue(rowData[i]);
			}
			
			fis.close();
			
			FileOutputStream fos = new FileOutputStream(filePath);
			workbook.write(fos);
			fos.close();
			workbook.close();
			System.out.println("Filled: " + fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// Egypt localized test data
		String name = "mahmoud QCtester";
		String email = "testermahmoud358@gmail.com";
		String fName = "mahmoud";
		String lName = "QCtester";
		
		// TC01: Name, Email, Password, Day, Month, Year, FName, LName, Company, Addr1, Addr2, Country, State, City, Zip, Mobile
		fillFile("TC01_RegUsr_h.xlsx", new String[]{name, email, "Password123", "10", "12", "2000", fName, lName, "IT", "123 Tahrir Square", "Downtown", "United States", "Cairo", "Cairo", "11511", "+201000000000"});
		
		// TC02: Email, Password
		fillFile("TC02_LogUsr_h.xlsx", new String[]{email, "Password123"});
		
		// TC03: Email, Password (Negative)
		fillFile("TC03_LogUsr_n.xlsx", new String[]{"invalid" + System.currentTimeMillis() + "@gmail.com", "wrongpass"});
		
		// TC04: Email, Password
		fillFile("TC04_LgOutUsr_h.xlsx", new String[]{email, "Password123"});
		
		// TC05: Name, ExistingEmail
		fillFile("TC05_RegUsr_n.xlsx", new String[]{name, email});
		
		// TC06: Name, Email, Subject, Message, FilePath
		fillFile("TC06_ContUs_h.xlsx", new String[]{name, email, "Feedback", "Great site, greetings from Egypt!", ""});
		
		// TC07: None needed really, but let's put dummy
		fillFile("TC07_TstCasPg_h.xlsx", new String[]{"Dummy"});
		
		// TC08: None
		fillFile("TC08_VrfPrds_h.xlsx", new String[]{"Dummy"});
		
		// TC09: Search Keyword
		fillFile("TC09_SrchPrd_h.xlsx", new String[]{"Dress"});
		
		// TC10: Subscription Email
		fillFile("TC10_SubHome_h.xlsx", new String[]{"sub@gmail.com"});
		
		// TC11: Subscription Email
		fillFile("TC11_SubCart_h.xlsx", new String[]{"sub@gmail.com"});
		
		// TC12 - TC19: Dummy
		fillFile("TC12_AddPrdCrt_h.xlsx", new String[]{"Dummy"});
		fillFile("TC13_VrfQtyCrt_h.xlsx", new String[]{"Dummy"});
		
		// TC14: NameOnCard, Card, CVC, Month, Year
		fillFile("TC14_PlOrwChOu_h.xlsx", new String[]{"Abdelrahman", "123456789012", "123", "12", "2025"});
		fillFile("TC15_PlOrbChOu_h.xlsx", new String[]{"Abdelrahman", "123456789012", "123", "12", "2025"});
		fillFile("TC16_PlOlbChOu_h.xlsx", new String[]{"Abdelrahman", "123456789012", "123", "12", "2025"});
		
		fillFile("TC17_RmPrdsCrt_h.xlsx", new String[]{"Dummy"});
		fillFile("TC18_VwCatPrds_h.xlsx", new String[]{"Dummy"});
		fillFile("TC19_VwBrdPrds_h.xlsx", new String[]{"Dummy"});
		
		// TC20: Search, Email, Password
		fillFile("TC20_SrCrtLgn_h.xlsx", new String[]{"Dress", "abdelrahmanosama769@gmail.com", "a123456789"});
		
		// TC21: Name, Email, Review
		fillFile("TC21_AddRev_h.xlsx", new String[]{"Abdelrahman", "test@gmail.com", "Excellent quality!"});
		
		fillFile("TC22_AddCrtRec_h.xlsx", new String[]{"Dummy"});
		fillFile("TC23_VrfAddChO_h.xlsx", new String[]{"Dummy"});
		fillFile("TC24_DwnInv_h.xlsx", new String[]{"Dummy"});
		fillFile("TC25_ScrUpArr_h.xlsx", new String[]{"Dummy"});
		fillFile("TC26_ScrUpNoAr_h.xlsx", new String[]{"Dummy"});
	}
}
