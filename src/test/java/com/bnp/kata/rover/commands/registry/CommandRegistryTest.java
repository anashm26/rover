package com.bnp.kata.rover.commands.registry;

import com.bnp.kata.rover.exceptions.InvalidInstructionsFormatException;
import com.bnp.kata.rover.factory.RoverMissionData;
import com.bnp.kata.rover.model.MarsPlateau;
import com.bnp.kata.rover.model.Position;
import com.bnp.kata.rover.model.Rover;
import com.bnp.kata.rover.model.enums.Direction;
import com.bnp.kata.rover.validation.RoverMissionValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommandRegistryTest {

    private final CommandRegistry commandRegistry = new CommandRegistry(new RoverMissionValidator());

    private static final char LEFT_COMMAND = 'L';
    private static final char RIGHT_COMMAND = 'R';
    private static final char MOVE_FORWARD_COMMAND = 'M';
    private static final char UNKNOWN_COMMAND = 'X';

    @Test
    void executeLeftCommand(){

        //given
        RoverMissionData roverMissionData = createRoverMission();

        //when
        commandRegistry.resolve(LEFT_COMMAND)
                .execute(roverMissionData);

        Direction actualDirection = roverMissionData.getRover().direction();

        Direction expectedDirection = Direction.WEST;

        //then
        assertEquals(expectedDirection, actualDirection);
    }

    @Test
    void executeRightCommand(){

        //given
        RoverMissionData roverMissionData = createRoverMission();

        //when
        commandRegistry.resolve(RIGHT_COMMAND)
                .execute(roverMissionData);

        Direction actualDirection = roverMissionData.getRover().direction();

        Direction expectedDirection = Direction.EAST;

        //then
        assertEquals(expectedDirection, actualDirection);
    }

    @Test
    void executeMoveForwardCommand(){

        //given
        RoverMissionData roverMissionData = createRoverMission();

        //when
        commandRegistry.resolve(MOVE_FORWARD_COMMAND)
                .execute(roverMissionData);

        Position actualPosition = roverMissionData.getRover().position();

        int expectedXPosition = 0;
        int expectedYPosition = 1;

        //then
        assertEquals(expectedXPosition, actualPosition.x());
        assertEquals(expectedYPosition, actualPosition.y());
    }

    @Test
    void shouldThrowExceptionWhenUnknownCommand(){

        //when - then
        Throwable ex = assertThrows(InvalidInstructionsFormatException.class,
                () -> commandRegistry.resolve(UNKNOWN_COMMAND));

        String expectedErrorMessage = "Invalid input format unknown code [X] for enum Command. Allowed values [L, R, M]";

        assertEquals(expectedErrorMessage, ex.getMessage());
    }


    private RoverMissionData createRoverMission(){

        Rover rover = Rover.builder()
                .position(new Position(0,0))
                .direction(Direction.NORTH)
                .build();

        return RoverMissionData.builder()
                .rover(rover)
                .marsPlateau(new MarsPlateau(new Position(5,5)))
                .build();
    }
}
