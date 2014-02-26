package com.webther.pronun.webapp.exception;

import com.webther.pronun.webapp.controller.WAVUploadController;

/**
 * Checked exception thrown from the {@link WAVUploadController} when the upload
 * format is incorrect.
 */
public class InvalidUploadFormatException extends Exception {

    /**
     * Generated serial version UID
     */
    private static final long serialVersionUID = 4538772303497097786L;

    /**
     * Initializes an instance of the {@link InvalidUploadFormatException}
     * exception.
     * 
     * @param message
     *            Message to be shown.
     */
    public InvalidUploadFormatException(String message) {
        super(message);
    }

    /**
     * Initializes an instance of the {@link InvalidUploadFormatException}
     * exception.
     * 
     * @param message
     *            Message to be shown.
     * @param cause
     *            Inner exception
     */
    public InvalidUploadFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
