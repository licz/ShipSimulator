package org.test.thoughtmachine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.test.thoughtmachine.utils.interfaces.Game;
import org.test.thoughtmachine.utils.interfaces.FileHelper;
import org.test.thoughtmachine.utils.interfaces.Parser;

import java.util.List;

/**
 * Created by leszek on 21/01/17.
 */
@Component
public class Simulator implements ApplicationRunner {

    private static final String INPUT_FILE = "input.txt";
    private static final String OUTPUT_FILE = "output.txt";

    private static final int SIZE_LINE = 0;
    private static final int COORDINATE_LINE = 1;
    private static final int FIRST_OPERATION_LINE = 2;

    @Autowired
    private Game game;

    @Autowired
    private FileHelper fileHelper;

    @Autowired
    private Parser parser;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        List<String> inputLines = fileHelper.readFromFile(INPUT_FILE);

        game.initBoard(parser.parseSizeLine(inputLines.get(SIZE_LINE)));
        game.placeShips(parser.parseCoordinatesLine(inputLines.get(COORDINATE_LINE)));

        for (int iterator = FIRST_OPERATION_LINE; iterator < inputLines.size(); iterator++) {
            game.performOperation(parser.parseOperationLine(inputLines.get(iterator)));
        }

        fileHelper.writeToFile(parser.generateOutput(game.getShips()), OUTPUT_FILE);
    }
}
