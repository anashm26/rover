package com.bnp.kata.rover.service;

import com.bnp.kata.rover.commands.registry.MissionRegistry;
import com.bnp.kata.rover.factory.RoverMissionData;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RoverMissionService implements RoverMissionStrategy {

    private final MissionRegistry registry;

    public void execute(RoverMissionData mission) {

        for (char command : mission.getInstructions().toCharArray()) {

            registry.resolve(command)
                    .execute(mission);
        }
    }

}