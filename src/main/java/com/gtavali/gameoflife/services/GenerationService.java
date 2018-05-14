package com.gtavali.gameoflife.services;

import com.gtavali.gameoflife.entitites.Generation;
import com.gtavali.gameoflife.repositories.GenerationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The BE service of a generation.
 *
 * @author Gabor Tavali
 */
@Service
public class GenerationService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private GenerationRepository repository;

    public List<String> getGenerationNames() {
        return repository.getNames();
    }

    public Generation findGenerationByName(String name) {
        return repository.findByName(name);
    }

    public Long saveGeneration(Generation generation) {
        return repository.save(generation).getGenerationId();
    }

}
