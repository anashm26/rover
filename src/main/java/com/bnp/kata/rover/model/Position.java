package com.bnp.kata.rover.model;

import lombok.Builder;

@Builder
public record Position(
        int x,
        int y
) {

    public static Position of(String x, String y) {

        return Position.builder()
                .x(Integer.parseInt(x))
                .y(Integer.parseInt(y))
                .build();
    }
}