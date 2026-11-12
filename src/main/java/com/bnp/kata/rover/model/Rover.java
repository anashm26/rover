package com.bnp.kata.rover.model;

import com.bnp.kata.rover.model.enums.Direction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public record Rover(
        Position position,
        Direction direction
) {}