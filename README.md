# Mars Rover

A Java implementation of the classic Mars Rover kata.

The application reads rover missions from an input file, executes each mission on a plateau, and prints the final rover positions.

## Features

- Parse plateau and rover mission definitions from a text file
- Execute rover movement and rotation commands
- Validate rover deployment and movements
- Prevent out-of-bounds navigation
- Produce final rover positions in the expected format
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

Each rover mission is described using two lines:

1. Initial rover position (`X Y D`)
2. Navigation instructions (`L`, `R`, `M`)

The lower-left plateau coordinate is assumed to be:

```text
0 0
```

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
- **RoverMissionService** executes rover instructions.
- **RoverMissionValidator** validates rover deployment and movements.
- **OutputReporter** produces the final output.
- **RoverMissionOrchestrator** coordinates the complete workflow from input reading to result reporting.

Application flow:

1. Read input
2. Build missions
3. Execute rover commands
4. Report final rover positions

---

## Design Principles

- Single Responsibility Principle (SRP)
- Constructor-based dependency injection
- Command Pattern for rover instructions
- Immutable value objects
- Separation of input, execution and output concerns
- Centralized validation rules

The objective was to keep the implementation simple, testable and easy to extend while avoiding unnecessary abstractions.

---

## Build

```bash
mvn clean package
```

## Run

```bash
java -jar mars-rover.jar input.txt
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

## Running Integration Tests

Run a specific integration test:

```bash
mvn test -Dtest=RoverMissionDataIntegrationTest
```