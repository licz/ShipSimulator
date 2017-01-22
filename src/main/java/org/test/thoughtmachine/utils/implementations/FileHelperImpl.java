package org.test.thoughtmachine.utils.implementations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.test.thoughtmachine.utils.interfaces.FileHelper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by leszek on 21/01/17.
 */
public class FileHelperImpl implements FileHelper {

    private static final Logger logger = LoggerFactory.getLogger(FileHelperImpl.class);

    @Override
    public List<String> readFromFile(String name) {
        logger.info("reading from file: " + name);
        List<String> list = null;
        try {
            list = Files.readAllLines(new ClassPathResource(name).getFile().toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public void writeToFile(List<String> lines, String name) {
        logger.info("writing to file: " + name);
        try {
            Files.write(Paths.get(name), lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
