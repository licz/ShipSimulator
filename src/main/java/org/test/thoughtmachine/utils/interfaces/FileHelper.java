package org.test.thoughtmachine.utils.interfaces;

import java.util.List;

/**
 * Created by leszek on 21/01/17.
 */
public interface FileHelper {
    List<String> readFromFile(String name);
    void writeToFile(List<String> lines, String name);
}
