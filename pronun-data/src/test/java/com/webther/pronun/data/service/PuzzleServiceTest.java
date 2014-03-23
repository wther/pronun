package com.webther.pronun.data.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.webther.pronun.data.entity.PuzzleEntity;
import com.webther.pronun.data.service.impl.PuzzleServiceImpl;
import com.webther.pronun.data.util.BaseDataServiceTest;

/**
 * Class testing the {@link PuzzleServiceImpl} class.
 */
public class PuzzleServiceTest extends BaseDataServiceTest {

    /**
     * Target being tested
     */
	@Autowired
    private PuzzleService target;
    
    /**
     * Test case:
     * 	<code>schema.sql</code> is executed
     * 
     * Expected:
     *  Service can save and retrieve a {@link PuzzleEntity}
     */
    @Test
    public void testReturnsAnEntity(){
    	
    	final String puzzleCode = "puzzle";
    	
    	// Arrange
    	PuzzleEntity subject = new PuzzleEntity();
    	subject.setPuzzleCode(puzzleCode);
    	subject.setPuzzleText("Dadada DAA!");
    	subject.setSamplePath("samples/dadadada.mp3");
    	subject.setPhoneticText("D@D@D@ DAA!");
    	target.save(subject);
    	
    	// Act
    	PuzzleEntity result = target.getPuzzle(puzzleCode);
    	
    	// Assert
    	assertNotNull(result);
    	assertEquals(puzzleCode, subject.getPuzzleCode());
    }
}
