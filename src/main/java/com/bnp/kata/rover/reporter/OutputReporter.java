package com.bnp.kata.rover.reporter;

import com.bnp.kata.rover.model.RoverCoordinates;

import java.util.List;

public interface OutputReporter {

    void report(List<RoverCoordinates> roverCoordinates);
}
