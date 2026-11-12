package com.bnp.kata.rover.model;

import com.bnp.kata.rover.model.enums.Direction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class Rover {

    private Position position;
    private Direction direction;
}