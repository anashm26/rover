package com.bnp.kata.rover.orchestrator;

import com.bnp.kata.rover.builder.RoverMissionBuilder;
import com.bnp.kata.rover.factory.RoverMissionData;
import com.bnp.kata.rover.reader.InputReader;
import com.bnp.kata.rover.reporter.OutputReporter;
import com.bnp.kata.rover.service.RoverMissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class RoverMissionOrchestrator {

    private final InputReader reader;
    private final RoverMissionBuilder builder;
    private final OutputReporter reporter;

    public void execute(String inputFile) throws IOException {

        log.info("Starting rover mission execution from file [{}]", inputFile);

        List<String> input = reader.read(inputFile);

        log.info("Read {} input lines", input.size());

        List<RoverMissionData> missions = builder.buildMissions(input);

        RoverMissionService service = builder.buildService();

        for (RoverMissionData mission : missions) {

            service.execute(mission);
        }

        reporter.report(missions);

        log.info("Finished rover mission execution from file [{}]", inputFile);
    }
}