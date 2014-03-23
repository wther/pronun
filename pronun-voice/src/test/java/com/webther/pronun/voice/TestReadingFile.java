package com.webther.pronun.voice;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import org.junit.Ignore;
import org.junit.Test;

/**
 * Class testing the reading of WAV files
 */
public class TestReadingFile {

    @Test
    @Ignore
    public void thatLibReadsFile() throws Exception {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getAudioFile());

        System.out.println("Float encoding: "
                + (audioInputStream.getFormat().isBigEndian() ? "Big Endian" : "Little Endian"));
        System.out.println("Frame rate:" + audioInputStream.getFormat().getFrameRate());
        System.out.println("Frame count:" + audioInputStream.getFrameLength());

        double durationMSec = (long) ((audioInputStream.getFrameLength() * 1000) / audioInputStream.getFormat()
                .getFrameRate());
        ;
        System.out.print("Duration: " + durationMSec + " ms");
    }

    /**
     * Reads Audio file from resources
     * 
     * @return
     */
    private File getAudioFile() {
        URL url = TestReadingFile.class.getClass().getResource("/voice-samples/voice-hun-alma.wav");
        try {
            return new File(url.toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
