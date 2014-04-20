package com.webther.pronun.loader.exception;

import com.webther.pronun.loader.IPATextResolver;

/**
 * Exception thrown from {@link IPATextResolver} when it fails
 * to resolve the IPA for a word
 * @author Barnabas
 *
 */
public class UnresolveableIPAException extends Exception {

	public UnresolveableIPAException(String message){
		super(message);
	}
	
}
