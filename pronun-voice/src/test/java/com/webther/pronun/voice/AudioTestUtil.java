package com.webther.pronun.voice;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import com.webther.pronun.voice.service.PuzzleService;

/**
 * Utility class providing sample files
 */
public class AudioTestUtil {
    
    /**
     * Reads Audio file from resources containing a spoken word around 4.0 seconds
     */
    public static File getSimpleAudioFile() {
        return getResourceFile("/voice-samples/voice-hun-alma.wav", AudioTestUtil.class);

    }
    
    /**
     * Reads Audio file from resources containing a native audio sample
     */
    public static File getNativeAudioSample(String fileName){
        return getResourceFile("/samples/" + fileName, PuzzleService.class);
    }
    
    /**
     * Get resource as file
     * @param path
     * @return
     */
    private static File getResourceFile(String path, Class<?> clazz){
        URL url = clazz.getResource(path);
        if(url == null){
            throw new RuntimeException("File not found: " + path);
        }
        try {
            return new File(url.toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
