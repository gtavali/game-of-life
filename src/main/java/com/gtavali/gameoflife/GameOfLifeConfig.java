package com.gtavali.gameoflife;

import com.gtavali.gameoflife.services.GameOfLifeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Configuration file of the application.
 *
 * @author Gabor Tavali
 */
@Configuration
public class GameOfLifeConfig {

    @Value("${game.boardSize:3}")
    private int boardSize;

    @Bean
    public GameOfLifeService gameOfLifeService() {
        return new GameOfLifeService(this.boardSize);
    }

}
