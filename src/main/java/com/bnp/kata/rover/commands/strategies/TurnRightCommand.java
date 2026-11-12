package com.bnp.kata.rover.commands.strategies;

import com.bnp.kata.rover.factory.RoverMissionData;
import com.bnp.kata.rover.model.enums.Direction;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TurnRightCommand implements CommandInterface {

    @Override
    public void execute(RoverMissionData roverMissionData) {

        Direction previousDirection = roverMissionData.getRoverCoordinates().direction();

        Direction rightTurnDirection = Direction.of(previousDirection.getRightTurn());

        roverMissionData.changeDirection(rightTurnDirection);

        log.info("Rover turned right from {} to {}",
                previousDirection,
                rightTurnDirection);
    }
}
