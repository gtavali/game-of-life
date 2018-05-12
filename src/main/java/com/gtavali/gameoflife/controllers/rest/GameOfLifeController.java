package com.gtavali.gameoflife.controllers.rest;

import com.gtavali.gameoflife.beans.Cell;
import com.gtavali.gameoflife.services.GameOfLifeService;
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

    @Autowired
    private GameOfLifeService service;

    /**
     * Calculate the next step.
     * @param cells list with the cells alive.
     * @return the calculated list with the new cells.
     */
    @RequestMapping(value = "/next-generation/{boardSize}", method = RequestMethod.POST)
    public ResponseEntity<List> computeNextGeneration(@RequestBody List<Cell> cells, @PathVariable("boardSize") int boardSize) {
        return new ResponseEntity<>(service.computeNextGeneration(cells, boardSize), HttpStatus.OK);
    }

}
