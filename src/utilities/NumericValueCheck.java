package utilities;

public class NumericValueCheck {
	
	public static boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?");
	}

	
}
