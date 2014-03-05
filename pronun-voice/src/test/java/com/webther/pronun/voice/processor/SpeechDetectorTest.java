package com.webther.pronun.voice.processor;

import static org.junit.Assert.*;

import org.junit.Test;

import be.hogent.tarsos.dsp.AudioDispatcher;

import com.webther.pronun.voice.AudioTestUtil;

/**
 * Class testing the {@link SpeechDetector} class.
 */
public class SpeechDetectorTest {

    /**
     * Test case: There is a mock audio sample with a single work spoken around
     * [4.0 sec ->  4.5 sec] We extract speech interval from this sample
     * 
     * Expected: The {@link SpeechInterval} result is approximately this time
     * interval
     * 
     */
    @Test
    public void thatDetectsSampleBestInterval() throws Exception {

        // Arrange
        SpeechDetector target = new SpeechDetector();

        AudioDispatcher dispatcher = AudioDispatcher.fromFile(AudioTestUtil.getSimpleAudioFile(), 1024, 0);
        dispatcher.addAudioProcessor(target);

        // Act
        dispatcher.run();
        SpeechInterval result = target.waitForBestInterval();

        // Assert
        assertEquals(4.0, result.getSpeechStart(), 0.1);
        assertEquals(4.5, result.getSpeechEnd(), 0.1);
    }
}
