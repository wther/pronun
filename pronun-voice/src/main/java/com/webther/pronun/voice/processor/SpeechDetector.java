package com.webther.pronun.voice.processor;

import be.hogent.tarsos.dsp.AudioEvent;
import be.hogent.tarsos.dsp.AudioProcessor;

/**
 * Sound detector is an audio processor which detects the speech interval from an
 * audio sample. It assumes that the amplitude of the audio is higher during speech
 * than otherwise.
 */
public class SpeechDetector implements AudioProcessor {

    /**
     * Threshold over which sound is considered speech
     */
    //TODO Uses constant threshold, this should be adaptive
    private static float THRESHOLD = -70; // dB

    /**
     * Threshold in seconds over which an interval is considered too long
     */
    private static float INTERVAL_THRESHOLD = 6;

    /**
     * During processing we cache to best interval found
     */
    private SpeechInterval bestInterval = null;

    /**
     * While state is <i>SILENCE</i> this variable holds the information on the
     * previous speech's timestamp
     */
    private double lastSpeechStart = 0;

    /**
     * Indicates that processing was finished
     */
    private boolean finished = false;

    /**
     * Internal state of the processor
     */
    private static enum State {
        SILENCE, DETECTING_SPEECH
    }

    /**
     * Current state of the processor
     */
    private State state = State.SILENCE;

    /**
     * Initializes a new instance of the {@link SpeechDetector} class.
     */
    public SpeechDetector() {
        // If no speech is detected, detect everything as speech
        bestInterval = new SpeechInterval(0.0D, Double.MAX_VALUE);
    }

    /**
     * Synchronized method to wait for the result of the processing
     * 
     * @return Longest interval of speech not longer than
     *         {@link #INTERVAL_THRESHOLD} or full interval if non-found
     */
    public synchronized SpeechInterval waitForBestInterval() {
        while (!isFinished()) {
            try {
                wait();
            } catch (InterruptedException e) {
                // Check the while condition again
            }
        }

        return bestInterval;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean process(AudioEvent audioEvent) {
        float[] buffer = audioEvent.getFloatBuffer();
        double level = soundPressureLevel(buffer);

        if (state == State.SILENCE) {
            if (level > THRESHOLD) {
                lastSpeechStart = audioEvent.getTimeStamp();
                this.state = State.DETECTING_SPEECH;
            }
        }

        else if (state == State.DETECTING_SPEECH) {
            if (level < THRESHOLD) {
                SpeechInterval candidate = new SpeechInterval(lastSpeechStart, audioEvent.getTimeStamp());
                if (isBetterInterval(candidate)) {
                    bestInterval = candidate;
                }
                state = State.SILENCE;
            }
        }

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void processingFinished() {
        finished = true;
        notify();
    }

    /**
     * @return Value indicating that {@link #processingFinished()} was triggered
     */
    public boolean isFinished() {
        return finished;
    }

    /**
     * Returns true if candidate interval is considered better than current best
     * 
     * @param candidate
     * @return
     */
    private boolean isBetterInterval(SpeechInterval candidate) {
        if (bestInterval.getInterval() > INTERVAL_THRESHOLD) {
            return true;
        }

        if (candidate.getInterval() > INTERVAL_THRESHOLD) {
            return false;
        }

        return candidate.getInterval() > bestInterval.getInterval();
    }

    /**
     * Returns the dBSPL for a buffer.
     */
    private double soundPressureLevel(final float[] buffer) {
        double power = 0.0D;
        for (float element : buffer) {
            power += element * element;
        }
        double value = Math.pow(power, 0.5) / buffer.length;
        return 20.0 * Math.log10(value);
    }
}
