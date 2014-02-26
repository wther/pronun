package com.webther.pronun.webapp.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Class testing the {@link WAVUploadController} class.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:/spring/appServlet/mvc-applicationContext.xml")
public class WAVUploadControllerTest {
    
    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    
    /**
     * Test case:
     *  - Uploading multi-part (audio/wav) content to <code>/api/voice</code>
     *  
     * Expected:
     *  - Response's status is {@link HttpStatus.OK}
     * 
     * @throws Exception
     */
    @Test
    public void thatVoiceUploadReturnsHttpOk() throws Exception {
        
        // Arrange
        MockMultipartFile voiceFile = new MockMultipartFile("content", "voice.wav", "audio/wav", "123456789".getBytes());
        
        // Act & Assert        
        mockMvc.perform(MockMvcRequestBuilders
                        .fileUpload(WAVUploadController.URI)
                        .file(voiceFile)
                        .header(WAVUploadController.SESSION_ID_HEADER, "session123")
                        .header(WAVUploadController.PUZZLE_ID_HEADER, "often"))
                        .andExpect(status().is(HttpStatus.OK.value()));
    }
    
    
    /**
     * Test case:
     *  - Uploading <strong>two</strong> multipart (audio/wav) files to </code>/api/voice</code>
     *  
     * Expected:
     *  - Upload fails with <i>HTTP Bad Request</i>
     * 
     * @throws Exception
     */
    @Test
    public void thatVoiceUploadWithMultipleUploadFails() throws Exception {
        // Arrange
        MockMultipartFile firstVoiceFile = new MockMultipartFile("content", "voice.wav", "audio/wav", "123456789".getBytes());
        MockMultipartFile secondVoiceFile = new MockMultipartFile("content2", "voice2.wav", "audio/wav", "ABCDefgHIJK".getBytes());
        
        // Act & Assert        
        mockMvc.perform(MockMvcRequestBuilders
                        .fileUpload(WAVUploadController.URI)
                        .file(firstVoiceFile)
                        .file(secondVoiceFile)
                        .header(WAVUploadController.SESSION_ID_HEADER, "session123")
                        .header(WAVUploadController.PUZZLE_ID_HEADER, "often"))
                        .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
    }
    
    
}
