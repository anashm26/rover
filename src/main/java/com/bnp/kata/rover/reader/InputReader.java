package com.bnp.kata.rover.reader;

import java.io.IOException;
import java.util.List;

public interface InputReader {

    List<String> read(String source) throws IOException;
}
