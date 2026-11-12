package com.bnp.kata.rover.builder;

import com.bnp.kata.rover.commands.registry.CommandRegistry;
import com.bnp.kata.rover.factory.RoverMissionData;
import com.bnp.kata.rover.factory.RoverMissionFactory;
import com.bnp.kata.rover.service.RoverMissionService;
import com.bnp.kata.rover.utils.parser.RoverMissionParser;
import com.bnp.kata.rover.validation.RoverMissionValidator;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class RoverMissionBuilder {

    private final RoverMissionValidator validator;

    public List<RoverMissionData> buildMissions(List<String> lines) {

        RoverMissionFactory factory = new RoverMissionFactory(validator);

        RoverMissionParser parser = new RoverMissionParser(factory);

        return parser.parse(lines);
    }

    public RoverMissionService buildService() {

        CommandRegistry registry = new CommandRegistry(validator);

        return new RoverMissionService(registry);
    }
}