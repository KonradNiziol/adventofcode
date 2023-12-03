package pl.niziolkonrad.adventofcode.year2023.day2;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import pl.niziolkonrad.adventofcode.utility.PuzzleInputReader;

public class DayTwo {

    public static final String DAY = "day2";

    public static void main(String[] args) throws IOException {
        System.out.println("First star result: " + firstStar());
        System.out.println("Second star result: " + secondStar());
    }

    private static String firstStar() throws IOException {
        final List<String> inputPuzzle = PuzzleInputReader.readFrom("src/main/resources/year2023/" + DAY + "/firstStar");
        final int redCubes = 12;
        final int greenCubes = 13;
        final int blueCubes = 14;
        AtomicInteger count = new AtomicInteger(1);
        final Integer sumOfTheIDs = inputPuzzle.stream()
                .map(line -> new Game(count.getAndIncrement(), line))
                .filter(game -> game.containedOnly(redCubes, blueCubes, greenCubes))
                .reduce(0, (integer, game) -> integer + game.getGameNumber(), Integer::sum);

        return String.valueOf(sumOfTheIDs);
    }


    private static String secondStar() throws IOException {
        final List<String> inputPuzzle = PuzzleInputReader.readFrom("src/main/resources/year2023/" + DAY + "/firstStar");
        AtomicInteger count = new AtomicInteger(1);
        final Long sumOfTheIDs = inputPuzzle.stream()
                .map(line -> new Game(count.getAndIncrement(), line))
                .reduce(0L, (integer, game) -> integer + game.getMultipliedMinSetOfCubes(), Long::sum);

        return String.valueOf(sumOfTheIDs);
    }

}
