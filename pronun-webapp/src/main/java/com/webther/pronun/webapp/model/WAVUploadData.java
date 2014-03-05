package com.webther.pronun.webapp.model;

import java.io.BufferedInputStream;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.springframework.web.multipart.MultipartFile;

/**
 * Model entity for uploaded file. Normally it contains a <code>byte[]</code>
 * array of a WAV file.
 */
public class WAVUploadData {

    /**
     * SessionId identifying the source of the file
     */
    private String sessionId;

    /**
     * Puzzle id identifying the different puzzles (e.g. "pronounce schedule",
     * "pronounce good morning")
     */
    private String puzzleId;

    /**
     * Sample's data
     */
    private AudioInputStream audioData;

    /**
     * Initializes model
     */
    public WAVUploadData(String sessionId, String puzzleId, AudioInputStream audioData) {
        this.sessionId = sessionId;
        this.puzzleId = puzzleId;
        this.audioData = audioData;
    }
    /**
     * Initializes model
     */
    public WAVUploadData(String sessionId, String puzzleId, MultipartFile file) throws UnsupportedAudioFileException, IOException {
        this.sessionId = sessionId;
        this.puzzleId = puzzleId;
        this.audioData =  AudioSystem.getAudioInputStream(new BufferedInputStream(file.getInputStream()));
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getPuzzleId() {
        return puzzleId;
    }
    
    public AudioInputStream getAudioStream() {
        return audioData;
    }
    
    @Override
    public String toString() {
        return "WAVFileUpload [sessionId=" + sessionId + ", puzzleId=" + puzzleId + "]";
    }
}
