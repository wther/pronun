package com.webther.pronun.voice.processor;

/**
 * Speech interval entity
 */
public class SpeechInterval {

    /**
     * Start of the interval
     */
    private double speechStart = 0;
    
    /**
     * End of interval
     */
    private double speechEnd = 0;

    /**
     * @param speechStart Start of interval
     * @param speechEnd End of interval
     */
    public SpeechInterval(double speechStart, double speechEnd) {
        this.speechStart = speechStart;
        this.speechEnd = speechEnd;
    }

    public double getSpeechStart() {
        return speechStart;
    }

    public double getSpeechEnd() {
        return speechEnd;
    }

    public double getInterval(){
        return speechEnd - speechStart;
    }

    @Override
    public String toString() {
        return "SpeechInterval [speechStart=" + speechStart + ", speechEnd=" + speechEnd + "]";
    }
}
