package com.webther.pronun.loader;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class loading MP3 native samples for a word
 * 
 * @author Barnabas
 */
public class NativeSampleLoader {
    
    /**
     * Logger used
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(NativeSampleLoader.class);
    
    /**
     * Prefix to load resources from
     */
    public static final String URI_PREFIX = "http://www.howjsay.com/mp3/";
    

    /**
     * Private constructor for utility class
     */
    private NativeSampleLoader() {
        
    }

    /**
     * Loads native sample for a word
     * @param word
     * @throws IOException 
     * @throws MalformedURLException 
     */
    public static void getNativeSample(String word, String outputDir) throws MalformedURLException, IOException{
        final String url = URI_PREFIX + word + ".mp3";
        final String path = outputDir + word + ".mp3";
        
        LOGGER.info("Downloading {} to {}", url, path);
        FileUtils.copyURLToFile(new URL(url), new File(path));
    }
}
