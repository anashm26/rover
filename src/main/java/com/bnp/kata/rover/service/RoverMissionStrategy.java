package com.bnp.kata.rover.service;

import com.bnp.kata.rover.factory.RoverMissionData;

public interface RoverMissionStrategy {

    void execute(RoverMissionData roverMissionData);
}
