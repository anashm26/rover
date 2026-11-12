package com.bnp.kata.rover.commands.strategies;

import com.bnp.kata.rover.factory.RoverMissionData;
import com.bnp.kata.rover.model.Position;
import com.bnp.kata.rover.model.enums.Direction;
import com.bnp.kata.rover.validation.RoverMissionValidator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MoveForwardCommand implements CommandInterface {

    private final RoverMissionValidator validator;

    @Override
    public void execute(RoverMissionData roverMissionData) {

        Direction direction = roverMissionData.getRover().direction();

        Position current = roverMissionData.getRover().position();

        Position next = Position.builder()
                .x(current.x() + direction.getDeltaX())
                .y(current.y() + direction.getDeltaY())
                .build();

        validator.validatePosition(next, roverMissionData.getMarsPlateau());

        roverMissionData.moveTo(next);

    }
}
