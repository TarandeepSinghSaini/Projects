package com.Tarandeep.HousieTicketGenerator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.ArrayList;

public class Ticket {
	
	private static final Logger LOGGER = LogManager.getLogger(Ticket.class);
	
	Integer[][] numberPositions;
	int colMax;
	int rowMax;
	
	
	public Ticket(int rowMax,int colMax) {
		this.rowMax=rowMax;
		this.colMax=colMax;
		this.numberPositions = new Integer[rowMax][colMax];
	}
		
	public static Ticket createTicket(int rowMax, int colMax) {		
		
		Ticket t = new Ticket(rowMax, colMax);
		List<Integer> used = new ArrayList<>();
		
		for(int col=0; col<t.colMax; col++) {
			for(int row=0; row<t.rowMax; row++) {
				
				//for first column 1 to 9
				if (col == 0) {
					int num = getRandomNumber(1,t.colMax,used);
					t.numberPositions[row][col] = num == -1 ? 0 : (col*10)+num;
					used.add(num);
				}
				
				// for last column 80s including 90
				else if(col == t.colMax-1){	
					int num = getRandomNumber(1,t.colMax+1,used);
					t.numberPositions[row][col] = num == -1 ? 0 : (col*10)+num;
					used.add(num);
				}
				
				//For middle columns 10s till 70s
				else {
					int num = getRandomNumber(0,t.colMax,used);
					t.numberPositions[row][col] = num == -1 ? 0 : (col*10)+num;
					used.add(num);
				}				
			}	
			used.clear();
		}
		
		t.createHoles();
		
		t.sort();
		
		return t;
	}
	
	private static Integer getRandomNumber(int min, int max, List<Integer> usedList) {
		boolean keepCalculating = true;
		int number = 0;
				
		while(keepCalculating) {
			number = min + (int)(Math.random() * ((max - min) + min));
			if(!usedList.isEmpty()) {
				for(Integer num : usedList) {
					if(number == num ) {
						if(num==max) return -1;
						keepCalculating = true;
						break;
					}
					else {
						
						keepCalculating = false;
					}
				}
			}
			else return number;
		}
		return number;
	}
	
	private void createHoles() {
		
		//Even Rows
		for (int row = 0; row < rowMax; row+=2) {
			List<Integer> used = new ArrayList<>();
			int num = 0;
			while(used.size()<4) {
				num = getRandomNumber(0,colMax,used);
				if(num != -1) {
					this.numberPositions[row][num] = 0;
					used.add(num);
				}
			}
			used.clear();
		}
		
		//Odd Rows
		for (int row = 1; row < rowMax; row+=2) {
			List<Integer> used = new ArrayList<>();
			List<Integer> priorityList = new ArrayList<>();
			int row_above,row_below;
			
			//Set rows to zero having non-zero numbers in above and below rows
			for (int col =0; col<colMax; col++) {
				row_above = this.numberPositions[row-1][col];
				row_below = this.numberPositions[row+1][col];
				
				if (row_above != 0 && row_below != 0) {
					this.numberPositions[row][col] = 0;
					used.add(col);
				}
			}
			
			//Set remaining 
			int num = 0;
			while(used.size()<4) {
				num = getRandomNumber(0,colMax,used);
				if(num != -1) {
					row_above = this.numberPositions[row-1][num];
					row_below = this.numberPositions[row+1][num];
					
					if(row_above != 0 && row_below == 0 ){
						this.numberPositions[row][num] = 0;
						used.add(num);
					}
					if(row_above == 0 && row_below != 0 ){
						this.numberPositions[row][num] = 0;
						used.add(num);
					}

				}
			}
			used.clear();
		}
	}

	private void sort() {
		for(int col=0; col<colMax; col++){
						 			
		        // One by one move boundary of unsorted subarray
		        for (int row = 0; row < rowMax-1; row++)
		        {
		            // Find the minimum element in unsorted array
		            int min_idx = row;
		            for (int rowj = row+1; rowj < rowMax; rowj++) 
		                if (numberPositions[rowj][col] != 0 && numberPositions[rowj][col] < numberPositions[min_idx][col])
		                    min_idx = rowj;
		                
		             // Swap the found minimum element with the first
			            // element
			            int temp = numberPositions[min_idx][col];
			            numberPositions[min_idx][col] = numberPositions[row][col];
			            numberPositions[row][col] = temp;
 
		        }
		}
	}

	public void displayTicket() {
		for(Integer[] row : numberPositions) {
			for(Integer col : row) {
				System.out.print(":"+col);
			}
			System.out.print("\n");
		}
	}

}