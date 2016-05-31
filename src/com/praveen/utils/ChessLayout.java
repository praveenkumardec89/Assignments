package com.praveen.utils;

import java.util.HashMap;

import com.praveen.resources.ChessPiece;
import com.praveen.resources.NullPiece;

/**
 * Holds the information about the placement of chess pieces in a chess board.<p>
 * The positions are not identifies by column and row, but by an offset from 
 * row zero and column zero to width and height.
 * 
 */
public class ChessLayout implements Cloneable {
	
	public static final ChessPiece NULL_PIECE = new NullPiece();
	
	public static final int NULL_OFFSET = -1;

	/**
	 * @uml.property  name="width"
	 * @uml.associationEnd  qualifier="valueOf:java.lang.Integer com.praveen.resources.ChessPiece"
	 */
	private int width;
	
	/**
	 * @uml.property  name="boardLength"
	 */
	private int boardLength;
	
	/**
	 * @uml.property  name="completionAttempt"
	 */
	private int completionAttempt;
	
	/**
	 * @uml.property  name="hash"
	 */
	private int hash;
	
	/**
	 * Maps an offset to a chess piece in the board.<p> Offset 0 corresponds to column=0, row=0; Offset 1 corresponds to column=1, row=0
	 * @uml.property  name="pieceOffsets"
	 * @uml.associationEnd  qualifier="valueOf:java.lang.Integer com.praveen.resources.ChessPiece"
	 */
	private HashMap<Integer, ChessPiece> pieceOffsets;

	public ChessLayout(int width, int height) {

		this.width = width;
		this.boardLength = width * height;
		this.completionAttempt = 0;
		this.hash = 0;
		
		this.pieceOffsets = new HashMap<Integer, ChessPiece>(boardLength);
	}
	
	@SuppressWarnings("unchecked")
	private ChessLayout(int width, int boardLength, int completionAttempt, HashMap<Integer, ChessPiece> pieceOffsets) {
		this.width = width;
		this.boardLength = boardLength;
		this.completionAttempt = completionAttempt;
		this.hash = 0;
		this.pieceOffsets = (HashMap<Integer, ChessPiece>) pieceOffsets.clone();
	}
	
	/**
	 * @return
	 * @uml.property  name="boardLength"
	 */
	public int getBoardLength() {
		return boardLength;
	}
	
	
	/**
	 * @return
	 * @uml.property  name="completionAttempt"
	 */
	public int getCompletionAttempt() {
		return completionAttempt;
	}

	/**
	 * @param completionAttempt
	 * @uml.property  name="completionAttempt"
	 */
	public void setCompletionAttempt(int completionAttempt) {
		this.completionAttempt = completionAttempt;
	}

	/**
	 * Place a chess piece on the board at position column, row.<p>
	 * Does not verify whether the piece can be attacked buy other present pieces or that it can attack any of them.
	 * @param piece Chess piece to place
	 * @param column Column position place (0 is first column)
	 * @param row Row position place (0 is first row)
	 */
	public void placeChessPieceAtPosition(ChessPiece piece, int column, int row) {
		piece.setColumn(column);
		piece.setRow(row);
		pieceOffsets.put(row * width + column, piece);
	}

	/**
	 * @param piece
	 * @param offset
	 * @see {@link ChessLayout#placeChessPieceAtPosition(ChessPiece, int, int)}
	 */
	public void placeChessPieceAtPosition(ChessPiece piece, int offset) {
		int column = offset % width;
		int row = offset / width;
		placeChessPieceAtPosition(piece, column, row);
	}

	public void removeChessPiece(ChessPiece piece) {
		int offset = piece.getColumn() + width * piece.getRow();
		if(pieceOffsets.remove(Integer.valueOf(offset)) == null)
			throw new IllegalArgumentException("Could not find piece in this layout");
	}
	
	
	public int placePieceInNextAvailablePosition(ChessPiece piece) {
		return placePieceInNextAvailablePosition(piece, 0);
	}

	/**
	 * Place the specified chess piece in the next position where it cannot attack or be attacked by other existing pieces. 
	 * @param piece Chess piece to place
	 * @param startingOffset Offset from the top left corner of the chess board to start looking for an available position
	 * @return the offset at wich the piece was placed or NULL_OFFSET if the piece could not be placed.
	 */
	public int placePieceInNextAvailablePosition(ChessPiece piece, int startingOffset) {
		
		for(int offset = startingOffset; offset < boardLength; offset++)
			if(pieceOffsets.get(Integer.valueOf(offset)) == null) {
				int column = offset % width;
				int row = offset / width;
				if(canPlacePieceAtPosition(piece, column, row)) {
					placeChessPieceAtPosition(piece, column, row);
					return offset;
				}
			}

		return NULL_OFFSET;
	}

	private boolean canPlacePieceAtPosition(ChessPiece piece, int column, int row) {
		
		for(ChessPiece laidPiece : pieceOffsets.values()) {
			if(laidPiece.canAttackPosition(column, row))
				return false;
			piece.setColumn(column);
			piece.setRow(row);
			if(piece.canAttackPosition(laidPiece.getColumn(), laidPiece.getRow()))
				return false;
		}
		return true;
	}
	
	@Override
	public int hashCode() {
        int h = hash;
        if (h == 0 && pieceOffsets.size() > 0) {

            for (int i=0; i<boardLength; i++) {
    			ChessPiece chessPiece = pieceOffsets.get(Integer.valueOf(i));
    			if(chessPiece == null)
    				chessPiece = NULL_PIECE;
                h = 31 * h + (int) chessPiece.getSymbol();
            }
            hash = h;
        }
        return h;
	}
	
	public String getColumnsText() {
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<boardLength; i++) {
			ChessPiece chessPiece = pieceOffsets.get(Integer.valueOf(i));
			if(chessPiece == null)
				chessPiece = NULL_PIECE;
			sb.append(chessPiece.getSymbol());
		}
		return sb.toString();
	}
	
	public String getLayoutText() {
		StringBuilder sb = new StringBuilder();

		int height = boardLength / width;
		int offset = 0;
		for(int r = 0; r < height ; r++) {
			for(int c = 0; c < width; c++) {
				sb.append("|");
				sb.append("---");
			}
			sb.append("|\n");
			for(int c = 0; c < width; c++) {
				sb.append("| ");
				ChessPiece chessPiece = pieceOffsets.get(Integer.valueOf(offset++));
				if(chessPiece == null)
					chessPiece = NULL_PIECE;
				sb.append(chessPiece.getSymbol());
				sb.append(" ");
			}
			sb.append("|\n");
		}
		for(int c = 0; c < width; c++) {
			sb.append("|");
			sb.append("---");
		}
		sb.append("|\n");

		return sb.toString();
	}

	public void printBoard() {
		System.out.println(getLayoutText());
	}
	
	@Override
	public ChessLayout clone() {
		
		ChessLayout clone = new ChessLayout(width, boardLength, completionAttempt, pieceOffsets);
		return clone;
	}
}
