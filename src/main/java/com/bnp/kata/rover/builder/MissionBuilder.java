package com.bnp.kata.rover.builder;

import com.bnp.kata.rover.factory.RoverMissionData;
import com.bnp.kata.rover.service.RoverMissionStrategy;

import java.io.IOException;
import java.util.List;

public interface MissionBuilder {

    List<RoverMissionData> buildMissionData() throws IOException;

    RoverMissionStrategy buildService();
}
