package com.bnp.kata.rover.service;

import com.bnp.kata.rover.commands.registry.CommandRegistry;
import com.bnp.kata.rover.factory.RoverMission;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RoverMissionService {

    private final CommandRegistry registry;

    public void execute(RoverMission mission) {

        for (char command :mission.getInstructions().toCharArray()) {

            registry.resolve(command)
                    .execute(mission);
        }
    }

}