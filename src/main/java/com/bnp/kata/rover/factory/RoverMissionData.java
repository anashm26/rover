package com.bnp.kata.rover.factory;

import com.bnp.kata.rover.model.MarsPlateau;
import com.bnp.kata.rover.model.Position;
import com.bnp.kata.rover.model.Rover;
import com.bnp.kata.rover.model.enums.Direction;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class RoverMissionData {

    private final MarsPlateau marsPlateau;
    private final String instructions;

    private Rover rover;

    RoverMissionData(MarsPlateau marsPlateau, String instructions, Rover rover) {

        this.marsPlateau = marsPlateau;
        this.instructions = instructions;

        this.rover = rover;
    }

    public void moveTo(Position position) {

        Direction direction = getRover().direction();

        this.rover = new Rover(position, direction);
    }

    public void changeDirection(Direction direction) {

        this.rover = new Rover(rover.position(), direction);
    }
}
