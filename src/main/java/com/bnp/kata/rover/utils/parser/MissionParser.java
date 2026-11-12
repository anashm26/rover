package com.bnp.kata.rover.utils.parser;

import com.bnp.kata.rover.factory.RoverMissionData;

import java.util.List;

public interface MissionParser {

    List<RoverMissionData> parse(List<String> lines);
}
