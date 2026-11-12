package com.bnp.kata.rover.factory;

import com.bnp.kata.rover.model.MarsPlateau;
import com.bnp.kata.rover.model.Position;
import com.bnp.kata.rover.model.RoverCoordinates;
import com.bnp.kata.rover.model.enums.Direction;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class RoverMissionData {

    private final MarsPlateau marsPlateau;
    private final String instructions;

    private RoverCoordinates roverCoordinates;

    RoverMissionData(MarsPlateau marsPlateau, String instructions, RoverCoordinates roverCoordinates) {

        this.marsPlateau = marsPlateau;
        this.instructions = instructions;

        this.roverCoordinates = roverCoordinates;
    }

    public void moveTo(Position position) {

        Direction direction = getRoverCoordinates().direction();

        this.roverCoordinates = new RoverCoordinates(position, direction);
    }

    public void changeDirection(Direction direction) {

        this.roverCoordinates = new RoverCoordinates(roverCoordinates.position(), direction);
    }
}
