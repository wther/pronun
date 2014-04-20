package com.webther.pronun.loader;

import static org.junit.Assert.*;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Test;

import com.webther.pronun.loader.entity.PuzzleEntity;

/**
 * Class testing the {@link PronunLoaderMojo}
 * 
 * @author Barnabas
 */
public class PronunLoaderMojoTest {

    /**
     * Output directory for the unit test
     */
    private static final String outputDir = System.getProperty("java.io.tmpdir") + "/pronun-maven-plugin/";

    /**
     * Test subject
     */
    private static final String word = "weather";
    
    /**
     * Remove file from directory
     */
    @After
    public void cleanUp(){

        // Clean up
        File existingFile = new File(outputDir + word + ".mp3");
        if(existingFile.exists()){
            existingFile.delete();
        }
        
    }
    
    /**
     * Test case:
     *  A line is received with no phonetic/no file
     *  
     * Expected:
     *  Line is fixed with content and file downloaded.
     * 
     */
    @Test
    public void thatFixesEmptyRows(){
        PuzzleEntity subject = new PuzzleEntity();
        subject.setWord(word);
        
        // Act
        boolean result = PronunLoaderMojo.executeLine(subject, outputDir);
        
        // Assert
        assertEquals(true, result);
        
        File newFile = new File(outputDir + word + ".mp3");
        assertTrue(newFile.exists());
    }
    
    /**
     * Test case:
     *  A line is received with no phonetic/no file
     *  
     * Expected:
     *  Line is fixed with content and file downloaded,
     *  but multiple executions do nothing
     * 
     */
    @Test
    public void thatExecutingMoreThanOnceHasNoEffect(){
        PuzzleEntity subject = new PuzzleEntity();
        subject.setWord(word);
        
        // Act
        PronunLoaderMojo.executeLine(subject, outputDir);
        PronunLoaderMojo.executeLine(subject, outputDir);
        PronunLoaderMojo.executeLine(subject, outputDir);
        boolean result = PronunLoaderMojo.executeLine(subject, outputDir);
        
        // Assert
        assertEquals(true, result);
        
        File newFile = new File(outputDir + word + ".mp3");
        assertTrue(newFile.exists());
    }
    
    /**
     * Test case:
     *  Create CSV file, pass it to the plugin
     * 
     * Expected:
     *  CSV file is fixed with correct content
     */
    @Test
    public void thatReadsCsvFile() throws Exception {
        
        final String path = outputDir + "content.csv";
        FileUtils.copyFile(new File("src/test/resources/content.csv"), new File(path));
        
        PronunLoaderMojo target = new PronunLoaderMojo();
        target.setCsvFile(path);
        target.setOutputDir(outputDir);
        
        // Act
        target.execute();
    }
}
