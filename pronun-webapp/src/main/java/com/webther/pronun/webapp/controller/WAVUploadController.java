package com.webther.pronun.webapp.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.webther.pronun.webapp.exception.InvalidUploadFormatException;
import com.webther.pronun.webapp.model.WAVFileUpload;

/**
 * Controller for uploading files. These files are sound streams captured by
 * the microphone of the user.
 */
@Controller
@RequestMapping(WAVUploadController.URI)
public class WAVUploadController {

    /** Logger instance */
    private static final Logger LOGGER = LoggerFactory.getLogger(WAVUploadController.class);

    /** The URI of this controller. */
    public static final String URI = "/voice";

    /**
     * Header tag marking which session the voice is related to
     */
    public static final String SESSION_ID_HEADER = "X-Voice-Session";
    
    /**
     * Header tag marking which puzzle the user was attempting to solve
     */
    public static final String PUZZLE_ID_HEADER = "X-Puzzle-Id";

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<String> upload(
            @RequestHeader(SESSION_ID_HEADER) String sessionId,
            @RequestHeader(PUZZLE_ID_HEADER) String puzzleId,
            MultipartHttpServletRequest request)
            throws InvalidUploadFormatException {
              
        // Parse content
        LOGGER.info("Content uploaded by {} for {}", sessionId, puzzleId);
        
        // We expect only a single file to be uploaded, 
        // otherwise lets throw an exception containing the names of
        // the uploaded files
        if(request.getFileMap().size() != 1){
            final String errMsg = "Expected only one file to uploaded but received: " + getOriginalFileNames(request);
            LOGGER.warn(errMsg);

            return new ResponseEntity<String>(errMsg, HttpStatus.BAD_REQUEST);
        }
        
        // Process all files to be a {@link WAVFileUpload} 
        MultipartFile file = request.getFileMap().values().iterator().next();
        byte[] content = null;
        try {
            content = file.getBytes();
            LOGGER.debug("Uploaded file ({} bytes)", content.length);
        } catch (IOException e) {
            throw new InvalidUploadFormatException("Failed to read upload content", e);
        }
        
        WAVFileUpload entity = new WAVFileUpload(sessionId, puzzleId, content);
        LOGGER.debug("Entity created {}", entity);
        
        return new ResponseEntity<String>(HttpStatus.OK);        
    }

    /**
     * Utility function used for generating an error message
     * @param request
     * @return
     */
    private String getOriginalFileNames(MultipartHttpServletRequest request) {
        StringBuilder originalNames = new StringBuilder();
        for(MultipartFile file : request.getFileMap().values()){
            if(originalNames.length() != 0){
                originalNames.append(',');
            }
            originalNames.append(file.getOriginalFilename());
        }
        return originalNames.toString();
    }
}
