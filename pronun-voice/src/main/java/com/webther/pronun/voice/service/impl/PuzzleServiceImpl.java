package com.webther.pronun.voice.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.webther.pronun.voice.entity.PuzzleEntity;
import com.webther.pronun.voice.service.PuzzleService;

/**
 * Puzzle service implementation
 */
@Service
public class PuzzleServiceImpl implements PuzzleService {

    /** Logger instance */
    private static final Logger LOGGER = LoggerFactory.getLogger(PuzzleServiceImpl.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public PuzzleEntity getPuzzle(String puzzleId) {
        LOGGER.debug("Requesting puzzle: {}", puzzleId);

        return new PuzzleEntity(puzzleId, "/samples/" + puzzleId + ".mp3");
    }

}
