package com.webther.pronun.voice.entity;



/**
 * Entity for a Puzzle, e.g. its correct pronunciation, its category, its text representation, etc.
 */
public class PuzzleEntity {
    
    /**
     * Puzzle's ID string
     */
    private String puzzleId;

    /**
     * Stream to audio file
     */
    private String samplePath;
    
    public PuzzleEntity(String puzzleId, String samplePath) {
        super();
        this.puzzleId = puzzleId;
        this.samplePath = samplePath;
    }

    public String getSamplePath() {
        return samplePath;
    }

    public void setSamplePath(String samplePath) {
        this.samplePath = samplePath;
    }

    public String getPuzzleId() {
        return puzzleId;
    }

    public void setPuzzleId(String puzzleId) {
        this.puzzleId = puzzleId;
    }
}
