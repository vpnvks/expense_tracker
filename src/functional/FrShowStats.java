package functional;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class FrShowStats {
	
	public JButton displayStatButton;
	public JTextArea StatDisplayArea;

	public void showstat(JFrame frame) {
		
		displayStatButton = new JButton("Display Expense Statistics");
        frame.add(displayStatButton);

        StatDisplayArea = new JTextArea(5, 10);
        StatDisplayArea.setEditable(false);
        frame.add(new JScrollPane(StatDisplayArea));

		
        displayStatButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	StatisticsCalc stcalc = new StatisticsCalc();
	            	Font boldFont = new Font(StatDisplayArea.getFont().getName(), Font.BOLD, 12);
	                StatDisplayArea.setFont(boldFont);
	                stcalc.displayExpenseStatistics(frame,StatDisplayArea);
	            }
	        });	
	}
}
