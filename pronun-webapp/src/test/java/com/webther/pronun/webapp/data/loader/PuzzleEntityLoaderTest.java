package com.webther.pronun.webapp.data.loader;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.webther.pronun.data.entity.PuzzleEntity;
import com.webther.pronun.data.service.PuzzleService;
import com.webther.pronun.data.service.impl.PuzzleEntityNotFoundException;
import com.webther.pronun.webapp.controller.BaseWebappTest;

/**
 * Class testing the {@link PuzzleEntityLoader}
 * @author Barnabas
 *
 */
public class PuzzleEntityLoaderTest extends BaseWebappTest {
    
    /**
     * Target being tested
     */
    @Autowired
    private PuzzleService service;
    
    /**
     * Test case:
     *  The <code>entityList.csv</code> contains all sorts of entity definitions.
     *  
     * Expected:
     *  These are persisted as {@link PuzzleEntity} entities
     * @throws PuzzleEntityNotFoundException 
     * 
     */
    @Test
    public void thatPuzzleRepositoryIsInitialized() throws PuzzleEntityNotFoundException{
        PuzzleEntity third = service.getPuzzle("third");
        assertNotNull(third.getPhoneticText());
    }
}
