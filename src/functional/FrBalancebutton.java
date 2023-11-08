package functional;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import utilities.SaveExcelData;

import org.apache.poi.xssf.usermodel.XSSFSheet;

public class FrBalancebutton {
public	JTextField balanceField;
public	JButton balanceButton;
public	 XSSFWorkbook workbook;
public FileInputStream fileInputStream;
	 
	public void addbutton(JFrame frame) {
				
		 balanceButton = new JButton("Add Balance");
	        frame.add(balanceButton);
	        
	        balanceField = new JTextField();
	        frame.add(balanceField);
	        
	        
	        balanceButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                updateBalance(frame);
	            }
	        });
	}
	
	public void updateBalance(JFrame frame) {
        String balanceText = balanceField.getText();
        
        try {
            double newBalance = Double.parseDouble(balanceText);
            addBalanceToExcel(newBalance, frame);
            SaveExcelData.saveinexcel(this.workbook, frame);
        } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(frame, "Please provide a Numeric Balance Value.", "Error", JOptionPane.ERROR_MESSAGE);
      	   return;
        }
    }

    public void addBalanceToExcel(double newBalance,JFrame frame)  {
        // Create or get the "Balance" sheet
    	 try {
			fileInputStream = new FileInputStream("expenses.xlsx");
			workbook = new XSSFWorkbook(fileInputStream);
		} catch (IOException e) {
			System.out.println("problem in reading expense excel file");
			e.printStackTrace();
		}
    	
        XSSFSheet balanceSheet = workbook.getSheet("Balance");
        if (balanceSheet == null) {
        	
            balanceSheet = workbook.createSheet("Balance");
            Row row = balanceSheet.createRow(0);
            Cell cell = row.createCell(0);
            cell.setCellValue(newBalance);  
            JOptionPane.showMessageDialog(frame, "Balance added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
			
        }
        else { 
       
       Row row = balanceSheet.getRow(0);
       double rowData =  row.getCell(0).getNumericCellValue();
       double newrowdata = rowData + newBalance;
       row.getCell(0).setCellValue(newrowdata);
       JOptionPane.showMessageDialog(frame, "Balance added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
		   
       
        }
    }

}
