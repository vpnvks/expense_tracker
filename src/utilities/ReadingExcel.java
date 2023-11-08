package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadingExcel {
	FileInputStream fileInputStream;
	XSSFWorkbook workbook;
	XSSFSheet balanceSheet;
	
	@SuppressWarnings("unchecked")
	public <T> List<T> readdata(String filename, String sheetname, int colnumber,int datastartrownum) {
		List<T> allval=new ArrayList<>() ;
		try {
			fileInputStream = new FileInputStream(filename);
			workbook =  new XSSFWorkbook(fileInputStream);
			balanceSheet = workbook.getSheet(sheetname);
			int rownum = balanceSheet.getLastRowNum();
		
			int colnum= colnumber;
			
			for(int i=datastartrownum; i<=rownum; i++) {
				Row row = balanceSheet.getRow(i);
				
				if(row == null) {
					break;
				}
				
				Cell cell = row.getCell(colnum);
				if (cell.getCellType() == CellType.NUMERIC) {
				double val = cell.getNumericCellValue();
				allval.add((T) Double.valueOf(val));
				}else {
					String val = cell.getStringCellValue();
					allval.add((T) String.valueOf(val));
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
        
		return allval;
		
	}

}
