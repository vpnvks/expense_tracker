package utilities;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DateFormatCheck {
	
	public static boolean isvalifformat(String datetext) {
		
		
		 return datetext.matches("^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-(\\d{4})$");
    };
		
    public static boolean DateMonthVal(JFrame frame, String datetext) {
    	
    	String[] parts = datetext.split("-");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);
        boolean valid = false;
        boolean validfeb = false;
        if( day>30 && (month == 2 || month == 4 || month == 6 || month == 9 || month == 11)) {
        	valid = false;
        	
        	 
        }
        else if((day==29 && (month==2) && (!leapyear(year)))) {
        	validfeb = false;	
        	
        }
        else {
        	 valid = true;
        	validfeb = true;
        }
       
      // System.out.println("valid is "+valid + " and validfex "+ validfeb);
       
        if (!valid || !validfeb) {
        	JOptionPane.showMessageDialog(frame, "Date and Month combination is invalid", "Error", JOptionPane.ERROR_MESSAGE);
              return false;
        }
        else {
        
		return true;
        }
    }
    
    private static boolean leapyear(int year) {
    	
    	int reminder = year%4;
    	
    	if(reminder==0) {
    		return true;
    	}
		return false;
    	
    }
	}


