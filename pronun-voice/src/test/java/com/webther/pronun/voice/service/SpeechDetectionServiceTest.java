package com.webther.pronun.voice.service;

import static org.junit.Assert.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import org.junit.Test;

import com.webther.pronun.voice.AudioTestUtil;
import com.webther.pronun.voice.processor.SpeechInterval;
import com.webther.pronun.voice.service.impl.SpeechDetectionServiceImpl;

/**
 * Class testing the {@link SpeechDetectionServiceImpl}
 */
public class SpeechDetectionServiceTest {

    /**
     * Test case: There is a mock audio sample, we read it as a
     * {@link AudioInputStream} and pass it to the service
     * 
     * Expected: The {@link SpeechInterval} result is approximately the time
     * interval of the speech in the sample
     */
    @Test
    public void thatServiceReadsByteArray() throws Exception {

        // Arrange
        SpeechDetectionService target = new SpeechDetectionServiceImpl();
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(AudioTestUtil.getSimpleAudioFile());

        // Act
        SpeechInterval interval = target.getIntervalMeta(audioInputStream);

        // Assert
        assertEquals(4.0, interval.getSpeechStart(), 0.1);
        assertEquals(4.5, interval.getSpeechEnd(), 0.1);
    }
}
