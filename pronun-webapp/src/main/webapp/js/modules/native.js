/*
 * How I Sound ? 
 * https://github.com/wther/pronun
 * Copyright 2014, bszirmay@gmail.com
 * Free to use under the MIT license.
 * http://www.opensource.org/licenses/mit-license.php
 */

define([ 'jquery' ], function($) {

	var puzzleCache = {};

	/**
	 * Change puzzle
	 */
	var changePuzzle = function(puzzleIndex) {
		var data = puzzleCache[puzzleIndex];
	    	
    	$('#puzzleText').html(data.puzzleText);
    	$('#puzzleMeta').html(data.phoneticText);

        var audio = document.getElementById('nativeAudio');
        audio.src = data.samplePath;
	}
	
	/**
	 * Calcutes word's color
	 */
	var getWordColor = function(word){
		var hash = 1097152;
		for(var i = 0; i < word.length; i++){
			hash = (hash * 4317 + word.charCodeAt(i) * 413) % 2097152; 
		}
		
		var min = 60;
		var values = {
			red: min + hash % 128, green: min + (hash >> 7) % 128, blue: min + (hash >> 14) % 128
		};
		
		
		return "rgb(" + values.red + "," + values.green + "," + values.blue + ")";
	}

	/**
	 * Get HTML of puzzle button from jsonRow
	 */
	var getPuzzleHtml = function(jsonRow, puzzleIndex) {
		return $('<button/>', {
			id: "button_" + puzzleIndex,
			text : jsonRow.puzzleText,
			click : function() {
				changePuzzle(puzzleIndex);
				$(this).hide();
			}
		}).css('background-color',getWordColor(jsonRow.puzzleText));
	};

	var fetchPuzzles = function() {

		$.get("/api/puzzles/", function(data) {
			puzzleCache = data;
			
			// Shuffle
			for(var i = 0; i < puzzleCache.length * 1.5; i++){
				var a = parseInt(Math.random() * puzzleCache.length);
				var b = parseInt(Math.random() * puzzleCache.length);
				var temp = puzzleCache[a];
				puzzleCache[a] = puzzleCache[b];
				puzzleCache[b] = temp;
			}
			
			for(rowIndex in puzzleCache){
				$('#puzzleContainer').append(getPuzzleHtml(puzzleCache[rowIndex], rowIndex));
			}
		});
	};
	
	return {
		fetchPuzzles: fetchPuzzles
	};
});