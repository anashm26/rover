package com.bnp.kata.rover;

import com.bnp.kata.rover.builder.MissionBuilder;
import com.bnp.kata.rover.builder.RoverMissionBuilder;
import com.bnp.kata.rover.formatter.ConsoleOutputFormatter;
import com.bnp.kata.rover.orchestrator.RoverMissionOrchestrator;
import com.bnp.kata.rover.reader.FileInstructionsInputReader;
import com.bnp.kata.rover.reader.InstructionsInputReader;
import com.bnp.kata.rover.reporter.ConsoleOutputReporter;
import com.bnp.kata.rover.reporter.OutputReporter;
import com.bnp.kata.rover.validation.RoverMissionValidator;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        validateArguments(args);

        InstructionsInputReader reader = new FileInstructionsInputReader(args[0]);
        MissionBuilder builder = new RoverMissionBuilder(reader, new RoverMissionValidator());
        OutputReporter reporter = new ConsoleOutputReporter(new ConsoleOutputFormatter());

        RoverMissionOrchestrator orchestrator =
                new RoverMissionOrchestrator( builder, reporter);

        orchestrator.execute();
    }

    private static void validateArguments(String[] args) {
        if (args.length != 1
                || args[0].isBlank()) {

            throw new IllegalArgumentException("Invalid command line arguments");
        }
    }

}
