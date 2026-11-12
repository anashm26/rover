package com.bnp.kata.rover.commands.strategies;

import com.bnp.kata.rover.factory.RoverMission;
import com.bnp.kata.rover.model.Rover;
import com.bnp.kata.rover.model.enums.Direction;

public class TurnLeftCommand implements CommandInterface {

    @Override
    public void execute(RoverMission roverMission) {

        Rover rover = roverMission.getRover();

        rover.setDirection(Direction.of(rover.getDirection().getLeftTurn()));
    }
}
