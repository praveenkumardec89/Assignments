package com.praveen.resources;

import com.praveen.utils.AbstractChessPiece;

public class NullPiece extends AbstractChessPiece {

	public char getSymbol() {
		return ' ';
	}

	public boolean canAttackPosition(int column, int row) {
		return false;
	}

}
