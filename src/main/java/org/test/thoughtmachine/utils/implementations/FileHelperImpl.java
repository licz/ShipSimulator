package org.test.thoughtmachine.utils.implementations;

import org.springframework.core.io.ClassPathResource;
import org.test.thoughtmachine.utils.interfaces.FileHelper;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * Created by leszek on 21/01/17.
 */
public class FileHelperImpl implements FileHelper {

    @Override
    public List<String> readFromFile(String name) {
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
        try {
            Files.write(new ClassPathResource(name).getFile().toPath(), lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
