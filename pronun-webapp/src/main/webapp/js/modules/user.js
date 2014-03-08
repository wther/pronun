/*
 * How I Sound ? 
 * https://github.com/wther/pronun
 * Copyright 2014, bszirmay@gmail.com
 * Free to use under the MIT license.
 * http://www.opensource.org/licenses/mit-license.php
 */
define([ 'modules/recorder' ], function(Recorder) {
    
    /**
     * Private property initialized from Recorder
     */
    var recorder = undefined;
    
    /**
     * Publicly modifiable callback function called once replaying sample is enabled
     * @param
     */
    var onReplayReady = function() {
        
    };

    /**
     * Milliseconds for how long to record user voice
     */
    var RECORD_TIME = 4000;
    
    /**
     * Public configuration parameter
     */
    var config = {
            audioContollerParam: 'audio'
    };
    
    /**
     * Initialize recorder, get userMedia from browser
     * @param callback Called when recorder initialization was successful
     */    
    var initRecorder = function(callback) {
        if (recorder !== undefined) {
            return;
        }

        window.URL = window.URL || window.webkitURL;
        navigator.getUserMedia = navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia
                || navigator.msGetUserMedia;
        if (navigator.getUserMedia) {
            navigator.getUserMedia({
                audio : true
            },
            // onSuccess
            function(s) {
                var context = new webkitAudioContext();
                var mediaStreamSource = context.createMediaStreamSource(s);
                recorder = new Recorder(mediaStreamSource, {
                    workerPath : "js/workers/recorderWorker.js"
                });
                callback();
            },
            // onFail
            function(e) {
                throw new Error('navigator.getUserMedia rejected', e);
            });
        } else {
            throw new Error('navigator.getUserMedia not present');
        }
    };
    
    /**
     * Start recording
     * @param callback Called when recordTime is elapsed
     */
    var startRecording = function() {
                
        if (recorder === undefined) {
            initRecorder(startRecording);
        } else {
            recorder.clear();
            recorder.record();
        }
    };

    /**
     * Stops recording and exports WAV content
     * @param callback Callback called with WAV blob
     */
    var stopRecording = function(callback) {
        recorder.stop();
        if(callback !== undefined){
            recorder.exportWAV(callback);
        }
    };
    
    /**
     * Start recording, and when finished export content to the server 
     */
    var record = function(){
        startRecording();
        setTimeout(recordingFinished, RECORD_TIME);
    };
    
    /**
     * When recording is finished (after RECORD_TIME) this is executed
     */
    var recordingFinished = function(){
        stopRecording(uploadContent);
    };
    
    var restUri = '/api/voice';
       
    /**
     * When exporting WAV content from the worker is finished this is executed
     */
    var uploadContent = function(soundBlob){
        var formData = new FormData();
        formData.append('fname', 'voice.wav');
        formData.append('data', soundBlob);

        // Uploading blob to the java server
        $.ajax({
            type : 'POST',
            url : '/api/voice',
            data : formData,
            processData : false,
            contentType : false,
            headers : {
                "X-Voice-Session" : "test-session",
                "X-Puzzle-Id" : "test-puzzle"
            }
        }).done(function(data){
            setupAudioController(data);
        });
    };
    

    /**
     * When server returns with meta, execute this
     */
    var setupAudioController = function(data) {
        var duration = recorder.duration();
        var range = {
            from: data.speechStart / duration,
            to: data.speechEnd / duration
        };
        
        // This time we only fetch to correct interval        
        recorder.exportWAV(function(soundBlob) {
            var audio = document.getElementById(config.audioContollerParam);
            audio.src = window.URL.createObjectURL(soundBlob);
            audio.oncanplay = function() {
                play();
            };
        }, undefined, range);

    };
    
    /**
     * Public function to replay audio
     */
    var play = function(){
        var audio = document.getElementById(config.audioContollerParam);
        audio.play();
        audio.currentTime = 0;
        onReplayReady();
    };    
    
    return {
        config: config,
        restUri: restUri,
        record: record,
        play: play,
        onReplayReady: onReplayReady
    };
});