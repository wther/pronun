package com.webther.pronun.webapp.controller;

import java.io.IOException;

import javax.sound.sampled.UnsupportedAudioFileException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.webther.pronun.voice.entity.SpeechInterval;
import com.webther.pronun.voice.service.SpeechDetectionService;
import com.webther.pronun.webapp.entity.WAVUploadEntity;
import com.webther.pronun.webapp.exception.InvalidUploadFormatException;

/**
 * Controller for uploading files. These files are sound streams captured by the
 * microphone of the user.
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

    /**
     * Speech service provides an interface to generate meta information on the
     * WAV file
     */
    @Autowired
    private SpeechDetectionService speechService;

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<SpeechInterval> upload(@RequestHeader(SESSION_ID_HEADER) String sessionId,
            @RequestHeader(PUZZLE_ID_HEADER) String puzzleId, MultipartHttpServletRequest request)
            throws InvalidUploadFormatException {

        // Parse content
        LOGGER.info("Content uploaded by {} for {}", sessionId, puzzleId);

        // We expect only a single file to be uploaded,
        // otherwise lets throw an exception containing the names of
        // the uploaded files
        if (request.getFileMap().size() != 1) {
            final String errMsg = "Expected only one file to uploaded but received: " + getOriginalFileNames(request);
            LOGGER.warn(errMsg);

            return new ResponseEntity<SpeechInterval>(HttpStatus.BAD_REQUEST);
        }

        // Process all files to be a {@link WAVFileUpload}
        MultipartFile file = request.getFileMap().values().iterator().next();
        try {

            WAVUploadEntity entity = new WAVUploadEntity(sessionId, puzzleId, file);
            SpeechInterval interval = speechService.getIntervalMeta(entity.getAudioStream());
            return new ResponseEntity<SpeechInterval>(interval, HttpStatus.OK);
            
        } catch (IOException e) {
            throw new InvalidUploadFormatException("Failed to read upload content", e);
        } catch (UnsupportedAudioFileException e) {
            throw new InvalidUploadFormatException("Failed to parse audio", e);
        }
    }

    /**
     * Utility function used for generating an error message
     * 
     * @param request
     * @return
     */
    private String getOriginalFileNames(MultipartHttpServletRequest request) {
        StringBuilder originalNames = new StringBuilder();
        for (MultipartFile file : request.getFileMap().values()) {
            if (originalNames.length() != 0) {
                originalNames.append(',');
            }
            originalNames.append(file.getOriginalFilename());
        }
        return originalNames.toString();
    }

    public SpeechDetectionService getSpeechService() {
        return speechService;
    }

    public void setSpeechService(SpeechDetectionService speechService) {
        this.speechService = speechService;
    }
}
