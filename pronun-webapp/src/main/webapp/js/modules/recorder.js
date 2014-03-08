/*
 * License (MIT)
 * Copyright © 2013 Matt Diamond
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a 
 * copy of this software and associated documentation files (the "Software"), 
 * to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, 
 * and/or sell copies of the Software, and to permit persons to whom the 
 * Software is furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be 
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR 
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS 
 * BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
define([], function() {

    var WORKER_PATH = 'recorderWorker.js';

    var Recorder = function(source, cfg) {
        
        var config = cfg || {};
        var bufferLen = config.bufferLen || 4096;
        this.context = source.context;
        this.node = (this.context.createScriptProcessor || this.context.createJavaScriptNode).call(this.context,bufferLen, 2, 2);
        
        var worker = new Worker(config.workerPath || WORKER_PATH);
        worker.postMessage({
            command : 'init',
            config : {
                sampleRate : this.context.sampleRate
            }
        });
        
        var recording = false;
        var currCallback;
        var recordTime = 0.0;

        this.node.onaudioprocess = function(e) {
            if (!recording){
                return;
            }
            recordTime += e.inputBuffer.duration;
            worker.postMessage({
                command : 'record',
                buffer : [ e.inputBuffer.getChannelData(0), e.inputBuffer.getChannelData(1) ]
            });
        };

        this.configure = function(cfg) {
            for ( var prop in cfg) {
                if (cfg.hasOwnProperty(prop)) {
                    config[prop] = cfg[prop];
                }
            }
        };

        this.record = function() {
            recording = true;
            recordTime = 0.0;
        };

        this.stop = function() {
            recording = false;
        };

        this.clear = function() {
            worker.postMessage({
                command : 'clear'
            });
        };

        this.getBuffer = function(cb) {
            currCallback = cb || config.callback;
            worker.postMessage({
                command : 'getBuffer'
            });
        };
        
        this.duration = function(){
            return recordTime;
        };

        this.exportWAV = function(cb, type, range) {
            currCallback = cb || config.callback;
            type = type || config.type || 'audio/wav';
            range = range || {from: 0.0, to: 1.0};
            
            if (!currCallback)
                throw new Error('Callback not set');
            
            var command = {
                command : 'exportWAV',
                type : type,
                range: range
            };
            worker.postMessage(command);
        };
        

        worker.onmessage = function(e) {
            var blob = e.data;
            currCallback(blob);
        };

        source.connect(this.node);
        this.node.connect(this.context.destination); // this should not be
                                                        // necessary
    };

    Recorder.forceDownload = function(blob, filename) {
        var url = (window.URL || window.webkitURL).createObjectURL(blob);
        var link = window.document.createElement('a');
        link.href = url;
        link.download = filename || 'output.wav';
        var click = document.createEvent("Event");
        click.initEvent("click", true, true);
        link.dispatchEvent(click);
    };

    //window.Recorder = Recorder;
    return Recorder;
});
