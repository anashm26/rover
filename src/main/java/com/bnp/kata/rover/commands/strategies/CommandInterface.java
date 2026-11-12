package com.bnp.kata.rover.commands.strategies;

import com.bnp.kata.rover.factory.RoverMissionData;

public interface CommandInterface {

    void execute(RoverMissionData roverMissionData);
}
