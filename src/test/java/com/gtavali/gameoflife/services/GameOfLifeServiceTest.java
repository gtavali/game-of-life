package com.gtavali.gameoflife.services;

import com.gtavali.gameoflife.beans.Cell;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Test for the {@link GameOfLifeService}
 *
 * @author Gabor Tavali
 */
public class GameOfLifeServiceTest {

    private GameOfLifeService service;

    private List<Cell> cells = new ArrayList<>();
    private List<Cell> expectedCells = new ArrayList<>();

    @Before
    public void setUp() {
        service = new GameOfLifeService();

        cells.add(Cell.builder().row(1).column(0).build());
        cells.add(Cell.builder().row(1).column(1).build());
        cells.add(Cell.builder().row(1).column(2).build());

        expectedCells.add(Cell.builder().row(0).column(1).build());
        expectedCells.add(Cell.builder().row(1).column(1).build());
        expectedCells.add(Cell.builder().row(2).column(1).build());
    }

    @Test
    public void computeNextGenerationTest() {
        List<Cell> computedCells = service.computeNextGeneration(cells, 30);

        Assert.assertEquals(expectedCells.size(), computedCells.size());

        Assert.assertEquals(0, computedCells.get(0).getRow());
        Assert.assertEquals(1, computedCells.get(0).getColumn());

        Assert.assertEquals(1, computedCells.get(1).getRow());
        Assert.assertEquals(1, computedCells.get(1).getColumn());

        Assert.assertEquals(2, computedCells.get(2).getRow());
        Assert.assertEquals(1, computedCells.get(2).getColumn());
    }

}
