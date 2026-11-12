package com.bnp.kata.rover.reader;

import java.io.IOException;
import java.util.List;

public interface InstructionsInputReader {

    List<String> readAll() throws IOException;
}
