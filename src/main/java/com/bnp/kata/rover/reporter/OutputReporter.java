package com.bnp.kata.rover.reporter;

import com.bnp.kata.rover.factory.RoverMissionData;

import java.util.List;

public interface OutputReporter {

    void report(List<RoverMissionData> missions);
}
