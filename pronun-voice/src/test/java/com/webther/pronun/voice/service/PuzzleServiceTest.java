package com.webther.pronun.voice.service;

import static org.junit.Assert.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.webther.pronun.voice.AudioTestUtil;
import com.webther.pronun.voice.entity.PuzzleEntity;
import com.webther.pronun.voice.service.impl.PuzzleServiceImpl;

/**
 * Class testing the {@link PuzzleServiceImpl} class.
 */
public class PuzzleServiceTest {

    /** Logger instance */
    private static final Logger LOGGER = LoggerFactory.getLogger(PuzzleServiceTest.class);
    
    /**
     * Target being tested
     */
    private PuzzleService target;
    
    @Before
    public void setup(){
        this.target = new PuzzleServiceImpl();
    }
}
