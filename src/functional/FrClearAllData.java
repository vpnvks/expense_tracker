package functional;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public class FrClearAllData {
	
	public JButton clearalldata;
	

	public void clear(JFrame frame) {
		clearalldata = new JButton("Clear All Data");
        frame.add(clearalldata);
	
	
	clearalldata.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
         //System.out.println("data cleared successfully");
         ClearAllData cldata = new ClearAllData();
         cldata.deletedata(frame);
         }
     }); 
	}
}
