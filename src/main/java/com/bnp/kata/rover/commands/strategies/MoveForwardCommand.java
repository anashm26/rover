package com.bnp.kata.rover.commands.strategies;

import com.bnp.kata.rover.factory.RoverMission;
import com.bnp.kata.rover.model.Position;
import com.bnp.kata.rover.model.Rover;
import com.bnp.kata.rover.model.enums.Direction;
import com.bnp.kata.rover.validation.RoverValidator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MoveForwardCommand implements CommandInterface {

    private final RoverValidator validator;

    @Override
    public void execute(RoverMission roverMission) {

        Rover rover = roverMission.getRover();

        Direction direction = rover.getDirection();

        Position current = rover.getPosition();

        Position next = Position.builder()
                .x(current.x() + direction.getDeltaX())
                .y(current.y() + direction.getDeltaY())
                .build();

        validator.validateMovement(next, roverMission.getMarsPlateau());

        rover.setPosition(next);

    }
}
