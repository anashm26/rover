# Mars Rover Mission

A Java implementation of the classic Mars Rover kata.

The application reads roverCoordinates missions from an input file, executes each mission on a plateau, and prints the final roverCoordinates positions.



## Requirements

- Java 21+
- Maven 3.9+

### Libraries

- Lombok - Boilerplate code reduction

### Testing

- JUnit 5 - Unit testing
- AssertJ - Fluent assertions


## Build

```bash
mvn clean package
```

## Run

```bash
java -jar mars-roverCoordinates.jar input.txt
```


## Running Tests

Run all tests:

```bash
mvn test
```

Run a specific unit test:

```bash
mvn test -Dtest=CommandRegistryTest
```

Run integration tests with success and errors scenarios: 

```bash
mvn test -Dtest=RoverMissionIntegrationTest
```

## Features

- Parse plateau and roverCoordinates mission definitions from a text file
- Execute roverCoordinates movement and rotation commands
- Validate roverCoordinates deployment and movements
- Prevent out-of-bounds navigation
- Produce final roverCoordinates positions in the expected format
- Extensible command architecture using the Command Pattern

---

## Input Format

Example:

```text
5 5
1 2 N
LMLMLMLMM
3 3 E
MMRMMRMRRM
```


The first line defines the plateau boundaries.

Each roverCoordinates mission is described using two lines:

1. Initial roverCoordinates position (`X Y D`)
2. Navigation instructions (`L`, `R`, `M`)
3. The lower-left plateau coordinate is assumed to be: `0  0`


---

## Commands

- `L` : Turn left
- `R` : Turn right
- `M` : Move forward

---
## Expected Output

For the sample input:

```text
5 5
1 2 N
LMLMLMLMM
3 3 E
MMRMMRMRRM
```

Output:

```text
1 3 N
5 1 E
```

---

## Architecture

The application is organized around a small set of responsibilities:

- **InputReader** loads the mission input.
- **RoverMissionBuilder** parses the input and builds the mission execution components.
- **RoverMissionService** executes roverCoordinates instructions.
- **RoverMissionValidator** validates roverCoordinates deployment and movements.
- **OutputReporter** produces the final output.
- **RoverMissionOrchestrator** coordinates the complete workflow from input reading to result reporting.

Application flow:

1. Read input
2. Build missions
3. Execute roverCoordinates commands
4. Report final roverCoordinates positions

---

## Design Principles

- Single Responsibility Principle (SRP)
- Constructor-based dependency injection
- Command Pattern for roverCoordinates instructions
- Immutable value objects
- Separation of input, execution and output concerns
- Centralized validation rules

The objective was to keep the implementation simple, testable and easy to extend while avoiding unnecessary abstractions.
