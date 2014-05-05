package com.webther.pronun.loader;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.codehaus.plexus.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.StaticLoggerBinder;

import au.com.bytecode.opencsv.CSVWriter;

import com.webther.pronun.loader.entity.PuzzleEntity;
import com.webther.pronun.loader.exception.IllegalCSVRowException;
import com.webther.pronun.loader.exception.UnresolveableIPAException;

/**
 * Entry point for the custom maven plugin.
 * 
 * @author Barnabas
 */
@Mojo(name="load")
public class PronunLoaderMojo extends AbstractMojo {
    
    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PronunLoaderMojo.class);

    /**
     * Directory where native samples are downloaded
     */
    //@Parameter(defaultValue = "src/main/webapp/samples/" )
    @Parameter
    private String directory;
    
    /**
     * File where the CSV configuration of the entities are stored
     */
    @Parameter
    private String csvFile;
    
    /**
     * File where the CSV configuration of the entity meta information will be written
     */
    @Parameter
    private String outFile;
    
    /**
     * Entry point of the plugin
     */
	public void execute() throws MojoExecutionException, MojoFailureException {
		StaticLoggerBinder.getSingleton().setLog(getLog());
		
		LOGGER.info("Executing PronunLoader");
		
		// Verify output directory
		File outputDirResource = new File(directory);
		if(!outputDirResource.exists()){
            throw new MojoExecutionException("Provided native-directory doesn't exist: " + directory);
        }
		
		if(!outputDirResource.canWrite()){
		    throw new MojoExecutionException("Provided native-directory is not writable: " + directory);
		}
		
		List<PuzzleEntity> fileContents = null;
		try {
	        // Load CSV content
            fileContents = CsvFileLoader.getWordsFromFile(csvFile);
            
            for(PuzzleEntity row : fileContents){
                LOGGER.info("Resolving content for {}", row.getWord());
                executeLine(row, directory);
            }
            
            // Persist
            CsvFileLoader.writeEntitiesToFile(outFile, fileContents);

        } catch (IOException e) {
            throw new MojoExecutionException("Failed to open CSV file", e);
        } catch (IllegalCSVRowException e) {
            throw new MojoExecutionException("Failed to open CSV file", e);
        }
	}
	
	/**
	 * Fix just one line
	 * @param line
	 * @returns true if succeeded
	 */
	public static boolean executeLine(PuzzleEntity line, String nativeSampleDir){
	    
	    final String word = line.getWord();
	    
	    // Missing IPA
	    if(StringUtils.isEmpty(line.getIpa())){
	        try {
                final String ipa = IPATextResolver.getIPAForWord(word);
                line.setIpa(ipa);
            } catch (IOException e) {
                LOGGER.warn("Failed to connect to phonetic resources for {}", word, e);
                return false;
            } catch (UnresolveableIPAException e) {
                LOGGER.warn("Failed to resolve phonetic text for {}", word, e);
                return false;
            }
	    }
	    
	    // Missing file
	    final String path = nativeSampleDir + word + ".mp3";
	    File file = new File(path);
	    if(!file.exists()){
	        try {
                NativeSampleLoader.getNativeSample(word, nativeSampleDir);
            } catch (MalformedURLException e) {
                LOGGER.warn("Unable to reach native sample resources for {}", word, e);
                return false;
            } catch (IOException e) {
                LOGGER.warn("Failed to retrieve native sample for {}", word, e);
                return false;
            }
	    }
	    
	    return true;
	}

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public void setCsvFile(String csvFile) {
        this.csvFile = csvFile;
    }

    public void setOutFile(String outFile) {
        this.outFile = outFile;
    }
}
