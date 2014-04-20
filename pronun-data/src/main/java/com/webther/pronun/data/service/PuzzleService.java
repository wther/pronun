package com.webther.pronun.data.service;

import java.util.List;

import com.webther.pronun.data.entity.PuzzleEntity;
import com.webther.pronun.data.service.impl.PuzzleEntityNotFoundException;

/**
 * Service to access Puzzle entities
 */
public interface PuzzleService {

    /**
     * Returns puzzle with puzzleId
     * @param puzzleId, e.g. preventive
     * @return
     * @throws PuzzleEntityNotFoundException 
     */
    PuzzleEntity getPuzzle(String puzzleCode) throws PuzzleEntityNotFoundException;
    
    /**
     * Returns a list of {@link PuzzleEntity}
     * @return
     */
    List<PuzzleEntity> getAllPuzzles();
    
    /**
     * Saves puzzle
     * @param puzzle
     */
    void save(PuzzleEntity puzzle);

    /**
     * Flush
     */
    void flush();
    
}
