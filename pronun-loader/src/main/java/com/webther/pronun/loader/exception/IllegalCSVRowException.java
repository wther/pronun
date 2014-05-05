package com.webther.pronun.loader.exception;

import com.webther.pronun.loader.CsvFileLoader;

/**
 * Exception thrown from {@link CsvFileLoader} when encountering an 
 * illegal line in the file
 * 
 * @author Barnabas
 */

public class IllegalCSVRowException extends Exception {

    /**
	 * Initialize {@link Throwable}
	 * @param reason Why is it illegal?
	 * @param line Which line is illegal?
	 */
	public IllegalCSVRowException(String reason, int line){
		super(reason + " Line:" + line);
	}
	
	   /**
     * Initialize {@link Throwable}
     * @param reason Why is it illegal?
     * @param line Which line is illegal?
     */
    public IllegalCSVRowException(Throwable reason){
        super(reason);
    }
}
