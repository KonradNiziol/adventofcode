package pl.niziolkonrad.adventofcode.utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PuzzleInputReader {

    public static List<String> readFrom(final String path)
            throws IOException {
        List<String> result = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();
            while (line != null) {
                result.add(line);
                line = br.readLine();
            }
        }
        return result;
    }
}
