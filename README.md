# Mars Rover Mission
A Java application that simulates rovers moving on a rectangular plateau.

## Features

- Move a rover forward and rotate left or right.
- Support multiple rover missions on the same plateau.
- Keep rover positions inside the plateau.
- Parse and validate mission input.
- Report invalid input and movement through clear exceptions.
- Verify behavior with unit and integration tests.

## Requirements

- Java 17 or later
- Maven

## Build and Test

Run these commands from the project directory:

```bash
mvn clean test
mvn clean package
```

## Running the JAR with an input file
You can execute the application by passing the input.txt file path as the first command line argument: 

```bash
java -jar rover.jar input.txt
```

## Mission Input

The input starts with the plateau's maximum X and Y coordinates. Each rover then has a
starting position/direction line followed by its instruction sequence.

```text
5 5
1 2 N
LMLMLMLMM
3 3 E
MMRMM
```

Directions: `N`, `E`, `S`, `W`.

Commands:
- `L` — turn left.
- `R` — turn right.
- `M` — move forward one position.

## Project Structure

```text
src/
  main/java/       Application source code
  test/java/       Unit and integration tests
```


## Architecture

The application is organized into small packages.

```text
src/main/java/
  model/                 Rover, Position, Direction
  commands/              CommandInterface
  commands/registry/     CommandRegistry
  factory/               RoverMission and RoverMissionFactory
  validation/            RoverValidator
  utils/parser/          RoverMissionParser
  service/               RoverMissionService
```

### Main Components

- **Model:** 
  - `Rover` stores its current position and direction. 
  - `Position` represents
    coordinates and calculates the next position. 
  - `Direction` defines the four directions and its rotations.

- **Commands:** 
  - `Command` interface represents an instruction. 
  - `CommandRegistry`
    maps Command Enum values to the corresponding rover actions.
- **Validation:** 
  - `RoverMissionValidator` checks plateau dimensions, starting positions,
    movement boundaries, and allowed instruction characters.
- **Factory:** 
  - `RoverMissionFactory` validates mission data and creates a rover and
    its `RoverMissionData` object.
- **Parser:** 
  - `RoverMissionParser` reads the input lines, removes blank/comment lines,
    parses the plateau and rover data, and creates missions through the factory.
- **Service:** 
  - `RoverMissionService` executes each instruction in order. Before a
    forward move, it calculates and validates the proposed position so an invalid move
    is rejected before the rover changes position.

### Execution Flow

1. The parser receives the mission input.
2. The factory and validator check the plateau, rover starting data, and instructions.
3. A `RoverMissionData` is created for each rover.
4. The service processes each mission's instructions in sequence.
5. The command registry dispatches each instruction to the rover.
6. The rover's final position and direction can then be read from its model.

## Tests

Tests are under `src/test/java/` and are grouped by purpose:

- **Unit tests:** model, parser, factory, validator, command registry, and service behavior.
- **Integration tests:** complete rover mission success and exceptions (error) scenarios.

