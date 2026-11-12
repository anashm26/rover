package com.bnp.kata.rover.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileInputReader implements InputReader {

    @Override
    public List<String> read(String source) throws IOException {

        return Files.readAllLines(Path.of(source));
    }
}
