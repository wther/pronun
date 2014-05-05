package com.webther.pronun.loader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

import com.webther.pronun.loader.entity.PuzzleEntity;
import com.webther.pronun.loader.exception.IllegalCSVRowException;

/**
 * Class loading a csv file
 * 
 * @author Barnabas
 * 
 */
public class CsvFileLoader {
    
    /**
     * Private constructor for utility class
     */
    private CsvFileLoader() {
        
    }

	/**
	 * Load content from {@link FileReader}
	 * 
	 * @param contentReader Path of the file to access it
	 * @throws IOException
	 * @throws IllegalCSVRowException 
	 */
	public static List<PuzzleEntity> getWordsFromFile(String filePath)  throws IOException, IllegalCSVRowException {
		return getWordsFromFile(new FileReader(filePath));
	}

	/**
	 * Load CSV content from reader
	 * 
	 * @param contentReader Reader to access file content
	 * @throws IOException
	 * @throws IllegalCSVRowException 
	 */
	public static List<PuzzleEntity> getWordsFromFile(Reader contentReader) throws IOException, IllegalCSVRowException {
		
		List<PuzzleEntity> retVal = new ArrayList<PuzzleEntity>();
		
		CSVReader reader = null;
		try {
			reader = new CSVReader(contentReader);
			
			String[] nextLine;
			int line = 0;
			
			while ((nextLine = reader.readNext()) != null) {
				if(nextLine.length == 0){
					throw new IllegalCSVRowException("Should be at least one word per line!", line);
				}
				
				if(line > 0){
					PuzzleEntity row = new PuzzleEntity();
					
					row.setWord(nextLine[0].trim());
					
					if(nextLine.length > 1){
						row.setIpa(nextLine[1].trim());
					}
					
					retVal.add(row);
				}
				
				line++;
			}
			
		} finally {
		    if(reader != null){
		        reader.close();
		    }
		}
		return retVal;
	}
	
	/**
	 * Persists modified CSV content to file
	 * 
	 * @param filePath
	 * @param entities
	 * @throws IOException 
	 */
	public static void writeEntitiesToFile(String filePath, List<PuzzleEntity> entities) throws IOException{
	    CSVWriter writer = null;
	    try {
	        File file = new File(filePath);
	        if(!file.exists()){
	            file.createNewFile();
	        }
	        
	        writer = new CSVWriter(new FileWriter(filePath), ',', '\"');
	        writer.writeNext(new String[] {"Words", "Phonetic"} );
	        for(PuzzleEntity row : entities){
	            String[] line = new String[] {
	                    row.getWord(), row.getIpa()
	            };
	            writer.writeNext(line);
	        }
	    } finally {
	        if(writer != null){
	            writer.close();
	        }
	    }
	}
}
