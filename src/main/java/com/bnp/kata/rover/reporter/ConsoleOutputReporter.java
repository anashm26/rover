package com.bnp.kata.rover.reporter;

import com.bnp.kata.rover.formatter.OutputFormatter;
import com.bnp.kata.rover.model.RoverCoordinates;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class ConsoleOutputReporter implements OutputReporter {

    private final OutputFormatter formatter;

    @Override
    public void report(List<RoverCoordinates> roverCoordinates) {

        log.info("Reporting {} rover mission result(s)", roverCoordinates.size());

        roverCoordinates.stream()
                .map(formatter::format)
                .forEach(System.out::printf);
    }

}
