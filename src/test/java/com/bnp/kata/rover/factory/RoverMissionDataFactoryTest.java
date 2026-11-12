package com.bnp.kata.rover.factory;

import com.bnp.kata.rover.exceptions.InvalidInstructionsFormatException;
import com.bnp.kata.rover.exceptions.RoverException;
import com.bnp.kata.rover.exceptions.RoverOutOfMarsPlateauException;
import com.bnp.kata.rover.model.MarsPlateau;
import com.bnp.kata.rover.model.Position;
import com.bnp.kata.rover.model.Rover;
import com.bnp.kata.rover.model.enums.Direction;
import com.bnp.kata.rover.validation.RoverMissionValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RoverMissionDataFactoryTest {

    private final RoverMissionFactory factory = new RoverMissionFactory(new RoverMissionValidator());

    @Test
    void createsValidMission(){

        //given
        MarsPlateau marsPlateau = new MarsPlateau(new Position(5,7));
        Rover rover = Rover.builder()
                .position(new Position(1,2))
                .direction(Direction.NORTH)
                .build();

        String instructions = "LMMRM";

        //when
        RoverMissionData roverMissionData = factory.create(marsPlateau, rover, instructions);

        //then
        assertEquals(5, roverMissionData.getMarsPlateau().upperRight().x());
        assertEquals(7, roverMissionData.getMarsPlateau().upperRight().y());

        assertEquals(1, roverMissionData.getRover().position().x());
        assertEquals(2, roverMissionData.getRover().position().y());

        assertEquals(Direction.NORTH, roverMissionData.getRover().direction());
        assertEquals("LMMRM", roverMissionData.getInstructions());
    }

    @Test
    void shouldThrowExceptionWhenPositionOutOfMarsPlateau(){

        //given
        MarsPlateau marsPlateau = new MarsPlateau(new Position(5,7));
        Rover rover = Rover.builder()
                .position(new Position(6,2))
                .direction(Direction.NORTH)
                .build();

        String instructions = "LMMRM";

        //when-then
        RoverException exception = assertThrows(RoverOutOfMarsPlateauException.class,
                () -> factory.create(marsPlateau, rover, instructions));

        String expectedErrorMessage = "Rover position (6,2) outside mars plateau (5,7).";

        assertEquals(expectedErrorMessage, exception.getMessage()) ;
    }

    @Test
    void shouldThrowExceptionWhenInvalidInstructions(){

        //given
        MarsPlateau marsPlateau = new MarsPlateau(new Position(5,7));
        Rover rover = Rover.builder()
                .position(new Position(1,1))
                .direction(Direction.NORTH)
                .build();

        String instructions = "ZZZ";

        //when-then
        RoverException exception = assertThrows(InvalidInstructionsFormatException.class,
                () -> factory.create(marsPlateau, rover, instructions));

        String expectedErrorMessage = "Invalid input format unknown code [Z] for enum Command. Allowed values [L, R, M]";

        assertEquals(expectedErrorMessage, exception.getMessage()) ;
    }

}