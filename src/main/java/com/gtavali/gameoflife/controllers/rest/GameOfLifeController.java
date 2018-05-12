package com.gtavali.gameoflife.controllers.rest;

import com.gtavali.gameoflife.beans.Cell;
import com.gtavali.gameoflife.services.GameOfLifeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
     * Get the size of the board.
     * @return the size as an integer.
     */
    @RequestMapping(value = "/board-size", method = RequestMethod.GET)
    public ResponseEntity<Integer> getBoardSize() {
        return new ResponseEntity<>(service.getBoardSize(), HttpStatus.OK);
    }

    /**
     * Calculate the next step.
     * @param cells list with the cells alive.
     * @return the calculated list with the new cells.
     */
    @RequestMapping(value = "/next-generation", method = RequestMethod.POST)
    public ResponseEntity<List> computeNextGeneration(@RequestBody List<Cell> cells) {
        return new ResponseEntity<>(service.computeNextGeneration(cells), HttpStatus.OK);
    }

}
