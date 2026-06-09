package utilities;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.*;

public class ExcelUtils {
	
	

    public static Object[][] getExcelData()
    throws Exception {

        FileInputStream fis =new FileInputStream("TestData/LoginData.xlsx");

        XSSFWorkbook wb =new XSSFWorkbook(fis);

        XSSFSheet sheet =wb.getSheetAt(0);

        int rows =sheet.getLastRowNum();

        int cols =sheet.getRow(0).getLastCellNum();

        Object data[][] =new Object[rows][cols];

        for(int r=1;r<=rows;r++) {

            for(int c=0;c<cols;c++) {

                data[r-1][c] =sheet.getRow(r).getCell(c).toString();
            }
        }

        wb.close();

        return data;
    }
}