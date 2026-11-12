package com.bnp.kata.rover.commands.registry;

import com.bnp.kata.rover.commands.strategies.CommandInterface;
import com.bnp.kata.rover.commands.strategies.MoveForwardCommand;
import com.bnp.kata.rover.commands.strategies.TurnLeftCommand;
import com.bnp.kata.rover.commands.strategies.TurnRightCommand;
import com.bnp.kata.rover.model.enums.Command;
import com.bnp.kata.rover.validation.RoverMissionValidator;

import java.util.Map;

public class MissionCommandRegistry implements MissionRegistry{

    private final Map<Command, CommandInterface> registry;

    public MissionCommandRegistry(RoverMissionValidator validator) {

        registry = Map.of(
                Command.MOVE_FORWARD, new MoveForwardCommand(validator),
                Command.TURN_LEFT, new TurnLeftCommand(),
                Command.TURN_RIGHT, new TurnRightCommand()
        );
    }

    @Override
    public CommandInterface resolve(char commandCode) {

        return registry.get(Command.of(commandCode));
    }
}