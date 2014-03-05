package com.webther.pronun.voice.service;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.springframework.stereotype.Service;

import be.hogent.tarsos.dsp.AudioDispatcher;

import com.webther.pronun.voice.processor.SpeechDetector;
import com.webther.pronun.voice.processor.SpeechInterval;

/**
 * Service for detecting {@link SpeechInterval} from an {@link AudioInputStream}
 * data
 */
@Service
public class SpeechDetectionService {

    /**
     * Parse uploaded content
     * 
     * @param data
     * @return
     */
    public SpeechInterval getIntervalMeta(final AudioInputStream stream) {
        try {
            AudioDispatcher dispatcher = new AudioDispatcher(stream, 512, 0);

            SpeechDetector detector = new SpeechDetector();
            dispatcher.addAudioProcessor(detector);
            dispatcher.run();

            return detector.waitForBestInterval();

        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        }
    }
}
