package com.praveen.utils;

import com.praveen.resources.ChessPiece;

public abstract class AbstractChessPiece implements ChessPiece {
	
	/**
	 * @uml.property  name="column"
	 */
	private int column = -1;
	/**
	 * @uml.property  name="row"
	 */
	private int row = -1;

	public AbstractChessPiece() {
	}
	
	public abstract char getSymbol();
	
	@Override
	public String toString() {
		return String.valueOf(getSymbol());
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null || ! ChessPiece.class.isAssignableFrom(obj.getClass()))
			return false;
		return (getSymbol() == ((ChessPiece)obj).getSymbol());
	}
	
	@Override
	public int hashCode() {
		return getSymbol();
	}

	/**
	 * @param column
	 * @uml.property  name="column"
	 */
	public void setColumn(int column) {
		this.column = column;
	}
	
	/**
	 * @return
	 * @uml.property  name="column"
	 */
	public int getColumn() {
		return column;
	}
	
	/**
	 * @param row
	 * @uml.property  name="row"
	 */
	public void setRow(int row) {
		this.row = row;
	}
	
	/**
	 * @return
	 * @uml.property  name="row"
	 */
	public int getRow() {
		return row;
	}
}
