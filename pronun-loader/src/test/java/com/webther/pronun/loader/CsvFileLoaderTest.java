package com.webther.pronun.loader;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import org.junit.Test;

import com.webther.pronun.loader.entity.PuzzleEntity;
import com.webther.pronun.loader.exception.IllegalCSVRowException;

/**
 * Class testing the {@link CsvFileLoader}
 * @author Barnabas
 *
 */
public class CsvFileLoaderTest {

	/**
	 * Test case:
	 *  There is a CSV file with two words and a header line
	 *  
	 * Expected:
	 *  The words are returned when retrieved from the CSV
	 *  
	 * @throws IOException 
	 * @throws IllegalCSVRowException 
	 */
	@Test
	public void thatReadsFile() throws IOException, IllegalCSVRowException {
		StringReader stringReader = new StringReader("Word\nfirst\nsecond");
		
		// Act
		List<PuzzleEntity> words = CsvFileLoader.getWordsFromFile(stringReader);
		
		// Assert
		assertEquals(2, words.size());
		assertEquals("first", words.get(0).getWord());
		assertEquals("second", words.get(1).getWord());
	}
}
