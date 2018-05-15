package com.gtavali.gameoflife.repositories;

import com.gtavali.gameoflife.entitites.Generation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Repository for persist a {@link Generation} entity.
 *
 * @author Gabor Tavali
 */
public interface GenerationRepository extends CrudRepository<Generation, Long> {

    @Query(value = "SELECT name FROM generation", nativeQuery = true)
    List<String> getNames();

    Generation findByName(String name);

}
