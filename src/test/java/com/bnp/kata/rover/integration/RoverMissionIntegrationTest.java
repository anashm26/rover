package com.bnp.kata.rover.integration;

import com.bnp.kata.rover.commands.registry.CommandRegistry;
import com.bnp.kata.rover.exceptions.RoverException;
import com.bnp.kata.rover.factory.RoverMission;
import com.bnp.kata.rover.factory.RoverMissionFactory;
import com.bnp.kata.rover.model.Position;
import com.bnp.kata.rover.model.Rover;
import com.bnp.kata.rover.service.RoverMissionService;
import com.bnp.kata.rover.utils.parser.RoverMissionParser;
import com.bnp.kata.rover.validation.RoverValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RoverMissionIntegrationTest {

    private RoverMissionParser roverMissionParser;
    private RoverMissionService roverMissionService;

    @BeforeEach
    void setUp(){

        RoverValidator roverValidator = new RoverValidator();
        RoverMissionFactory roverMissionFactory = new RoverMissionFactory(roverValidator);
        CommandRegistry commandRegistry = new CommandRegistry(roverValidator);
        roverMissionParser = new RoverMissionParser(roverMissionFactory);
        roverMissionService = new RoverMissionService(commandRegistry);
    }

    @ParameterizedTest(name = "{index} - {0}")
    @MethodSource("com.bnp.kata.rover.integration.provider.RoverMissionTestDataProvider#roverMissionScenarios")
    void executeRoverMissionScenario(String testCaseName,
                                     String inputFile,
                                     String outputFile) throws IOException {

        Path path = Path.of("src", "test", "resources", "input", inputFile);

        List<RoverMission> missions = readRoverMissions(path);
        assertOutput(missions,outputFile);
    }

    @ParameterizedTest(name = "{index} - {0}")
    @MethodSource("com.bnp.kata.rover.integration.provider.RoverMissionTestDataProvider#roverMissionExceptionScenarios")
    void shouldThrowExceptionForInvalidInputFile(String testCaseName,
                                     String inputFile,
                                     Class<? extends RoverException> expectedException,
                                     String expectedErrorMessage) {

        Path path = Path.of("src", "test", "resources", "input", "errors", inputFile);

        RoverException exception = assertThrows(expectedException, () -> readRoverMissions(path));

        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    private List<RoverMission> readRoverMissions(Path path) throws IOException {

        List<String> input = Files.readAllLines(path);
        List<RoverMission> roverMissions = roverMissionParser.parse(input);

        roverMissions.forEach(roverMissionService::execute);

        return roverMissions;
    }

    private void assertOutput(List<RoverMission> missions, String outputFile) throws IOException {

        List<String> actual = missions.stream()
                                        .map(roverMission -> {

                                            Rover rover = roverMission.getRover();
                                            Position position = rover.getPosition();
                                            char direction = rover.getDirection().getCode();

                                            return position.x() + " " + position.y() + " " + direction;
                                        }).toList();

        List<String> expected = Files.readAllLines(Path.of("src", "test",  "resources",  "expected", outputFile));

        assertEquals(expected, actual);
    }
}
