package com.Tarandeep.HousieTicketGenerator;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

public class TicketView {
	JFrame f;
	List<JButton> numberButton;
	public TicketView(Ticket t) {
		
		
		f = new JFrame();
		numberButton = new ArrayList<>(); 
	    int count = 0;
		for(Integer[] row : t.numberPositions) {
			for(Integer col : row) {
				if(col == 0) {
					numberButton.add(new JButton(""));
					numberButton.get(count).setEnabled(false);
					f.add(numberButton.get(count));
					
				}
				else {
					numberButton.add(new JButton(Integer.toString(col)));
					f.add(numberButton.get(count));
				}
			count++;
			}
		}   
		
		for(JButton button : numberButton) {
			if(button.getText()!="") {
				button.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						button.setEnabled(false);
						
					}
				});
			}
		}
		
		
	    // setting grid layout of 3 rows and 9 columns    
	    f.setLayout(new GridLayout(t.rowMax,t.colMax));    
	    
	    f.setSize(100*t.colMax,100*t.rowMax);    
	    f.setVisible(true);    

	}
}
