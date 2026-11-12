package com.bnp.kata.rover.reporter;

import com.bnp.kata.rover.factory.RoverMissionData;
import com.bnp.kata.rover.model.Rover;

import java.util.List;

public class ConsoleOutputReporter implements OutputReporter {

    @Override
    public void report(List<RoverMissionData> missions) {

        missions.stream()
                .map(RoverMissionData::getRover)
                .forEach(ConsoleOutputReporter::printConsoleOutput);
    }

    private static void printConsoleOutput(Rover rover) {

        System.out.printf(
                "%d %d %s%n",
                rover.position().x(),
                rover.position().y(),
                rover.direction().getCode()
        );
    }
}
