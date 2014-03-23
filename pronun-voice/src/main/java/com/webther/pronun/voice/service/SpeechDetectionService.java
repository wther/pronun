package com.webther.pronun.voice.service;

import javax.sound.sampled.AudioInputStream;

import com.webther.pronun.voice.entity.SpeechInterval;

/**
 * Service for detecting {@link SpeechInterval} from an {@link AudioInputStream}
 * data
 */
public interface SpeechDetectionService {

    /**
     * Parse uploaded content
     * 
     * @param data
     * @return Interval of actual speech
     */
    public abstract SpeechInterval getIntervalMeta(AudioInputStream stream);

}