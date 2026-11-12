package com.bnp.kata.rover.commands.strategies;

import com.bnp.kata.rover.factory.RoverMissionData;
import com.bnp.kata.rover.model.enums.Direction;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TurnLeftCommand implements CommandInterface {

    @Override
    public void execute(RoverMissionData roverMissionData) {

        Direction previousDirection = roverMissionData.getRover().direction();

        Direction leftTurnDirection = Direction.of(previousDirection.getLeftTurn());

        roverMissionData.changeDirection(leftTurnDirection);

        log.info("Rover turned left from {} to {}",
                previousDirection,
                leftTurnDirection);
    }
}
