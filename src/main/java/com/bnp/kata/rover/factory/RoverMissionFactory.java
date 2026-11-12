package com.bnp.kata.rover.factory;

import com.bnp.kata.rover.model.MarsPlateau;
import com.bnp.kata.rover.model.RoverCoordinates;
import com.bnp.kata.rover.validation.RoverMissionValidator;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class RoverMissionFactory implements MissionFactory {

    private final RoverMissionValidator validator;

    @Override
    public RoverMissionData create(MarsPlateau marsPlateau,
                                   RoverCoordinates roverCoordinates,
                                   String instructions) {

        validator.validatePosition(roverCoordinates.position(), marsPlateau);

        validator.validateInstructions(instructions);

        return RoverMissionData.builder()
                .marsPlateau(marsPlateau)
                .roverCoordinates(roverCoordinates)
                .instructions(instructions)
                .build();
    }
}