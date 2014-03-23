package com.webther.pronun.data.service;

import com.webther.pronun.data.entity.PuzzleEntity;

/**
 * Service to access Puzzle entities
 */
public interface PuzzleService {

    /**
     * Returns puzzle with puzzleId
     * @param puzzleId, e.g. preventive
     * @return
     */
    PuzzleEntity getPuzzle(String puzzleId);
    
    /**
     * Saves puzzle
     * @param puzzle
     */
    void save(PuzzleEntity puzzle);
    
}
