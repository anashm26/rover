package com.bnp.kata.rover.validation;


import com.bnp.kata.rover.exceptions.InvalidInputFormatException;
import com.bnp.kata.rover.exceptions.RoverOutOfMarsPlateauException;
import com.bnp.kata.rover.model.MarsPlateau;
import com.bnp.kata.rover.model.Position;
import com.bnp.kata.rover.model.enums.Command;

public class RoverValidator {

    public void validateInitialPosition(Position position, MarsPlateau marsPlateau) {

        validatePosition(position, marsPlateau, "Initial position (%s,%s) outside mars plateau (%s,%s).");
    }

    public void validateMovement(Position position, MarsPlateau marsPlateau) {

        validatePosition(position, marsPlateau, "Rover position (%s,%s) outside mars plateau (%s,%s).");
    }

    private void validatePosition(Position position, MarsPlateau marsPlateau, String errorMessage) {

        if (marsPlateau.isOutOfMarsPlateau(position)) {

            throw new RoverOutOfMarsPlateauException(
                    errorMessage
                            .formatted(position.x(),
                                    position.y(),
                                    marsPlateau.upperRight().x(),
                                    marsPlateau.upperRight().y()));
        }
    }

    public void validateInstructions(String instructions) {

        if (instructions == null
                || instructions.isBlank()) {

            throw new InvalidInputFormatException("Instructions cannot be empty");
        }

        for (char command : instructions.toCharArray()) {

            Command.of(command);
        }
    }
}