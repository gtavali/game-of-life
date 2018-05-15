package com.gtavali.gameoflife.services;

import com.gtavali.gameoflife.entitites.Generation;
import com.gtavali.gameoflife.repositories.GenerationRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

/**
 * Test for {@link GenerationService}
 *
 * @author Gabor Tavali
 */
@RunWith(MockitoJUnitRunner.class)
public class GenerationServiceTest {

    @Mock
    private GenerationRepository generationRepository;

    private GenerationService generationService;

    @Before
    public void setUp() {
        generationService = new GenerationService(generationRepository);
    }

    @Test
    public void getGenerationNamesTest() {
        List<String> names = Arrays.asList("name1", "name2");

        Mockito.when(generationRepository.getNames()).thenReturn(names);

        List<String> outputNames = generationService.getGenerationNames();

        Assert.assertEquals(names.size(), outputNames.size());
        Assert.assertEquals(names.get(0), outputNames.get(0));
        Assert.assertEquals(names.get(1), outputNames.get(1));

        Mockito.verify(generationRepository).getNames();
    }

    @Test
    public void findGenerationByNameTest() {
        Generation outputGeneration = Generation.builder().generationId(1L).name("gen1").build();

        Mockito.when(generationRepository.findByName("gen1")).thenReturn(outputGeneration);

        Generation generation = generationService.findGenerationByName("gen1");

        Assert.assertEquals("gen1", generation.getName());

        Mockito.verify(generationRepository).findByName("gen1");

    }

    @Test
    public void saveGenerationTest() {
        Generation inputGeneration = Generation.builder().name("gen1").build();
        Generation outputGeneration = Generation.builder().generationId(1L).name("gen1").build();

        Mockito.when(generationRepository.save(inputGeneration)).thenReturn(outputGeneration);

        Long id = generationService.saveGeneration(inputGeneration);

        Assert.assertEquals(1L, id.longValue());

        Mockito.verify(generationRepository).save(inputGeneration);
    }

}
