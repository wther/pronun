package com.webther.pronun.loader.entity;

/**
 * Entity stored in the CSV configuration files
 */
public class PuzzleEntity {

	/**
	 * Word
	 */
	private String word;
	
	/**
	 * Phonetic representation
	 */
	private String ipa;

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getIpa() {
		return ipa;
	}

	public void setIpa(String ipa) {
		this.ipa = ipa;
	}
}
