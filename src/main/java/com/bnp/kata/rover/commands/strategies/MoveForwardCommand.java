package com.bnp.kata.rover.commands.strategies;

import com.bnp.kata.rover.factory.RoverMissionData;
import com.bnp.kata.rover.model.Position;
import com.bnp.kata.rover.model.enums.Direction;
import com.bnp.kata.rover.validation.RoverMissionValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class MoveForwardCommand implements CommandInterface {

    private final RoverMissionValidator validator;

    @Override
    public void execute(RoverMissionData roverMissionData) {

        Position current = roverMissionData.getRover().position();

        Direction direction = roverMissionData.getRover().direction();

        Position next = Position.builder()
                .x(current.x() + direction.getDeltaX())
                .y(current.y() + direction.getDeltaY())
                .build();

        log.info("Moving rover from ({}, {}) to ({}, {}) facing {}",
                current.x(),
                current.y(),
                next.x(),
                next.y(),
                direction);

        validator.validatePosition(next, roverMissionData.getMarsPlateau());

        roverMissionData.moveTo(next);

        log.info("Rover moved successfully to ({}, {})", next.x(), next.y());

    }
}
