package functional;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;

import utilities.SaveExcelData;
import utilities.Workbook;

public class DeleteData {
	
	 public void deleteSelectedData(JFrame frame, JList<String> dataList,DefaultListModel<String> dataListModel) {
	        int selectedIndex = dataList.getSelectedIndex();
	        
	        if (selectedIndex != -1 ) {
	            dataListModel.remove(selectedIndex);
	            
	            
	            Workbook wk = new Workbook();
	            wk.deleteRowFromExcel(frame, selectedIndex);
	            SaveExcelData.saveinexcel(wk.workbook, frame);
	           
	           	            
	        }
	        else {
	        	 JOptionPane.showMessageDialog(frame, "Please Select an Expense Entry to Delete.", "Success", JOptionPane.INFORMATION_MESSAGE);
	        	   return;
	        }
	    }

}
