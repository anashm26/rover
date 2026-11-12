package com.bnp.kata.rover.commands.strategies;

import com.bnp.kata.rover.factory.RoverMissionData;
import com.bnp.kata.rover.model.Rover;
import com.bnp.kata.rover.model.enums.Direction;

public class TurnRightCommand implements CommandInterface {

    @Override
    public void execute(RoverMissionData roverMissionData) {

        Direction rightTurnDirection = Direction.of(roverMissionData.getRover().direction().getRightTurn());

        roverMissionData.changeDirection(rightTurnDirection);
    }
}
