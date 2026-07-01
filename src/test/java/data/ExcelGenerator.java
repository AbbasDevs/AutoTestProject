package data;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelGenerator {

	public static void main(String[] args) {
		String[] fileNames = {
			"TC01_RegUsr_h.xlsx",
			"TC02_LogUsr_h.xlsx",
			"TC03_LogUsr_n.xlsx",
			"TC04_LgOutUsr_h.xlsx",
			"TC05_RegUsr_n.xlsx",
			"TC06_ContUs_h.xlsx",
			"TC07_TstCasPg_h.xlsx",
			"TC08_VrfPrds_h.xlsx",
			"TC09_SrchPrd_h.xlsx",
			"TC10_SubHome_h.xlsx",
			"TC11_SubCart_h.xlsx",
			"TC12_AddPrdCrt_h.xlsx",
			"TC13_VrfQtyCrt_h.xlsx",
			"TC14_PlOrwChOu_h.xlsx",
			"TC15_PlOrbChOu_h.xlsx",
			"TC16_PlOlbChOu_h.xlsx",
			"TC17_RmPrdsCrt_h.xlsx",
			"TC18_VwCatPrds_h.xlsx",
			"TC19_VwBrdPrds_h.xlsx",
			"TC20_SrCrtLgn_h.xlsx",
			"TC21_AddRev_h.xlsx",
			"TC22_AddCrtRec_h.xlsx",
			"TC23_VrfAddChO_h.xlsx",
			"TC24_DwnInv_h.xlsx",
			"TC25_ScrUpArr_h.xlsx",
			"TC26_ScrUpNoAr_h.xlsx"
		};
		
		for (String fileName : fileNames) {
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("Data");
			XSSFRow row = sheet.createRow(0);
			// just some dummy data so it's not empty
			row.createCell(0).setCellValue("DummyData1");
			row.createCell(1).setCellValue("DummyData2");
			
			try {
				FileOutputStream out = new FileOutputStream(System.getProperty("user.dir") + "\\src\\test\\java\\Excel\\" + fileName);
				workbook.write(out);
				out.close();
				workbook.close();
				System.out.println("Generated: " + fileName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
