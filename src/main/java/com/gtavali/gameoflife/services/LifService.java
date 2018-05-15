package com.gtavali.gameoflife.services;

import com.gtavali.gameoflife.entitites.Cell;
import com.gtavali.gameoflife.entitites.Generation;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The BE service to parse .lif files. It is compatible with Life 1.05 files.
 *
 * @author Gabor Tavali
 */
@Service
public class LifService {

    /**
     * Start coordinate to position the generation on the board.
     */
    private final static int START_COORDINATE = 3;

    public Generation parseLif(InputStream path) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(path));
        Generation generation = new Generation();
        List<Cell> cells = new ArrayList<>();
        int rowNumber = START_COORDINATE;
        int columnNumber = START_COORDINATE;
        List<Integer> columnSizesByLines = new ArrayList<>();
        try {
            int row = START_COORDINATE;
            int column = START_COORDINATE;
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("#D") && null == generation.getName()) {
                    generation.setName(line.substring(3));
                }
                if (line.startsWith("#P")) {
                    row++;
                    rowNumber++;
                }
                if (line.startsWith(".") || line.startsWith("*")) {

                    for (char c : line.toCharArray()) {
                        if ("*".equals(String.valueOf(c))) {
                            cells.add(Cell.builder().row(row).column(column).build());
                        }
                        column++;
                        columnNumber++;
                    }
                    row++;
                    rowNumber++;
                }
                columnSizesByLines.add(columnNumber);
                columnNumber = START_COORDINATE;
                column = START_COORDINATE;
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        generation.setCells(cells);
        generation.setRowSize(rowNumber);
        generation.setColumnSize(Collections.max(columnSizesByLines));
        return generation;
    }

}
