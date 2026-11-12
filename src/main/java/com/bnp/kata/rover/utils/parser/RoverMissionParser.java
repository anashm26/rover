package com.bnp.kata.rover.utils.parser;

import com.bnp.kata.rover.exceptions.InvalidInstructionsFormatException;
import com.bnp.kata.rover.factory.RoverMissionData;
import com.bnp.kata.rover.factory.RoverMissionFactory;
import com.bnp.kata.rover.model.MarsPlateau;
import com.bnp.kata.rover.model.Position;
import com.bnp.kata.rover.model.Rover;
import com.bnp.kata.rover.model.enums.Direction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class RoverMissionParser {

    private final RoverMissionFactory factory;

    private static final String SPACE_DELIMITER = "\\s+";

    public List<RoverMissionData> parse(List<String> lines) {

        log.info("Starting rover mission parsing");

        validate(lines);

        MarsPlateau marsPlateau = parseMarsPlateau(lines.getFirst());
        log.info(
                "Creating marsPlateau [{} {}]",
                marsPlateau.upperRight().x(),
                marsPlateau.upperRight().y()
        );

        List<RoverMissionData> missions = new ArrayList<>();

        for (int i = 1; i < lines.size(); i += 2) {

            Rover rover = parseRover(lines.get(i));
            String instructions = lines.get(i + 1);

            log.info("Creating instructions [{}]", instructions);

            RoverMissionData roverMissionData = factory.create(marsPlateau, rover, instructions);

            log.info(
                    "Creating mission for rover [{} {} {}]",
                    rover.position().x(),
                    rover.position().y(),
                    rover.direction()
            );
            missions.add(roverMissionData);
        }

        log.info("Successfully parsed {} rover missions", missions.size());

        return missions;
    }

    private void validate(List<String> lines) {

        if (lines.size() < 3
                || lines.size() % 2 == 0) {

            throw new InvalidInstructionsFormatException("Invalid input format number of instruction lines not valid.");
        }
    }

    private MarsPlateau parseMarsPlateau(String line) {

        String[] marsPlateauDimensions = parseLine(line);

        if(marsPlateauDimensions.length < 2) {

            throw new InvalidInstructionsFormatException("Invalid input format number of instruction lines for marsPlateau not valid.");
        }

        return MarsPlateau.builder()
                .upperRight(Position.of(marsPlateauDimensions[0], marsPlateauDimensions[1]))
                .build();
    }

    private Rover parseRover(String line) {

        String[] coordinates = parseLine(line);

        if(coordinates.length < 3) {

            throw new InvalidInstructionsFormatException("Invalid input format number of instruction lines for rover coordinates not valid.");
        }

        return Rover.builder()
                .position(Position.of(coordinates[0], coordinates[1]))
                .direction(Direction.of(coordinates[2].charAt(0)))
                .build();
    }

    private String[] parseLine(String line) {

        if(line == null || line.isBlank()){

            throw new InvalidInstructionsFormatException("Invalid input format instruction line should be not null or blank.");
        }

        return line.trim().split(SPACE_DELIMITER);
    }
    
}