package com.webther.pronun.voice;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Utility class providing sample files
 */
public class AudioTestUtil {
    
    /**
     * Reads Audio file from resources containing a spoken word around 4.0 seconds
     */
    public static File getSimpleAudioFile() {
        URL url = TestReadingFile.class.getClass().getResource("/voice-samples/voice-hun-alma.wav");
        try {
            return new File(url.toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
