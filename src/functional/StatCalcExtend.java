package functional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import utilities.ReadingExcel;

public class StatCalcExtend {
	
	
	public double dispAdvStat() {
		
		double balance = readbalance();
		double expense  = sumexpenses();
		double percal = (expense/balance)*100;
		
		return percal;
		
	}
	
	public double readbalance() {
		List<Double> str = new ArrayList<>();
		ReadingExcel rdexcl = new ReadingExcel();
		str = rdexcl.readdata("expenses.xlsx", "Balance", 0,0);
		double balance = str.get(0);
		//double bal = Double.parseDouble(balance);
		//System.out.println("balance value is "+balance);
		return balance;
		
	}
	
	public double sumexpenses() {
		List<String> str = new ArrayList<>();
		ReadingExcel rdexcl = new ReadingExcel();
		str = rdexcl.readdata("expenses.xlsx", "Expenses", 2,1);
		Iterator<String> it = str.iterator();
		double bal,finalval = 0;
		
		while(it.hasNext()) {
		 bal = Double.parseDouble(it.next());
		 finalval = finalval+bal;
		}
		//System.out.println("total expense value is "+finalval);
		return finalval;
		
	}
	

}
