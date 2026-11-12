package com.bnp.kata.rover.model;

import lombok.Builder;

@Builder
public record MarsPlateau(
        Position upperRight
) {

    public boolean isOutOfMarsPlateau(Position position){

        return position.x() < 0
                || position.y() < 0
                || position.x() > upperRight.x()
                || position.y() > upperRight.y();
    }
}
