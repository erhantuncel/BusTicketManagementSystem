package com.erhan.onlineticket.utilities;

public class GenerateSeatArray {

	public static void main(String[] args) {
		
		Integer[][] seatArray = generateSeatArray();
		writeSeatArray(seatArray);
		
	}
	
	private static Integer[][] generateSeatArray() {
		Integer[][] seatArray = new Integer[13][3];
		Integer seatNumber = 1;
		for(int i=0; i<seatArray.length; i++) {
			for(int j=0; j<seatArray[i].length; j++) {
			    if(i==7 && j>0) {
		            seatArray[i][j] = null;
		            continue;
			    } else {
			        seatArray[i][j] = seatNumber;
			    }
			    seatNumber++;
			}
		}
		return seatArray;
	}
	
	private static void writeSeatArray(Integer[][] seatArray) {
		for(int i=0; i<seatArray.length; i++) {
			for(int j=0; j<seatArray[i].length; j++) {
				System.out.print(seatArray[i][j] + "\t");
			}
			System.out.println();
		}
	}

}
