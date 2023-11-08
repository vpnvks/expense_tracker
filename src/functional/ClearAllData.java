package functional;

import java.io.File;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import utilities.ReadingExcel;
import utilities.SaveExcelData;
import utilities.Workbook;

public class ClearAllData {
	
	public void deletedata(JFrame frame) {
	  	ReadingExcel rdexl = new ReadingExcel();
	  	File fl = new File("expenses.xlsx");
	  	if(fl.exists()) {
		int intial_num = rdexl.getsheetrows("expenses.xlsx", "Expenses");
		//System.out.println("intial row value is "+intial_num);
		Workbook wk = new Workbook();
        
		wk.deleteRowFromExcel(frame, 0,"Balance");
        SaveExcelData.saveinexcel(wk.workbook, frame);
		
		while(intial_num>0) {
		/*deleteRowFromExcel(frame, 1);
		
		*/
			wk.deleteRowFromExcel(frame, 1,"Expenses");
	        SaveExcelData.saveinexcel(wk.workbook, frame);
	        intial_num = intial_num-1;
	        //System.out.println("data deleted new row value is "+intial_num);
		}
		 JOptionPane.showMessageDialog(frame, "All data cleared successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
			
	}else {
		 JOptionPane.showMessageDialog(frame, "No data found to clear", "Success", JOptionPane.INFORMATION_MESSAGE);
	}
	}

}
