package com.webther.pronun.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller to access version information
 */
@Controller
@RequestMapping(VersionMetaController.URI)
public class VersionMetaController {

    /** Logger instance */
    private static final Logger LOGGER = LoggerFactory.getLogger(VersionMetaController.class);
    
    /** Response */
    public static final String version = "1.0.0";
    
    /** URI of the controller */
    public static final String URI = "/version";
    
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<String> version(HttpServletRequest request){
        
        LOGGER.info("Version requested");
        return new ResponseEntity<String>(version, HttpStatus.OK);
    }
}
