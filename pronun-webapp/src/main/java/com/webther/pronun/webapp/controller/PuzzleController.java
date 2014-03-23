package com.webther.pronun.webapp.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webther.pronun.data.entity.PuzzleEntity;
import com.webther.pronun.data.service.PuzzleService;

/**
 * Controller using the {link {@link PuzzleService}
 */
@Controller
public class PuzzleController {

    /** Logger instance */
    private static final Logger LOGGER = LoggerFactory.getLogger(PuzzleController.class);

    /** Service to access {@link PuzzleEntity} */
    @Autowired
    private PuzzleService service;

    @RequestMapping(value = "/puzzle/{puzzle_id}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<PuzzleEntity> getPuzzle(@PathVariable("puzzle_id") String puzzleId, HttpServletResponse response) {
        LOGGER.debug("Puzzle requested: {}", puzzleId);
        return new ResponseEntity<PuzzleEntity>(service.getPuzzle(puzzleId), HttpStatus.OK);
    }
}
