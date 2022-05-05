package com.Tarandeep.HousieTicketGenerator;

public class Launcher {
	
	public static void main(String[] args) {
		
		//
		Ticket t = Ticket.createTicket(3, 10);
		
		new TicketView(t);
		
		t.displayTicket();
		
	}
}
