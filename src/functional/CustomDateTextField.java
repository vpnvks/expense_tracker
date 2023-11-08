package functional;

import java.awt.event.FocusEvent;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;

public class CustomDateTextField {
	JFrame frame;
	JTextField dateField;
	
	public CustomDateTextField(JFrame frame, JTextField dateField) {
		this.frame=frame;
		this.dateField= dateField;
	}
	   
	public void adddatefield() {
		
		    frame.add(new JLabel("Date:"));
	       // dateField = new JTextField();// new changes are made
	        frame.add(dateField);
	        
	        dateField.setForeground(Color.GRAY);
	    
	        dateField.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	              
	                    dateField.setText("");
	               	            }
	        });

	        dateField.addFocusListener(new FocusAdapter() {
	            public void focusLost(FocusEvent e) {
	                if (dateField.getText().isEmpty()) {
	                    dateField.setText("dd-mm-yyyy");
	                }
	            }
	        });     
	        
	       
	}
}
