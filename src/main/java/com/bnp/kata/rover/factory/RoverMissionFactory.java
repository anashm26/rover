package com.bnp.kata.rover.factory;

import com.bnp.kata.rover.model.MarsPlateau;
import com.bnp.kata.rover.model.Rover;
import com.bnp.kata.rover.validation.RoverMissionValidator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RoverMissionFactory {

    private final RoverMissionValidator validator;

    public RoverMissionData create(MarsPlateau marsPlateau,
                                   Rover rover,
                                   String instructions) {

        validator.validatePosition(rover.position(), marsPlateau);

        validator.validateInstructions(instructions);

        return RoverMissionData.builder()
                .marsPlateau(marsPlateau)
                .rover(rover)
                .instructions(instructions)
                .build();
    }
}