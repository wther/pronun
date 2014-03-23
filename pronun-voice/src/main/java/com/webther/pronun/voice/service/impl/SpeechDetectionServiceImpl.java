package com.webther.pronun.voice.service.impl;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.springframework.stereotype.Service;

import be.hogent.tarsos.dsp.AudioDispatcher;

import com.webther.pronun.voice.entity.SpeechInterval;
import com.webther.pronun.voice.processor.SpeechDetector;
import com.webther.pronun.voice.service.SpeechDetectionService;

/**
 * Service for detecting {@link SpeechInterval} from an {@link AudioInputStream}
 * data
 */
@Service
public class SpeechDetectionServiceImpl implements SpeechDetectionService {

    /**
     * {@inheritDoc}
     */
    @Override
    public SpeechInterval getIntervalMeta(final AudioInputStream stream) {
        try {
            AudioDispatcher dispatcher = new AudioDispatcher(stream, 512, 0);

            SpeechDetector detector = new SpeechDetector();
            dispatcher.addAudioProcessor(detector);
            
            Thread dispactherThread= new Thread(dispatcher);
            dispactherThread.start();

            return detector.waitForBestInterval();

        } catch (UnsupportedAudioFileException e) {
            // TODO more robust exception handling
            throw new RuntimeException(e);
        }
    }
}
