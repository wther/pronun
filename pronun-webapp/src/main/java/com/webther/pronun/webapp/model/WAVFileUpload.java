package com.webther.pronun.webapp.model;

/**
 * Model entity for uploaded file. Normally it contains a <code>byte[]</code> array
 * of a WAV file.
 */
public class WAVFileUpload {

    /**
     * SessionId identifying the source of the file
     */
    private String sessionId;
    
    /**
     * Puzzle id identifying the different puzzles (e.g. "pronounce schedule", "pronounce good morning")
     */
    private String puzzleId;

    /**
     * Sample's data
     */
    private byte[] content;

    /**
     * Initializes model
     * @param sessionId {@link WAVFileUpload#sessionId}
     * @param puzzleId {@link WAVFileUpload#puzzleId}
     * @param content WAV content
     * @throws IllegalArgumentException If content is <i>null</i>
     */
    public WAVFileUpload(String sessionId, String puzzleId, byte[] content) {
        if(content == null){
            throw new IllegalArgumentException("content");
        }
        
        this.sessionId = sessionId;
        this.puzzleId = puzzleId;
        this.content = content;
    }

    public byte[] getContent() {
        return content;
    }

    public void setData(byte[] data) {
        this.content = data;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getPuzzleId() {
        return puzzleId;
    }

    @Override
    public String toString() {
        return "WAVFileUpload [sessionId=" + sessionId + ", puzzleId=" + puzzleId + "]";
    }
}
