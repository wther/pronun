package com.webther.pronun.data.service.impl;

/**
 * Exception thrown when puzzle entity is not found in the database
 * 
 * @author Barnabas
 */
public class PuzzleEntityNotFoundException extends Exception {

    /**
     * Generated serialVersionUID
     */
    private static final long serialVersionUID = 318357319148518616L;

    /**
     * Initialize exception by providing which puzzle is not found
     * @param puzzleCode Puzzle code
     */
    public PuzzleEntityNotFoundException(String puzzleCode) {
        super("Puzzle code not found: " + puzzleCode);
    }
}
