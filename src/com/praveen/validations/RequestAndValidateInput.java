package com.praveen.validations;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.praveen.resources.Bishop;
import com.praveen.resources.ChessPiece;
import com.praveen.resources.King;
import com.praveen.resources.Knight;
import com.praveen.resources.Queen;
import com.praveen.resources.Rook;

public class RequestAndValidateInput {
	
	private static String dimensions;
	public static List<ChessPiece> piecesList = new ArrayList<>();;	
	Pattern dimensionPattern = Pattern.compile("^[0-9],[0-9]+$");
	Pattern piecePattern = Pattern.compile("^[0-9]+$");
	public static ChessPiece[] pieceArray ;
	
	public static void requestForInput() {		
		 
		try{
			@SuppressWarnings("resource")
			Scanner console = new Scanner(System.in);					
			System.out.println("Enter ChessBoard Dimentions Ex:3,3:\t");
			dimensions = console.nextLine(); 
			if(dimensions.matches("^\\d,\\d")){				
				System.out.println("enter number of Kings:\t ");
				addPiece(console.nextInt(),"King");
				System.out.println("\nenter number of Queens: \t");
				addPiece(console.nextInt(),"Queen");
				System.out.println("\nenter number of Bishops: \t");
				addPiece(console.nextInt(),"Bishop");
				System.out.println("\nenter number of Rooks:\t");
				addPiece(console.nextInt(),"Rook");
				System.out.println("\nenter number of Knights: \t");
				addPiece(console.nextInt(),"Knight");				
				
				
			}else{				
				System.out.println("invalid dimensions");
				requestForInput();
			}
			
		}catch(Exception io){
			System.out.println("Invalid input");
			io.printStackTrace();
			requestForInput();
			
		}					
	}
	
	
	public static void addPiece(int count, String pieceType){
		
		if(count !=0){	
	
		switch(pieceType){
	    case "King" :
	    	for(int i=0;i<count;i++){
				piecesList.add(new King());	
			}
	       break;
	    case "Queen" :
	    	for(int i=0;i<count;i++){
				piecesList.add(new Queen());	
			}
	       break;
	    case "Bishop" :
	    	for(int i=0;i<count;i++){
				piecesList.add(new Bishop());	
			}
	       break;
	    case "Rook" :
	    	for(int i=0;i<count;i++){
				piecesList.add(new Rook());	
			}
	       break;
	    case "Knight" :
	    	for(int i=0;i<count;i++){
				piecesList.add(new Knight());	
			}
	       break;
	    default : //nothing
	}
		}
		
	}
	
	
	public static List<ChessPiece> getPieceList(){
		
		return piecesList;
	}
	
	public static void setPieceArray (List<ChessPiece> coins){	
	
	
	 pieceArray = coins.toArray(new ChessPiece[coins.size()]);
	
		
	}
	
	
	public static ChessPiece[] getPieceArray(){
		
		return pieceArray;
	}
	
	public static String getDimensions(){
		
		return dimensions;
	}
	
	public static List<ChessPiece> getPiecesList() {
		return piecesList;
	}

	public static void setPiecesList(List<ChessPiece> piecesList) {
		RequestAndValidateInput.piecesList = piecesList;
	}

	public static void setDimensions(String dimensions) {
		RequestAndValidateInput.dimensions = dimensions;
	}



	
	

}
