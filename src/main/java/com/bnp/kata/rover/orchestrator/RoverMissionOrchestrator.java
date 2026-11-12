package com.bnp.kata.rover.orchestrator;

import com.bnp.kata.rover.builder.MissionBuilder;
import com.bnp.kata.rover.factory.RoverMissionData;
import com.bnp.kata.rover.model.RoverCoordinates;
import com.bnp.kata.rover.reporter.OutputReporter;
import com.bnp.kata.rover.service.RoverMissionStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class RoverMissionOrchestrator {

    private final MissionBuilder builder;
    private final OutputReporter reporter;

    public void execute() throws IOException {

        log.info("Starting rover mission execution");

        List<RoverMissionData> missions = builder.buildMissionData();

        RoverMissionStrategy service = builder.buildService();

        for (RoverMissionData mission : missions) {

            service.execute(mission);
        }

        List<RoverCoordinates> roverCoordinates = missions.stream().map(RoverMissionData::getRoverCoordinates).toList();

        reporter.report(roverCoordinates);

        log.info("Finished rover mission execution from file");
    }
}