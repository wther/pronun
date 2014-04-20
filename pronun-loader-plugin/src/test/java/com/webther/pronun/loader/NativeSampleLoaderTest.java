package com.webther.pronun.loader;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Test;

/**
 * Class testing the {@link NativeSampleLoader} class.
 * 
 * @author Barnabas
 */
public class NativeSampleLoaderTest {
    
    /**
     * Output directory for the unit test
     */
    private static final String outputDir = System.getProperty("java.io.tmpdir") + "/pronun-maven-plugin/";
    
    /**
     * Clean up
     */
    @After
    public void cleanUp(){

        // Clean up
        File existingFile = new File(outputDir + "height.mp3");
        if(existingFile.exists()){
            existingFile.delete();
        }
     
    }
    
    /**
     * Test case:
     *  Attempting to download an mp3 native sample for a file.
     *  
     * Expected:
     * 
     * It's downloaded and moved to the directory specified.
     * 
     */
    @Test
    public void thatItDownloadsMp3() throws Exception {
        // Act
        NativeSampleLoader.getNativeSample("height", outputDir);
        
        // Assert
        File newFile = new File(outputDir + "height.mp3");
        assertTrue(newFile.exists());
        assertNotEquals(0, newFile.length());
    }
}
