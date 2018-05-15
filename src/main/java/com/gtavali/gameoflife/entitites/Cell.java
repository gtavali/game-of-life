package com.gtavali.gameoflife.entitites;

import lombok.*;

import javax.persistence.*;

/**
 * It represents a Cell entity.
 *
 * @author Gabor Tavali
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cell {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cellId;

    private int row;

    private int column;

}
