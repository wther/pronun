package com.webther.pronun.loader;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.webther.pronun.loader.exception.UnresolveableIPAException;

/**
 * Testing the {@link IPATextResolver} class.
 * 
 * @author Barnabas
 */
public class IPATextResolverTest {
	
	/**
	 * Test case:
	 *  Fetching the word miscellaneous
	 *  
	 * Expected:
	 *  IPA value for the word is returned
	 *  
	 *  Ignore because depends on external resoruce
	 * 
	 * @throws IOException
	 * @throws UnresolveableIPAException 
	 */
	@Test
	public void thatItFetchesContent() throws IOException, UnresolveableIPAException{
			
		// Act
		final String ipa = IPATextResolver.getIPAForWord("miscellaneous");
		
		// Assert mɪs·əˈleɪ·ni·əs
		assertEquals("&#716;m&#618;s.l&#712;e&#618;.ni.&#601;s", ipa);
	}
}
