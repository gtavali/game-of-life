package com.gtavali.gameoflife.repositories;

import com.gtavali.gameoflife.entitites.Generation;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository for persist a {@link Generation} entity.
 *
 * @author Gabor Tavali
 */
public interface GenerationRepository extends CrudRepository<Generation, Long> {
}
