package com.webther.pronun.voice.service;

import com.webther.pronun.voice.entity.PuzzleEntity;

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
    
}
