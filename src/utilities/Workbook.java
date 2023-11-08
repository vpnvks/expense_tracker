package utilities;

import javax.swing.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class Workbook {
	public XSSFWorkbook workbook; 
    XSSFSheet sheet;

    public void saveToExcel(JFrame frame,JTextField dateField,JComboBox<String> expenseTypeField,JTextField amountField,JTextField descriptionField) {
        try {
        	File fl = new File("expenses.xlsx");
            
            FileInputStream fs = null;
            
            if(!fl.exists()) {
            	workbook= new XSSFWorkbook();
            	sheet= workbook.createSheet("Expenses");
            
            
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Date");
            headerRow.createCell(1).setCellValue("Expense Type");
            headerRow.createCell(2).setCellValue("Amount");
            headerRow.createCell(3).setCellValue("Description");
            }
            else {
             fs = new FileInputStream(fl);
            workbook= new XSSFWorkbook(fs);
            sheet= workbook.getSheet("Expenses");
           // System.out.println("inside the sheet else");
            }
            
           int lastRowNum = sheet.getLastRowNum();
           //System.out.println("lastRowNum="+ lastRowNum);
            
            Row dataRow = sheet.createRow(lastRowNum+1);
            
            String datetext = dateField.getText();
            if(DateFormatCheck.isvalifformat(datetext)) {
            	
            	if(DateFormatCheck.DateMonthVal(frame,datetext )) {
            dataRow.createCell(0).setCellValue(datetext);
            //System.out.println("date format is correct");
            	}
            	else {
            		return;
            	}
            }
            else {
            	JOptionPane.showMessageDialog(frame, "Invalid date format valid format is dd-mm-yyyy", "Error", JOptionPane.ERROR_MESSAGE);
           	  	return;
            }
          //  dataRow.createCell(0).setCellValue(dateField.getText());
            dataRow.createCell(1).setCellValue(expenseTypeField.getSelectedItem().toString());
            
           boolean isnum = NumericValueCheck.isNumeric(amountField.getText());
           if(isnum) {
            dataRow.createCell(2).setCellValue(amountField.getText());
           }else {
        	   JOptionPane.showMessageDialog(frame, "Please provide a Numeric Amount Value.", "Error", JOptionPane.ERROR_MESSAGE);
        	   return;
           }
           
            dataRow.createCell(3).setCellValue(descriptionField.getText());

            FileOutputStream fileOut = new FileOutputStream("expenses.xlsx");
            workbook.write(fileOut);
            if(fs!=null) {
            fs.close();}
            fileOut.close();
            workbook.close();
           
            JOptionPane.showMessageDialog(frame, "Data saved.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error while saving data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    ///////////////////////display excel data///////////////////////////
    
    public void displayDataFromExcel(JFrame frame,DefaultListModel<String> dataListModel) {
        dataListModel.clear(); // Clear existing data in the list

        try {
            FileInputStream fileInputStream = new FileInputStream("expenses.xlsx");
            workbook = new XSSFWorkbook(fileInputStream);
            sheet = workbook.getSheet("Expenses");

            for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
            	Row row = null;
            	try {
                 row = sheet.getRow(rowNum);
            	}catch(Exception e) {
            	//System.out.println("row is null");
            	}
            	String rowData;
            	if(row != null) {
            		if(row.getRowNum() == 0) {
            			 rowData =  row.getCell(0) + " | " + row.getCell(1) + " | " + row.getCell(2) + " | " + row.getCell(3);
                       	
            		}
            		else {	
                 rowData = row.getCell(0) + " | " + row.getCell(1) + " | " + row.getCell(2) + " | " + row.getCell(3);
            		}
                dataListModel.addElement(rowData);}
            	else {
            		continue;
            	}
            }

            fileInputStream.close();
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error while reading data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
///////////////////////////////////delete excel data/////////////////
    
    public void deleteRowFromExcel(JFrame frame, int rowIndex) {
    	try {
            FileInputStream fileInputStream = new FileInputStream("expenses.xlsx");
            workbook = new XSSFWorkbook(fileInputStream);
            sheet = workbook.getSheet("Expenses");
        if (sheet != null) {
            Row row = sheet.getRow(rowIndex);
           if (row != null && rowIndex!=0) {
        	   
            	//System.out.println("deleting row and shifting it index num is:"+rowIndex +" deleted excel row is: "+(rowIndex+1) +" last row is :"+(sheet.getLastRowNum()));	
            	sheet.shiftRows(rowIndex+1, sheet.getLastRowNum()+1, -1);
            	    
           }
        }
    }catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(frame, "Error while reading data.", "Error", JOptionPane.ERROR_MESSAGE);
    }
   }   
 
}
