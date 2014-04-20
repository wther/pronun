package com.webther.pronun.data.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.webther.pronun.data.entity.PuzzleEntity;
import com.webther.pronun.data.service.impl.PuzzleEntityNotFoundException;
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
     * 	Inserting a new {@link PuzzleEntity} into the database, and 
     *  attempting to read it back.
     * 
     * Expected:
     *  Service can save and retrieve a {@link PuzzleEntity}
     * @throws PuzzleEntityNotFoundException 
     */
    @Test
    public void testReturnsAnEntity() throws PuzzleEntityNotFoundException{
    	
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
    
    /**
     * Test case:
     * 	Inserting a new {@link PuzzleEntity} into the database, and 
     *  attempting to read an other back.
     *  
     * Expected:
     *  Execution throws exception.
     * 
     * @throws PuzzleEntityNotFoundException
     */
    @Test(expected = PuzzleEntityNotFoundException.class)
    public void thatThrowsExceptionIfNotFound() throws PuzzleEntityNotFoundException {
    	
    	PuzzleEntity subject = new PuzzleEntity();
    	subject.setPuzzleCode("existing");
    	target.save(subject);
    	
    	// Act
    	target.getPuzzle("missing");
    }
    
    /**
     * Test case:
     *  Inserting a single {@link PuzzleEntity} into the reposity.
     * 
     * Expected:
     *  {@link PuzzleService#getAllPuzzles()} returns it.
     */
    @Test
    public void thatFetchAllReturnsInserted(){

        PuzzleEntity subject = new PuzzleEntity();
        subject.setPuzzleCode("existing");
        target.save(subject);
        
        // Act
        List<PuzzleEntity> entityList = target.getAllPuzzles();
        
        assertEquals(1, entityList.size());
        assertEquals(subject.getPuzzleCode(), entityList.get(0).getPuzzleCode());
    }
}
