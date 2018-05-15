package com.gtavali.gameoflife.controllers.rest;

import com.gtavali.gameoflife.entitites.Generation;
import com.gtavali.gameoflife.services.GenerationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * REST Controller of the generations.
 *
 * @author Gabor Tavali
 */
@RestController
@RequestMapping("/backend/generation")
public class GenerationController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private GenerationService generationService;

    /**
     * Get the names of the saved Generations.
     * @return the list of the names.
     */
    @RequestMapping(value = "/names", method = RequestMethod.GET)
    public ResponseEntity<List> getGenerationNames() {
        log.info("Called GET /backend/generation/names.");
        return new ResponseEntity<>(generationService.getGenerationNames(), HttpStatus.OK);
    }

    /**
     * Find a {@link Generation} by name.
     * @param name
     * @return the found {@link Generation}.
     */
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public ResponseEntity<Generation> findGenerationByName(@PathVariable("name") String name) {
        log.info("Called GET /backend/generation/{names}. {}", name);
        return new ResponseEntity<>(generationService.findGenerationByName(name), HttpStatus.OK);
    }

    /**
     * Save a custom {@link Generation}.
     * @param generation
     * @return the id of the saved {@link Generation}.
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Long> saveGeneration(@RequestBody Generation generation) {
        log.info("Called POST /backend/generation/. {}", generation.toString());
        return new ResponseEntity<>(generationService.saveGeneration(generation), HttpStatus.OK);
    }

}
