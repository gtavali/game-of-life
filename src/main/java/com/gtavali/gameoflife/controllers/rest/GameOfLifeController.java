package com.gtavali.gameoflife.controllers.rest;

import com.gtavali.gameoflife.entitites.Cell;
import com.gtavali.gameoflife.services.GameOfLifeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller of the application.
 *
 * @author Gabor Tavali
 */
@RestController
@RequestMapping("/backend")
public class GameOfLifeController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private GameOfLifeService service;

    /**
     * Calculate the next step.
     * @param cells list with the cells alive.
     * @return the calculated list with the new cells.
     */
    @RequestMapping(value = "/next-generation/{boardSize}", method = RequestMethod.POST)
    public ResponseEntity<List> computeNextGeneration(@RequestBody List<Cell> cells, @PathVariable("boardSize") int boardSize) {
        log.info("Called POST /backend/next-generation/{boardSize} {}", boardSize);
        return new ResponseEntity<>(service.computeNextGeneration(cells, boardSize), HttpStatus.OK);
    }

}
