package com.praveen.utils;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class Results {

	//private static final Logger logger = Logger.getLogger(Results.class);

	/**
	 * @uml.property  name="completedLayouts"
	 * @uml.associationEnd  qualifier="hash:java.lang.Integer com.praveen.utils.ChessLayout"
	 */
	private Map<Integer, ChessLayout> completedLayouts;
	/**
	 * @uml.property  name="attempts"
	 */
	private int attempts;
	/**
	 * @uml.property  name="duplicates"
	 */
	private int duplicates;

	public Results() {
		completedLayouts = new HashMap<Integer, ChessLayout>();
		attempts = 0;
		duplicates = 0;
	}

	public void addLayout(ChessLayout chessLayout) {
		attempts++;
		chessLayout.setCompletionAttempt(attempts);
		Integer hash = Integer.valueOf(chessLayout.hashCode());
		ChessLayout previous = completedLayouts.get(hash);

		if (previous == null) {
			completedLayouts.put(hash, chessLayout);
			
		} else {
			duplicates++;
	
			}
		
	}
	
	public void printLayouts(PrintStream out) {
		for(ChessLayout chessLayout : completedLayouts.values()) {
			out.println(chessLayout.getLayoutText());
		}
	}

	public int getNumUniqueLayouts() {
		return completedLayouts.size();
	}

	public int getNumAttempts() {
		return attempts;
	}

	public int getNumDuplicates() {
		return duplicates;
	}

}
