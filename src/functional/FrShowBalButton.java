package functional;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;

public class FrShowBalButton {
	
	public JButton displayBalanceButton;
	public JTextArea balanceDisplayArea;

	public void showbalance(JFrame frame) {
		
		displayBalanceButton = new JButton("Display Balance");
        frame.add(displayBalanceButton);

        balanceDisplayArea = new JTextArea(5, 20);
        balanceDisplayArea.setEditable(false);
        frame.add(balanceDisplayArea);

		
		  displayBalanceButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	//Font boldFont = new Font(StatDisplayArea.getFont().getName(), Font.BOLD, StatDisplayArea.getFont().getSize());
	                //StatDisplayArea.setFont(boldFont);
	                displayBalance(frame);
	            }
	        }); 
	}
	 private void displayBalance(JFrame frame) {
	        StringBuilder balanceText = new StringBuilder();
	        FileInputStream fileInputStream;
	        XSSFWorkbook workbook;
	        Double totalexpense = ExpenseSum.addexpense();
			try {
				fileInputStream = new FileInputStream("expenses.xlsx");
				workbook = new XSSFWorkbook(fileInputStream);
				 XSSFSheet balanceSheet = workbook.getSheet("Balance");
				 Row row=null;
				 if(balanceSheet==null) {
				
					 JOptionPane.showMessageDialog(frame, "Balance is not yet added.", "Error", JOptionPane.ERROR_MESSAGE);
					 //row = null;
				 }
				 else {
		            row = balanceSheet.getRow(0);
				 }
		            if (row != null) {
		                Cell cell = row.getCell(0);
		                if (cell != null) {
		                    double balanceValue = cell.getNumericCellValue();
		                    double rmngbal = balanceValue- totalexpense;
		                    balanceText.append("Starting Balance: ").append(balanceValue).
		                    append(" ; Total Expenditure: ").append(totalexpense)
		                   .append(" ; Remaining Balance: " ).append(rmngbal);
		                }
		            }
		            String resultString = balanceText.toString(); 
		          Font boldFont = new Font(resultString, Font.BOLD,12);
	              balanceDisplayArea.setFont(boldFont);
		        balanceDisplayArea.setText(resultString);
		        
			} catch (IOException e) {
				 JOptionPane.showMessageDialog(frame, "Balance is not yet added.", "Error", JOptionPane.ERROR_MESSAGE);
				 e.printStackTrace();
			}
	       
	       
	    }
}
