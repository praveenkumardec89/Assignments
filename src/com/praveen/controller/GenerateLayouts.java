package com.praveen.controller;

import com.praveen.utils.ChessBoard;
import com.praveen.utils.Results;
import com.praveen.validations.RequestAndValidateInput;

public class GenerateLayouts {
	
	static RequestAndValidateInput input;

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		
		try{		
			input.requestForInput();
			input.setPieceArray(input.piecesList);
			String[] parts = input.getDimensions().split(",");		
			ChessBoard chessBoard = new ChessBoard(Integer.parseInt(parts[0]),Integer.parseInt(parts[1]) , input.pieceArray);		
			long begin = System.currentTimeMillis();	
			Results results = chessBoard.searchLayouts();			
			long end = System.currentTimeMillis();			
			System.out.format("%5s","   Unique Layouts:\t " + results.getNumUniqueLayouts());
			System.out.println();
			System.out.format("%5s", "  Computation time:\t"+ (end - begin) + "ms" );
			System.out.println();
			System.out.format("%5s", "  attempted Permutations:\t"+results.getNumAttempts() );
			System.out.println();
			System.out.format("%5s","   duplicates\t"+results.getNumDuplicates()  );
			System.out.println();
			results.printLayouts(System.out);
			
	}catch(Exception e){
		e.printStackTrace();
	}
		
	}
}
