package com.gtavali.gameoflife.entitites;

import lombok.*;

import javax.persistence.*;

/**
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
