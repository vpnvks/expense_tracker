package functional;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

//import org.apache.log4j.Logger;

import utilities.Workbook;

public class Frmainpage {
	
	    JFrame frame;
	    JTextField dateField;
	    JComboBox<String> expenseTypeField;
	    JTextField amountField;
	    JTextField descriptionField;
	    DefaultListModel<String> dataListModel;
	    
	    
	    public void ExpenseTrackerApp() {
	        frame = new JFrame("Expense Tracker");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setLayout(new GridLayout(9, 2, 10, 10));
	       // Logger log = Logger.getLogger("devpinoyLogger");

	        dateField = new JTextField();
	        CustomDateTextField dtfld = new  CustomDateTextField(frame, dateField);
	        dtfld.adddatefield();
	       
	        
	        	       
	        frame.add(new JLabel("Expense Type:"));
	        String[] expenseTypes = {"Grocery", "Fuel","Online","Loan interst","Dining"};
	        expenseTypeField = new JComboBox<>(expenseTypes);
	        frame.add(expenseTypeField);

	        frame.add(new JLabel("Expense Amount:"));
	        amountField = new JTextField();
	        frame.add(amountField);

	        frame.add(new JLabel("Description:"));
	        descriptionField = new JTextField();
	        frame.add(descriptionField);

	        JButton saveButton = new JButton("Add Expense");
	        frame.add(saveButton);
	        
	        JButton displayButton = new JButton("Display Expenses");
	        frame.add(displayButton);
	        
	        JButton deleteButton = new JButton("Delete Expense Entry");
	        frame.add(deleteButton);

	        dataListModel = new DefaultListModel<>();
	        JList<String> dataList = new JList<>(dataListModel);
	        frame.add(new JScrollPane(dataList));
	        
	        FrBalancebutton balbut = new FrBalancebutton();
	        balbut.addbutton(this.frame);
	        
	        FrShowBalButton swbalbut = new FrShowBalButton();
	        swbalbut.showbalance(this.frame);
	        
	        FrShowStats shwstat = new FrShowStats();
	        shwstat.showstat(frame);
	        
	        saveButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	Workbook wk = new Workbook();
	                wk.saveToExcel(frame,dateField,expenseTypeField,amountField,descriptionField);
	               // log.debug("data is saved to excel");
	            }
	        });
	        
	        displayButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	Workbook wk = new Workbook();
	               wk.displayDataFromExcel(frame,dataListModel);
	            }
	        });

	        deleteButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	DeleteData dd = new DeleteData();
	                dd.deleteSelectedData(frame, dataList, dataListModel);
	                JOptionPane.showMessageDialog(frame, "Expense entry deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
	            	
	            }
	        });
	        
	        frame.setSize(1000, 600);
	        frame.setVisible(true);
	    
	    }
}
