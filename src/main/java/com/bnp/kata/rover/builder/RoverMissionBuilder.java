package com.bnp.kata.rover.builder;

import com.bnp.kata.rover.commands.registry.MissionCommandRegistry;
import com.bnp.kata.rover.commands.registry.MissionRegistry;
import com.bnp.kata.rover.factory.MissionFactory;
import com.bnp.kata.rover.factory.RoverMissionData;
import com.bnp.kata.rover.factory.RoverMissionFactory;
import com.bnp.kata.rover.reader.InstructionsInputReader;
import com.bnp.kata.rover.service.RoverMissionService;
import com.bnp.kata.rover.utils.parser.MissionParser;
import com.bnp.kata.rover.utils.parser.RoverMissionParser;
import com.bnp.kata.rover.validation.RoverMissionValidator;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class RoverMissionBuilder implements MissionBuilder {

    private final InstructionsInputReader reader;
    private final RoverMissionValidator validator;

    @Override
    public List<RoverMissionData> buildMissionData() throws IOException {

        List<String> lines = reader.readAll();

        MissionFactory factory = new RoverMissionFactory(validator);

        MissionParser parser = new RoverMissionParser(factory);

        return parser.parse(lines);
    }

    public RoverMissionService buildService() {

        MissionRegistry registry = new MissionCommandRegistry(validator);

        return new RoverMissionService(registry);
    }
}