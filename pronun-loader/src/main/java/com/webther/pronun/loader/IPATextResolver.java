package com.webther.pronun.loader;

import java.io.IOException;

import org.apache.commons.lang.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.webther.pronun.loader.exception.UnresolveableIPAException;

/**
 * Class resolving word to phonemic text
 * 
 * @author Barnabas
 */
public class IPATextResolver {

    /**
     * Logger used
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(IPATextResolver.class);

    /**
     * URI prefix to access IPA details for words
     */
    public static final String URI_PREFIX = "http://dictionary.cambridge.org/dictionary/british/";

    /**
     * Private constructor for utility class
     */
    private IPATextResolver() {
        
    }
    
    /**
     * Get phonetic written representation of word
     * 
     * @param word
     * @return
     * @throws IOException
     * @throws UnresolveableIPAException 
     */
    public static String getIPAForWord(String word) throws IOException, UnresolveableIPAException {

        final String url = URI_PREFIX + word + "_1";
        LOGGER.info("Fetching IPA for {} from: {}", word, url);

        Document document = Jsoup.connect(URI_PREFIX + word).get();

        // #entryContent > div.di > div.di-head > span > span.pron > span
        // #entryContent > div.di > div.di-head > span > span.pron > span
        Element entryContent = document.getElementById("entryContent");

        if (entryContent == null) {
            throw new UnresolveableIPAException("No #entryContent found in fetched HTML");
        }

        Elements ipa = entryContent.getElementsByClass("ipa");
        if (ipa.size() > 0) {
            return StringEscapeUtils.escapeHtml(ipa.get(0).ownText());
        } else {
            throw new UnresolveableIPAException("No IPA found in: " + entryContent.toString());
        }
    }

}
