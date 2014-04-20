package com.webther.pronun.data.service.impl;

import java.util.List;

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
    public PuzzleEntity getPuzzle(String puzzleCode) throws PuzzleEntityNotFoundException {
        LOGGER.debug("Requesting puzzle: {}", puzzleCode);

        List<PuzzleEntity> matching = repository.findByPuzzleCode(puzzleCode);
        if (!matching.isEmpty()) {
            return matching.get(0);
        }

        throw new PuzzleEntityNotFoundException(puzzleCode);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PuzzleEntity> getAllPuzzles() {
        return repository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(PuzzleEntity puzzle) {
        repository.save(puzzle);
    }

    @Override
    public void flush() {
        this.repository.flush();
    }

    public PuzzleRepository getRepository() {
        return repository;
    }

    public void setRepository(PuzzleRepository repository) {
        this.repository = repository;
    }

}
