package com.bnp.kata.rover.commands.strategies;

import com.bnp.kata.rover.factory.RoverMission;

public interface CommandInterface {

    void execute(RoverMission roverMission);
}
