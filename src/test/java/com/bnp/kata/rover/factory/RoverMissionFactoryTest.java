package com.bnp.kata.rover.factory;

import com.bnp.kata.rover.exceptions.InvalidInputFormatException;
import com.bnp.kata.rover.exceptions.RoverException;
import com.bnp.kata.rover.exceptions.RoverOutOfMarsPlateauException;
import com.bnp.kata.rover.model.MarsPlateau;
import com.bnp.kata.rover.model.Position;
import com.bnp.kata.rover.model.Rover;
import com.bnp.kata.rover.model.enums.Direction;
import com.bnp.kata.rover.validation.RoverValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RoverMissionFactoryTest {

    private final RoverMissionFactory factory = new RoverMissionFactory(new RoverValidator());

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
        RoverMission roverMission = factory.create(marsPlateau, rover, instructions);

        //then
        assertEquals(5, roverMission.getMarsPlateau().upperRight().x());
        assertEquals(7, roverMission.getMarsPlateau().upperRight().y());

        assertEquals(1, roverMission.getRover().getPosition().x());
        assertEquals(2, roverMission.getRover().getPosition().y());

        assertEquals(Direction.NORTH, roverMission.getRover().getDirection());
        assertEquals("LMMRM", roverMission.getInstructions());
    }

    @Test
    void shouldThrowExceptionWhenInitialPositionOutOfMarsPlateau(){

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

        String expectedErrorMessage = "Initial position (6,2) outside mars plateau (5,7).";

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
        RoverException exception = assertThrows(InvalidInputFormatException.class,
                () -> factory.create(marsPlateau, rover, instructions));

        String expectedErrorMessage = "Invalid input format unknown code [Z] for enum Command. Allowed values [L, R, M]";

        assertEquals(expectedErrorMessage, exception.getMessage()) ;
    }

}