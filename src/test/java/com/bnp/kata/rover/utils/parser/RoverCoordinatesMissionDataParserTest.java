package com.bnp.kata.rover.utils.parser;

import com.bnp.kata.rover.exceptions.InvalidInstructionsFormatException;
import com.bnp.kata.rover.exceptions.RoverException;
import com.bnp.kata.rover.factory.RoverMissionData;
import com.bnp.kata.rover.factory.RoverMissionFactory;
import com.bnp.kata.rover.model.enums.Direction;
import com.bnp.kata.rover.validation.RoverMissionValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RoverCoordinatesMissionDataParserTest {

    private RoverMissionParser parser;

    @BeforeEach
    void setUp() {

        RoverMissionFactory factory = new RoverMissionFactory(new RoverMissionValidator());

        parser = new RoverMissionParser(factory);
    }

    @Test
    void parseOneMission() {

        //given
        List<String> input = List.of(
                "5 5",
                "1 2 N",
                "LMRMLM");

        //when
        List<RoverMissionData> roverMissionData = parser.parse(input);

        //then
        assertEquals(1, roverMissionData.size());

        assertEquals(1, roverMissionData.getFirst().getRoverCoordinates().position().x());
        assertEquals(2, roverMissionData.getFirst().getRoverCoordinates().position().y());
        assertEquals(Direction.NORTH, roverMissionData.getFirst().getRoverCoordinates().direction());
        assertEquals("LMRMLM", roverMissionData.getFirst().getInstructions());
    }

    @Test
    void parseMultipleMissions() {

        //given
        List<String> input = List.of(
                "5 5",
                "1 2 N",
                "LMRMLM",
                "3 3 S",
                "LMRMLM",
                "1 1 E",
                "LMRMLM",
                "2 3 E",
                "LMRMLM");

        //when
        List<RoverMissionData> roverMissionData = parser.parse(input);

        //then
        assertEquals(4, roverMissionData.size());
    }

    @Test
    void shouldThrowExceptionWhenMarsPlateauInputNotValid(){

        //given
        List<String> input = List.of(
                "3",
                "1 2 N",
                "LMRMLM");

        //when-then
        RoverException roverException = assertThrows(InvalidInstructionsFormatException.class,
                () -> parser.parse(input));

        assertEquals("Invalid input format number of instruction lines for marsPlateau not valid.", roverException.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenRoverCoordinatesInputNotValid(){

        //given
        List<String> input = List.of(
                "5 5",
                "1 2",
                "LMRMLM");

        //when-then
        RoverException roverException = assertThrows(InvalidInstructionsFormatException.class,
                () -> parser.parse(input));

        assertEquals("Invalid input format number of instruction lines for rover coordinates not valid.", roverException.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenRoverCoordinatesAreNonNumeric(){

        //given
        List<String> input = List.of(
                "5 5",
                "B 3 N",
                "LMRMLM");

        //when-then
        Throwable roverException = assertThrows(NumberFormatException.class,
                () -> parser.parse(input));

        assertEquals("For input string: \"B\"", roverException.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenInstructionsInputBlank(){

        //given
        List<String> input = List.of(
                "5 5",
                " ",
                "LMRMLM");

        //when-then
        RoverException roverException = assertThrows(InvalidInstructionsFormatException.class,
                () -> parser.parse(input));

        assertEquals("Invalid input format instruction line should be not null or blank.", roverException.getMessage());
    }


}
