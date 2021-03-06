/*
 * How I Sound ? 
 * https://github.com/wther/pronun
 * Copyright 2014, bszirmay@gmail.com
 * Free to use under the MIT license.
 * http://www.opensource.org/licenses/mit-license.php
 */

require.config({
    paths : {
        "jquery" : "lib/jquery.min",
    }
});

require(['jquery', 'modules/user', 'modules/native'], function($, user, native) {
    
    var userButtonClick = function(){
        user.config.audioContollerParam = 'userAudio';
        user.record();
    };
    
    var nativeButtonClick = function(){
        var audio = document.getElementById('nativeAudio');
        audio.currentTime = 0.0;
        audio.play();
    };
    
    var userReplayButtonClick = function(){
        user.play();
    };
    
    $('#userButton').unbind(userButtonClick).click(userButtonClick);
    
    $('#userReplayButton').unbind(userReplayButtonClick).click(userReplayButtonClick);
    
    $('#nativeReplayButton').unbind(nativeButtonClick).click(nativeButtonClick);
    
    $(function(){
    	native.fetchPuzzles();
    });
});
