package com.bnp.kata.rover.formatter;

import com.bnp.kata.rover.factory.RoverMissionData;

public class ConsoleOutputFormatter implements OutputFormatter{

    public String format(RoverMissionData roverMissionData) {

        return String.format("%d %d %s%n",
                roverMissionData.getRover().position().x(),
                roverMissionData.getRover().position().y(),
                roverMissionData.getRover().direction().getCode());


    }
}
