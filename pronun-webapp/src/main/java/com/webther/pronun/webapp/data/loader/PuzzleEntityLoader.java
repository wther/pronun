package com.webther.pronun.webapp.data.loader;

import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import au.com.bytecode.opencsv.CSVReader;

import com.webther.pronun.data.entity.PuzzleEntity;
import com.webther.pronun.data.service.PuzzleService;

/**
 * Class responsible for loading puzzles
 * 
 * @author Barnabas
 */
@Component
public class PuzzleEntityLoader implements InitializingBean, ResourceLoaderAware {

    /**
     * Resource loader to read files
     */
    private ResourceLoader resourceLoader;

    /**
     * Service used to save puzzles
     */
    @Autowired
    private PuzzleService puzzleService;

    /**
     * Method triggered after {@link #puzzleService} has be loaded
     * @throws IOException 
     */
    public void afterPropertiesSet() throws IOException {
        Resource entityListResource = resourceLoader.getResource("classpath:/entityList.csv");

        CSVReader reader = null;
        try {
            reader = new CSVReader(new InputStreamReader(entityListResource.getInputStream()));

            String[] nextLine;
            int line = 0;
            while ((nextLine = reader.readNext()) != null) {

                if (nextLine.length < 2) {
                    throw new IllegalStateException("Should be at least two word per line!");
                }

                // First line is the header
                if (line > 0) {
                    PuzzleEntity entity = new PuzzleEntity();
                    entity.setPuzzleCode(nextLine[0]);
                    entity.setPuzzleText(nextLine[0]);
                    entity.setPhoneticText(nextLine[1]);
                    entity.setSamplePath("/samples/" + nextLine[0] + ".mp3");

                    puzzleService.save(entity);
                }

                line++;
            }

        } finally {
            if (reader != null) {
                reader.close();
            }

            puzzleService.flush();
        }
    }

    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public void setPuzzleService(PuzzleService puzzleService) {
        this.puzzleService = puzzleService;
    }
}
