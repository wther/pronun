package com.webther.pronun.voice.service;

import javax.sound.sampled.AudioInputStream;

import com.webther.pronun.voice.processor.SpeechInterval;

public interface SpeechDetectionService {

    /**
     * Parse uploaded content
     * 
     * @param data
     * @return Interval of actual speech
     */
    public abstract SpeechInterval getIntervalMeta(AudioInputStream stream);

}