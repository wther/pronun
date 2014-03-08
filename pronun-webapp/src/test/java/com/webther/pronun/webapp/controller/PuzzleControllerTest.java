package com.webther.pronun.webapp.controller;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Test;

/**
 * Testing the {@link PuzzleController} class.
 */
public class PuzzleControllerTest extends BaseControllerTest {

    /**
     * Test case: Attempting to download a puzzle from
     * <code>/puzzle/preventive</code> 
     * 
     * Expected: Download returns OK, with content-type matching <i>audio/x-wav</i> 
     * and puzzle id header is set
     * 
     * @throws Exception
     */
    @Test
    public void thatDownloadContentTypeIsWav() throws Exception {

        // Act & Assert
        mockMvc.perform(get("/puzzle/preventive"))
                .andExpect(jsonPath("$.puzzleId", is("preventive")));
    }

}
