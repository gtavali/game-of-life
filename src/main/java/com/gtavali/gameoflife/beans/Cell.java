package com.gtavali.gameoflife.beans;

import lombok.*;

/**
 * It represents the position and the status of a cell.
 *
 * @author Gabor Tavali
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cell {

    private int row;

    private int column;

}
