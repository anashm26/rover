package com.bnp.kata.rover.factory;

import com.bnp.kata.rover.model.MarsPlateau;
import com.bnp.kata.rover.model.Rover;
import com.bnp.kata.rover.validation.RoverValidator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RoverMissionFactory {

    private final RoverValidator validator;

    public RoverMission create(MarsPlateau marsPlateau,
                               Rover rover,
                               String instructions) {

        validator.validateInitialPosition(rover.getPosition(), marsPlateau);

        validator.validateInstructions(instructions);

        return RoverMission.builder()
                .marsPlateau(marsPlateau)
                .rover(rover)
                .instructions(instructions)
                .build();
    }
}