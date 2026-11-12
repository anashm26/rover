package com.bnp.kata.rover.commands.registry;

import com.bnp.kata.rover.commands.strategies.CommandInterface;

public interface MissionRegistry {

    CommandInterface resolve(char commandCode);
}
