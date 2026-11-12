package com.bnp.kata.rover.factory;

import com.bnp.kata.rover.model.MarsPlateau;
import com.bnp.kata.rover.model.RoverCoordinates;

public interface MissionFactory {

    RoverMissionData create(MarsPlateau marsPlateau,
                            RoverCoordinates roverCoordinates,
                            String instructions);
}
