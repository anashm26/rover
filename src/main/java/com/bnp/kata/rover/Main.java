package com.bnp.kata.rover;

import com.bnp.kata.rover.builder.RoverMissionBuilder;
import com.bnp.kata.rover.formatter.ConsoleOutputFormatter;
import com.bnp.kata.rover.orchestrator.RoverMissionOrchestrator;
import com.bnp.kata.rover.reader.FileInputReader;
import com.bnp.kata.rover.reader.InputReader;
import com.bnp.kata.rover.reporter.ConsoleOutputReporter;
import com.bnp.kata.rover.reporter.OutputReporter;
import com.bnp.kata.rover.validation.RoverMissionValidator;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        validateArguments(args);

        InputReader reader = new FileInputReader();
        RoverMissionBuilder builder = new RoverMissionBuilder(new RoverMissionValidator());
        OutputReporter reporter = new ConsoleOutputReporter(new ConsoleOutputFormatter());

        RoverMissionOrchestrator orchestrator =
                new RoverMissionOrchestrator(reader, builder, reporter);

        orchestrator.execute(args[0]);
    }

    private static void validateArguments(String[] args) {
        if (args.length != 1
                || args[0].isBlank()) {

            throw new IllegalArgumentException("Invalid command line arguments");
        }
    }

}
