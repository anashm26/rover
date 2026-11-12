package com.bnp.kata.rover.integration.provider;

import com.bnp.kata.rover.exceptions.InvalidInstructionsFormatException;
import com.bnp.kata.rover.exceptions.RoverOutOfMarsPlateauException;
import lombok.experimental.UtilityClass;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

@UtilityClass
public class RoverMissionTestDataProvider {

    public static Stream<Arguments> roverMissionScenarios(){

        return Stream.of(
                Arguments.of("Official Test Example",
                        "official-example.txt",
                        "official-example-output.txt"),
                Arguments.of("Single Rover",
                        "single-rover.txt",
                        "single-rover-output.txt"),
                Arguments.of("Multiple Rovers",
                        "multiple-rovers.txt",
                        "multiple-rovers-output.txt")
        );

    }

    public static Stream<Arguments> roverMissionExceptionScenarios(){

        return Stream.of(
                Arguments.of("Empty Input File",
                        "empty-input.txt",
                        InvalidInstructionsFormatException.class,
                        "Invalid input format number of instruction lines not valid."),
                Arguments.of("Invalid Command",
                        "invalid-command.txt",
                        InvalidInstructionsFormatException.class,
                        "Invalid input format unknown code [X] for enum Command. Allowed values [L, R, M]"),
                Arguments.of("Invalid Direction",
                        "invalid-direction.txt",
                        InvalidInstructionsFormatException.class,
                        "Invalid input format unknown code [D] for enum Direction. Allowed values [N, S, E, W]"),
                Arguments.of("Rover Outside Plateau",
                        "rover-outside-plateau.txt",
                        RoverOutOfMarsPlateauException.class,
                        "Initial position (2,9) outside mars plateau (7,8).")
        );
    }
}
