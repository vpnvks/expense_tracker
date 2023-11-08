package functional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import utilities.ReadingExcel;

public class StatCalcExtend {
	
	
	public double dispAdvStat() throws IOException {
		
		double balance = 0;
		
			balance = readbalance();
		
		double expense  = sumexpenses();
		double percal = (expense/balance)*100;
		
		return percal;
		
	}
	
	public double readbalance() throws IOException {
		List<Double> str = new ArrayList<>();
		ReadingExcel rdexcl = new ReadingExcel();
		double balance;
		
			str = rdexcl.readdata("expenses.xlsx", "Balance", 0,0);
		
		
		try {
		balance = str.get(0);
		}catch(Exception e) {
			balance=0;
		}
		return balance;
		
	}
	
	public double sumexpenses() throws IOException {
		List<String> str = new ArrayList<>();
		ReadingExcel rdexcl = new ReadingExcel();
		str = rdexcl.readdata("expenses.xlsx", "Expenses", 2,1);
		Iterator<String> it = str.iterator();
		double bal,finalval = 0;
		
		while(it.hasNext()) {
		 bal = Double.parseDouble(it.next());
		 finalval = finalval+bal;
		}
		
		return finalval;
		
	}
	

}
