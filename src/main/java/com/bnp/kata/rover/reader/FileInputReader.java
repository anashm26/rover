package com.bnp.kata.rover.reader;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Slf4j
public class FileInputReader implements InputReader {

    @Override
    public List<String> read(String source) throws IOException {

        log.info("Reading mission file [{}]", source);

        List<String> lines = Files.readAllLines(Path.of(source));

        log.info("Successfully read {} line(s) from [{}]",
                lines.size(),
                source);

        return lines;
    }
}
