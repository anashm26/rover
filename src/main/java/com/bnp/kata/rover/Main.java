package com.bnp.kata.rover;

import com.bnp.kata.rover.commands.registry.CommandRegistry;
import com.bnp.kata.rover.factory.RoverMission;
import com.bnp.kata.rover.factory.RoverMissionFactory;
import com.bnp.kata.rover.model.Rover;
import com.bnp.kata.rover.service.RoverMissionService;
import com.bnp.kata.rover.utils.parser.RoverMissionParser;
import com.bnp.kata.rover.validation.RoverValidator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        if (args.length != 1
                || args[0].isBlank()) {

            throw new IllegalArgumentException("Invalid command line arguments");
        }

        List<String> lines = Files.readAllLines(Path.of(args[0]));

        RoverValidator validator = new RoverValidator();

        RoverMissionFactory factory = new RoverMissionFactory(validator);

        RoverMissionParser parser = new RoverMissionParser(factory);

        List<RoverMission> missions = parser.parse(lines);

        CommandRegistry registry = new CommandRegistry(validator);

        RoverMissionService service = new RoverMissionService(registry);

        for (RoverMission mission : missions) {

            service.execute(mission);
        }

        missions.stream()
                .map(RoverMission::getRover)
                .forEach(Main::print);
    }

    private static void print(Rover rover) {

        System.out.printf(
                "%d %d %s%n",
                rover.getPosition().x(),
                rover.getPosition().y(),
                rover.getDirection().getCode());
    }
}
