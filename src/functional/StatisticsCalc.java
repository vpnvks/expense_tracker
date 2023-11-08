package functional;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class StatisticsCalc {
	public XSSFWorkbook workbook; 
	public XSSFSheet sheet;
    
	public void displayExpenseStatistics(JFrame frame,JTextArea StatDisplayArea) {
		
		FileInputStream fileInputStream;
		try {
			fileInputStream = new FileInputStream("expenses.xlsx");
			workbook = new XSSFWorkbook(fileInputStream);
	        sheet = workbook.getSheet("Expenses");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			 JOptionPane.showMessageDialog(frame, "Balance and expenses are not yet added.", "Error", JOptionPane.ERROR_MESSAGE);
			 return;
		}
        
		
		
	    if (sheet != null) {
	        Map<String, Double> expenseMap = new HashMap<>();

	        // Iterate through the rows in the Excel sheet
	        for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
	            Row row = sheet.getRow(rowNum);
	            if (row != null) {
	                // Assuming that column 2 (index 1) contains the expense type and column 3 (index 2) contains the expense amount
	                Cell cellExpenseType = row.getCell(1);
	                Cell cellExpenseAmount = row.getCell(2);

	                if (cellExpenseType != null && cellExpenseAmount != null) {
	                    String expenseType = cellExpenseType.getStringCellValue();
	                    double expenseAmount = Double.parseDouble(cellExpenseAmount.getStringCellValue());

	                    // Update the total expense for the expense type
	                    expenseMap.put(expenseType, expenseMap.getOrDefault(expenseType, 0.0) + expenseAmount);
	                }
	            }
	        }

	        // Find the highest expense type and its total expense
	        String highestExpenseType = "";
	        double highestExpense = 0.0;

	        for (Map.Entry<String, Double> entry : expenseMap.entrySet()) {
	            if (entry.getValue() > highestExpense) {
	                highestExpenseType = entry.getKey();
	                highestExpense = entry.getValue();
	            }
	        }
	        /////////////////////
	        
	        StatCalcExtend stexd = new StatCalcExtend();
	        double number = 0;
			try {
				number = stexd.dispAdvStat();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				return;
			}
	        DecimalFormat df = new DecimalFormat("#.00");
	        String formatted = df.format(number);
	        
	        /////////////////////////////////
	        // Display the statistics
	        String statisticsMessage = null ;
	       
	        statisticsMessage = " Highest Expense Type: " + highestExpenseType;
	        statisticsMessage += "\n Total Expense on "+highestExpenseType +": " + highestExpense;
	        statisticsMessage += "\n You have utilised " + formatted +"% of total balance";
	        
	        StatDisplayArea.setText(statisticsMessage);
	        }
	}

}
