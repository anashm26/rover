package com.bnp.kata.rover.integration;

import com.bnp.kata.rover.commands.registry.CommandRegistry;
import com.bnp.kata.rover.exceptions.RoverException;
import com.bnp.kata.rover.factory.RoverMissionData;
import com.bnp.kata.rover.factory.RoverMissionFactory;
import com.bnp.kata.rover.model.Position;
import com.bnp.kata.rover.model.Rover;
import com.bnp.kata.rover.service.RoverMissionService;
import com.bnp.kata.rover.utils.parser.RoverMissionParser;
import com.bnp.kata.rover.validation.RoverMissionValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RoverMissionDataIntegrationTest {

    private RoverMissionParser roverMissionParser;
    private RoverMissionService roverMissionService;

    @BeforeEach
    void setUp(){

        RoverMissionValidator roverMissionValidator = new RoverMissionValidator();
        RoverMissionFactory roverMissionFactory = new RoverMissionFactory(roverMissionValidator);
        CommandRegistry commandRegistry = new CommandRegistry(roverMissionValidator);
        roverMissionParser = new RoverMissionParser(roverMissionFactory);
        roverMissionService = new RoverMissionService(commandRegistry);
    }

    @ParameterizedTest(name = "{index} - {0}")
    @MethodSource("com.bnp.kata.rover.integration.provider.RoverMissionTestDataProvider#roverMissionScenarios")
    void executeRoverMissionScenario(String testCaseName,
                                     String inputFile,
                                     String outputFile) throws IOException {

        Path path = Path.of("src", "test", "resources", "input", inputFile);

        List<RoverMissionData> missions = readRoverMissions(path);
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

    private List<RoverMissionData> readRoverMissions(Path path) throws IOException {

        List<String> input = Files.readAllLines(path);
        List<RoverMissionData> roverMissionData = roverMissionParser.parse(input);

        roverMissionData.forEach(roverMissionService::execute);

        return roverMissionData;
    }

    private void assertOutput(List<RoverMissionData> missions, String outputFile) throws IOException {

        List<String> actual = missions.stream()
                                        .map(roverMission -> {

                                            Rover rover = roverMission.getRover();
                                            Position position = rover.position();
                                            char direction = rover.direction().getCode();

                                            return position.x() + " " + position.y() + " " + direction;
                                        }).toList();

        List<String> expected = Files.readAllLines(Path.of("src", "test",  "resources",  "expected", outputFile));

        assertEquals(expected, actual);
    }
}
