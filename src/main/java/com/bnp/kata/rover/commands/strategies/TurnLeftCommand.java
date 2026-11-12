package com.bnp.kata.rover.commands.strategies;

import com.bnp.kata.rover.factory.RoverMissionData;
import com.bnp.kata.rover.model.enums.Direction;

public class TurnLeftCommand implements CommandInterface {

    @Override
    public void execute(RoverMissionData roverMissionData) {

        Direction leftTurnDirection = Direction.of(roverMissionData.getRover().direction().getLeftTurn());

        roverMissionData.changeDirection(leftTurnDirection);
    }
}
