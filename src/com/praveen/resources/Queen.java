package com.praveen.resources;

import com.praveen.utils.AbstractChessPiece;

public class Queen extends AbstractChessPiece {

	public char getSymbol() {
		return 'Q';
	}

	public boolean canAttackPosition(int column, int row) {
		if(Math.abs(getRow() - row) == Math.abs(getColumn() - column))
			return true;
		if(getRow() == row || getColumn() == column)
			return true;
		return false;
	}

	
}
