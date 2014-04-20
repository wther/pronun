package com.webther.pronun.webapp.controller;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webther.pronun.data.service.PuzzleService;

/**
 * Testing the {@link PuzzleController} class.
 */
public class PuzzleControllerTest extends BaseWebappTest {
    
    /**
     * Service used to insert content
     */
    @Autowired
    private PuzzleService service;

    /**
     * Test case: Attempting to download a puzzle from
     * <code>/puzzles</code> 
     * 
     * Expected: Download returns OK, with the first row
     * from <code>entityLists.csv</code>
     * 
     * @throws Exception
     */
    @Test
    public void thatEntityListIsReturned() throws Exception {
        
        // Act & Assert
        mockMvc.perform(get("/puzzles/"))
                .andExpect(jsonPath("$[0].puzzleCode", is("height")));
    }
}
