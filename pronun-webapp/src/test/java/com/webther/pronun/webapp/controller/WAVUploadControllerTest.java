package com.webther.pronun.webapp.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * Class testing the {@link WAVUploadController} class.
 */
public class WAVUploadControllerTest extends BaseControllerTest {
    
    /**
     * Test case: Uploading multi-part (audio/wav) content to
     * <code>/api/voice</code>
     * 
     * Expected: Response's status is {@link HttpStatus.OK}
     * 
     * @throws Exception
     */
    @Test
    @Ignore //TODO Justification: Invalid byte sequence throws Bad Request, needs unit test refactoring
    public void thatVoiceUploadReturnsHttpOk() throws Exception {

        // Arrange
        MockMultipartFile voiceFile = new MockMultipartFile("content", "voice.wav", "audio/wav", "123456789".getBytes());

        // Act & Assert
        mockMvc.perform(
                MockMvcRequestBuilders.fileUpload(WAVUploadController.URI).file(voiceFile)
                        .header(WAVUploadController.SESSION_ID_HEADER, "session123")
                        .header(WAVUploadController.PUZZLE_ID_HEADER, "often")).andExpect(
                status().is(HttpStatus.OK.value()));
    }

    /**
     * Test case: Uploading <strong>two</strong> multipart (audio/wav) files
     * to </code>/api/voice</code>
     * 
     * Expected: Upload fails with <i>HTTP Bad Request</i>
     * 
     * @throws Exception
     */
    @Test
    public void thatVoiceUploadWithMultipleUploadFails() throws Exception {
        // Arrange
        MockMultipartFile firstVoiceFile = new MockMultipartFile("content", "voice.wav", "audio/wav",
                "123456789".getBytes());
        MockMultipartFile secondVoiceFile = new MockMultipartFile("content2", "voice2.wav", "audio/wav",
                "ABCDefgHIJK".getBytes());

        // Act & Assert
        mockMvc.perform(
                MockMvcRequestBuilders.fileUpload(WAVUploadController.URI).file(firstVoiceFile).file(secondVoiceFile)
                        .header(WAVUploadController.SESSION_ID_HEADER, "session123")
                        .header(WAVUploadController.PUZZLE_ID_HEADER, "often")).andExpect(
                status().is(HttpStatus.BAD_REQUEST.value()));
    }
}
