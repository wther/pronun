package com.webther.pronun.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity for a Puzzle, e.g. its correct pronunciation, its category, its text representation, etc.
 */
@Entity
@Table(name = "puzzles")
public class PuzzleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "puzzle_id")
    private Long puzzleId;

    /**
     * Puzzle's ID string
     */
    @Column(name = "puzzle_code")
    private String puzzleCode;

    /**
     * Puzzle's text shown to the user
     */
    @Column(name = "puzzle_text")
    private String puzzleText;

    /**
     * Puzzle's phonetic text shown to the user
     */
    @Column(name = "phonetic")
    private String phoneticText;
    
    /**
     * Stream to audio file
     */
    @Column(name = "path")
    private String samplePath;

    public Long getPuzzleId() {
        return puzzleId;
    }

    public void setPuzzleId(Long puzzleId) {
        this.puzzleId = puzzleId;
    }

    public String getPuzzleCode() {
        return puzzleCode;
    }

    public void setPuzzleCode(String puzzleCode) {
        this.puzzleCode = puzzleCode;
    }

    public String getPuzzleText() {
        return puzzleText;
    }

    public void setPuzzleText(String puzzleText) {
        this.puzzleText = puzzleText;
    }

    public String getPhoneticText() {
        return phoneticText;
    }

    public void setPhoneticText(String phoneticText) {
        this.phoneticText = phoneticText;
    }

    public String getSamplePath() {
        return samplePath;
    }

    public void setSamplePath(String samplePath) {
        this.samplePath = samplePath;
    }

    @Override
    public String toString() {
        return "PuzzleEntity [puzzleId=" + puzzleId + ", puzzleCode=" + puzzleCode + ", puzzleText=" + puzzleText
                + ", phoneticText=" + phoneticText + ", samplePath=" + samplePath + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((puzzleId == null) ? 0 : puzzleId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PuzzleEntity other = (PuzzleEntity) obj;
        if (puzzleId == null) {
            if (other.puzzleId != null)
                return false;
        } else if (!puzzleId.equals(other.puzzleId))
            return false;
        return true;
    }
}
