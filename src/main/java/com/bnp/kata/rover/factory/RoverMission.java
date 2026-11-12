package com.bnp.kata.rover.factory;

import com.bnp.kata.rover.model.MarsPlateau;
import com.bnp.kata.rover.model.Rover;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public final class RoverMission {

    private final MarsPlateau marsPlateau;
    private final Rover rover;
    private final String instructions;

    RoverMission(MarsPlateau marsPlateau, Rover rover, String instructions) {

        this.marsPlateau = marsPlateau;
        this.rover = rover;
        this.instructions = instructions;
    }
}
