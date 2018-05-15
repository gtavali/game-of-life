package com.gtavali.gameoflife.services;

import com.gtavali.gameoflife.entitites.Generation;
import org.junit.Assert;
import org.junit.Test;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Test for the {@link LifService}
 *
 * @author Gabor Tavali
 */
public class LifServiceTest {

    private LifService lifService = new LifService();

    @Test
    public void parseLifTest()  {
        Generation generation = lifService.parseLif(getClass().getClassLoader().getResourceAsStream("pi.lif"));

        Assert.assertEquals("Pi Heptomino", generation.getName());
        Assert.assertEquals(7, generation.getCells().size());
    }

}
