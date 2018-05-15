package com.gtavali.gameoflife;

import com.gtavali.gameoflife.entitites.Generation;
import com.gtavali.gameoflife.repositories.GenerationRepository;
import com.gtavali.gameoflife.services.LifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

/**
 * @author Gabor Tavali
 */
@Component
public class GameOfLifeDataLoader implements CommandLineRunner {

    @Autowired
    private GenerationRepository generationRepository;

    @Autowired
    private LifService lifService;

    @Override
    public void run(String... args) throws Exception {

        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(getClass().getClassLoader());
        Resource[] resources = resolver.getResources("classpath*:/patterns/*.lif") ;

        for (Resource resource : resources) {
            Generation generation = lifService.parseLif(resource.getInputStream());
            generationRepository.save(generation);
        }
    }
}
