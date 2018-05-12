package com.gtavali.gameoflife.controllers;

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
     * Calculate the next step.
     * @param cells
     * @return
     */
    @RequestMapping(value = "/next-step", method = RequestMethod.POST)
    public ResponseEntity<List> computeNextStep(@RequestBody List<Cell> cells) {
        return new ResponseEntity<>(service.computeNextStep(cells), HttpStatus.OK);
    }

}
