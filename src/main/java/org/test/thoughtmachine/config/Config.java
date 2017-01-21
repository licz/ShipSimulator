package org.test.thoughtmachine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.test.thoughtmachine.utils.implementations.GameImpl;
import org.test.thoughtmachine.utils.implementations.FileHelperImpl;
import org.test.thoughtmachine.utils.implementations.ParserImpl;
import org.test.thoughtmachine.utils.interfaces.Game;
import org.test.thoughtmachine.utils.interfaces.FileHelper;
import org.test.thoughtmachine.utils.interfaces.Parser;

/**
 * Created by leszek on 21/01/17.
 */
@Configuration
public class Config {

    @Bean
    public Game getBoard() {
        return new GameImpl();
    }

    @Bean
    public Parser getParser() {
        return new ParserImpl();
    }

    @Bean
    public FileHelper getFileHelper() {
        return new FileHelperImpl();
    }
}
