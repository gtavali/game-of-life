package com.gtavali.gameoflife.services;

import com.gtavali.gameoflife.enums.Decision;
import com.gtavali.gameoflife.entitites.Cell;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * The BE service of the generation computing.
 *
 * @author Gabor Tavali
 */
@Service
public class GameOfLifeService {

    private int boardSize;

    private boolean cells [][];

    public List<Cell> computeNextGeneration(List<Cell> board, int boardSize) {
        this.boardSize = boardSize;

        convertBoardToArray(board);

        List<Cell> newCells = new ArrayList<>();

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                int numberOfNeighbours = 0;
                Decision decision;

                if (i-1 >= 0) {
                    if (j-1 >= 0) if(cells[i-1][j-1]) numberOfNeighbours++;
                    if(cells[i-1][j]) numberOfNeighbours++;
                    if (j+1 <= boardSize - 1) if(cells[i-1][j+1]) numberOfNeighbours++;
                }

                if (j-1 >= 0) {
                    if(cells[i][j-1]) numberOfNeighbours++;

                }

                if (j+1 <= boardSize - 1) {
                    if(cells[i][j+1]) numberOfNeighbours++;
                }

                if (i+1 <= boardSize - 1) {
                    if(j-1 >= 0) if(cells[i+1][j-1]) numberOfNeighbours++;
                    if(cells[i+1][j]) numberOfNeighbours++;
                    if(j+1 <= boardSize - 1) if(cells[i+1][j+1]) numberOfNeighbours++;
                }


                if (cells[i][j]) {
                    decision = makeDecisionWithCell(numberOfNeighbours);
                } else {
                    decision = makeDecisionWithoutCell(numberOfNeighbours);
                }

                switch (decision) {
                    case survive: newCells.add(Cell.builder().row(i).column(j).build()); break;
                    case dead: break;
                    case born: newCells.add(Cell.builder().row(i).column(j).build()); break;
                    case nothing: break;
                }
            }
        }

        return newCells;
    }

    private static Decision makeDecisionWithCell(int numberOfNeighbours) {
        if (numberOfNeighbours == 2 || numberOfNeighbours == 3) {
            return Decision.survive;
        } else {
            return Decision.dead;
        }
    }

    private static Decision makeDecisionWithoutCell(int numberOfNeighbours) {
        if (numberOfNeighbours == 3) {
            return Decision.born;
        } else {
            return Decision.nothing;
        }
    }

    private void convertBoardToArray(List<Cell> board) {
        cells = new boolean[boardSize][boardSize];
        board.forEach(cell -> cells[cell.getRow()][cell.getColumn()] = true);
    }

}
