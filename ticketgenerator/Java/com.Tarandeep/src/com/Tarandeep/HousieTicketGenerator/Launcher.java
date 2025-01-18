package com.Tarandeep.HousieTicketGenerator;

public class Launcher {
	
	public static void main(String[] args) {
		
		//
		Ticket t = Ticket.createTicket(3, 9);
		
		new TicketView(t);
		
		t.displayTicket();
		
	}
}
