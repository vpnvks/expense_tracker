package utilities;

import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SaveExcelData {
	
	public static void saveinexcel(XSSFWorkbook workbook,JFrame frame) {
		 FileOutputStream fileOut;
			try {
				fileOut = new FileOutputStream("expenses.xlsx");
				workbook.write(fileOut);
				fileOut.close();
			} catch (IOException e) {
				//System.out.println("problem in wrting and saving the data in excel");
				e.printStackTrace();
				JOptionPane.showMessageDialog(frame, "Error while saving data.", "Error", JOptionPane.ERROR_MESSAGE);
			}
	}

}
