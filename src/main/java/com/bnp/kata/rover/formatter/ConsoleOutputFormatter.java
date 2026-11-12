package com.bnp.kata.rover.formatter;

import com.bnp.kata.rover.model.RoverCoordinates;

public class ConsoleOutputFormatter implements OutputFormatter{

    @Override
    public String format(RoverCoordinates roverCoordinates) {

        return String.format("%d %d %s%n",
                roverCoordinates.position().x(),
                roverCoordinates.position().y(),
                roverCoordinates.direction().getCode());
    }
}
