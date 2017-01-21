package org.test.thoughtmachine.utils.implementations;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by leszek on 21/01/17.
 */
public class FileHelperImplTest {
    FileHelperImpl fileHelper = new FileHelperImpl();

    @Test
    public void shouldReadFromFile() {
        Assert.assertEquals("testINPUT", fileHelper.readFromFile("input.txt").get(0));
    }

    @Test
    public void shouldWriteToFile() {
        fileHelper.writeToFile(new ArrayList<>(Arrays.asList("asd")), "output.txt");
    }
}