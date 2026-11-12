package com.bnp.kata.rover.reporter;

import com.bnp.kata.rover.factory.RoverMissionData;
import com.bnp.kata.rover.formatter.ConsoleOutputFormatter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class ConsoleOutputReporter implements OutputReporter {

    private final ConsoleOutputFormatter formatter;

    @Override
    public void report(List<RoverMissionData> missions) {

        log.info("Reporting {} rover mission result(s)", missions.size());

        missions.stream()
                .map(formatter::format)
                .forEach(System.out::printf);
    }

}
