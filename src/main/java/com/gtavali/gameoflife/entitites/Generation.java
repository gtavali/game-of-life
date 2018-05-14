package com.gtavali.gameoflife.entitites;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * It represents a Generation from a .lif file or from the board by the user.
 *
 * @author Gabor Tavali
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Generation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long generationId;

    @Column(unique = true)
    private String name;

    @OneToMany(targetEntity = Cell.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "generation_id")
    private List<Cell> cells;

    private int rowSize;

    private int columnSize;
}
