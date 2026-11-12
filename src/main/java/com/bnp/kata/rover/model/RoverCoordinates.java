package com.bnp.kata.rover.model;

import com.bnp.kata.rover.model.enums.Direction;
import lombok.Builder;

@Builder
public record RoverCoordinates(
        Position position,
        Direction direction
) {}