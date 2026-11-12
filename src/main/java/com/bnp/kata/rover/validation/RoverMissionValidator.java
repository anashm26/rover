package com.bnp.kata.rover.validation;


import com.bnp.kata.rover.exceptions.InvalidInstructionsFormatException;
import com.bnp.kata.rover.exceptions.RoverOutOfMarsPlateauException;
import com.bnp.kata.rover.model.MarsPlateau;
import com.bnp.kata.rover.model.Position;
import com.bnp.kata.rover.model.enums.Command;


public class RoverMissionValidator {

    public void validatePosition(Position position, MarsPlateau marsPlateau) {

        if (marsPlateau.isOutOfMarsPlateau(position)) {

            throw new RoverOutOfMarsPlateauException(
                    "Rover position (%s,%s) outside mars plateau (%s,%s)."
                            .formatted(position.x(),
                                    position.y(),
                                    marsPlateau.upperRight().x(),
                                    marsPlateau.upperRight().y()));
        }
    }

    public void validateInstructions(String instructions) {

        if (instructions == null
                || instructions.isBlank()) {

            throw new InvalidInstructionsFormatException("Instructions cannot be empty");
        }

        for (char command : instructions.toCharArray()) {

            Command.of(command);
        }
    }
}