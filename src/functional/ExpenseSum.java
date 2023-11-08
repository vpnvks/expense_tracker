package functional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import utilities.ReadingExcel;

public class ExpenseSum {
	public static List<Object> sumlst; 
	
	public static double addexpense() {
		sumlst = new ArrayList<>();
		ReadingExcel rdexl = new ReadingExcel();
		sumlst=rdexl.readdata("expenses.xlsx", "Expenses", 2,1);
		//System.out.println("reached near for loop and values are "+sumlst.toString());
		double j = 0 ;
		Iterator<Object> it = sumlst.iterator();
		while(it.hasNext()) {
			Object d= it.next();
			
				double doubleValue = Double.parseDouble((String)d);
				j+=doubleValue;
			
		}
		
		//System.out.println("Sum val of expense is :"+j);
		return j;
		
	}

}
