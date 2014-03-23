package com.webther.pronun.data.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webther.pronun.data.entity.PuzzleEntity;
import com.webther.pronun.data.repository.PuzzleRepository;
import com.webther.pronun.data.service.PuzzleService;


/**
 * Puzzle service implementation
 */
@Service
public class PuzzleServiceImpl implements PuzzleService {

    /** Logger instance */
    private static final Logger LOGGER = LoggerFactory.getLogger(PuzzleServiceImpl.class);
    
    /**
     * Repository to access entities
     */
    @Autowired
    private PuzzleRepository repository;

    /**
     * {@inheritDoc}
     */
    @Override
    public PuzzleEntity getPuzzle(String puzzleId) {
        LOGGER.debug("Requesting puzzle: {}", puzzleId);
        return repository.findAll().get(0);
    }

    /**
     * {@inheritDoc}
     */
	@Override
	public void save(PuzzleEntity puzzle) {
		repository.save(puzzle);
	}
}
