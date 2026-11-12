package com.bnp.kata.rover.orchestrator;

import com.bnp.kata.rover.builder.RoverMissionBuilder;
import com.bnp.kata.rover.factory.RoverMissionData;
import com.bnp.kata.rover.reader.InputReader;
import com.bnp.kata.rover.reporter.OutputReporter;
import com.bnp.kata.rover.service.RoverMissionService;
import com.bnp.kata.rover.validation.RoverMissionValidator;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class RoverMissionOrchestrator {

    private final InputReader reader;
    private final RoverMissionBuilder builder;
    private final OutputReporter reporter;

    public void execute(String inputFile) throws IOException {

        List<String> input = reader.read(inputFile);

        List<RoverMissionData> missions = builder.buildMissions(input);

        RoverMissionService service = builder.buildService();

        for (RoverMissionData mission : missions) {

            service.execute(mission);
        }

        reporter.report(missions);
    }
}