/*
 *   Copyright (c) 2014 Sonrisa Informatikai Kft. All Rights Reserved.
 * 
 *  This software is the confidential and proprietary information of
 *  Sonrisa Informatikai Kft. ("Confidential Information").
 *  You shall not disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into
 *  with Sonrisa.
 * 
 *  SONRISA MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
 *  THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *  TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 *  PARTICULAR PURPOSE, OR NON-INFRINGEMENT. SONRISA SHALL NOT BE LIABLE FOR
 *  ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
 *  DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 */

package com.webther.pronun.voice;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import org.junit.Ignore;
import org.junit.Test;

import be.hogent.tarsos.dsp.AudioDispatcher;
import be.hogent.tarsos.dsp.AudioEvent;
import be.hogent.tarsos.dsp.AudioProcessor;

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
