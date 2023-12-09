package pl.niziolkonrad.adventofcode.year2023.day3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import pl.niziolkonrad.adventofcode.utility.PuzzleInputReader;
import pl.niziolkonrad.adventofcode.year2023.day2.Game;

public class DayThree {

    public static final String DAY = "day3";

    public static void main(String[] args) throws IOException {
        //System.out.println("First star result: " + firstStar());
        System.out.println("Second star result: " + secondStar());
    }

    private static String firstStar() throws IOException {
        final List<String> inputPuzzle = PuzzleInputReader.readFrom("src/main/resources/year2023/" + DAY + "/firstStar");
        final Pattern pattern = Pattern.compile("\\d+");
        final List<EngineSchematic> schematics = new ArrayList<>();

        for (int i = 0; i < inputPuzzle.size(); i++) {
            final String line = inputPuzzle.get(i);
            final List<String> results = pattern.matcher(line)
                    .results()
                    .map(MatchResult::group)
                    .toList();

            String copyOfLine = line;
            for (String result : results) {
                schematics.add(create(result, copyOfLine, getTopLine(inputPuzzle, i), getBottomLine(inputPuzzle, i)));
                copyOfLine = copyOfLine.replaceFirst(result, ".".repeat(result.length()));
            }
        }
        return String.valueOf(schematics.stream()
                .filter(EngineSchematic::isSymbol)
                .map(EngineSchematic::convertSymbol)
                .reduce(0L, Long::sum));
    }

    private static String getTopLine(List<String> inputPuzzle, int i) {
        return i > 0 ? inputPuzzle.get(i - 1) : "";
    }

    private static String getBottomLine(List<String> inputPuzzle, int i) {
        return i < inputPuzzle.size() - 1 ? inputPuzzle.get(i + 1) : "";
    }

    private static EngineSchematic create(final String schematic, final String line, final String topLine, final String bottomLine) {
        final int indexOf = line.indexOf(schematic);
        final int schematicLength = schematic.length();
        final String leftSideChar = getChars(line, indexOf - 1, indexOf);
        final String rightSideChar = getChars(line, indexOf + schematicLength, indexOf + schematicLength + 1);


        String topChars = "";
        String bottomChars = "";
        if (indexOf == 0) {
            topChars = topLine.isBlank() ? "" : topLine.substring(indexOf, indexOf + schematicLength + 1);
            bottomChars = bottomLine.isBlank() ? "" : bottomLine.substring(indexOf, indexOf + schematicLength + 1);

        } else if (indexOf + schematicLength + 1 >= line.length()) {
            topChars = topLine.isBlank() ? "" : topLine.substring(indexOf - 1);
            bottomChars = bottomLine.isBlank() ? "" : bottomLine.substring(indexOf - 1);
        } else {
            topChars = topLine.isBlank() ? "" : topLine.substring(indexOf - 1, indexOf + schematicLength + 1);
            bottomChars = bottomLine.isBlank() ? "" : bottomLine.substring(indexOf - 1, indexOf + schematicLength + 1);
        }

        return new EngineSchematic(schematic, leftSideChar, rightSideChar, topChars, bottomChars);
    }

    private static String getChars(String line, int startIndex, int endIndex) {
        String result;
        try {
            result = line.substring(startIndex, endIndex);
        } catch (final IndexOutOfBoundsException e) {
            result = "";
        }
        return result;
    }

    private static String secondStar() throws IOException {
        final List<String> inputPuzzle = PuzzleInputReader.readFrom("src/main/resources/year2023/" + DAY + "/firstStar");
        final List<Cell> cells = new ArrayList<>();
        for (int y = 0; y < inputPuzzle.size(); y++) {
            final String line = inputPuzzle.get(y);
            final char[] charArray = line.toCharArray();
            for (int x = 0; x < charArray.length; x++) {
                final char value = charArray[x];
                if (value != 46) {
                    cells.add(new Cell(x, y, value));
                }
            }
        }

        final List<Long> result = new ArrayList<>();
        for (Cell cell : cells) {
            if (cell.getValue() == '*') {
                final List<Coordinates> allAround = cell.getAllAround();
                final int x = cell.getX();
                final int y = cell.getY();
                System.out.println(y + " " + x + "->" + cell.getValue());
                final List<Cell> cellsAround = cells.stream()
                        .filter(c -> allAround.contains(c.getCoordinates()))
                        .collect(Collectors.toList());
                final Iterator<Cell> iterator = cellsAround.iterator();
                while (iterator.hasNext()) {
                    final Cell next = iterator.next();
                    if (cellsAround.stream().anyMatch(cell1 -> cell1.getCoordinates().equals(new Coordinates(next.getX()+1,
                            next.getY())))) {
                        iterator.remove();
                    }
                    if (cellsAround.stream().anyMatch(cell1 -> cell1.getCoordinates().equals(new Coordinates(next.getX()-1,
                            next.getY())))) {
                        iterator.remove();
                    }
                }

                List<Long> numbers = new ArrayList<>();
                for (Cell foundNumber : cellsAround) {
                    final long number = foundNumber.findNumber(cells);
                    System.out.println(number);
                    numbers.add(number);
                }
                if (numbers.size() == 1) {
                    System.out.println(numbers.get(0));
                } else if (numbers.size() >= 2) {
                    Set<Long> withoutDuplicates = new HashSet<>(numbers);
                    if (withoutDuplicates.size() == 1) {
                        System.out.println(withoutDuplicates);
                    } else {
                        result.add(withoutDuplicates.stream().reduce(1L, (aLong, aLong2) -> aLong * aLong2));
                    }
                }
            }
        }

        return String.valueOf(result.stream().reduce(0L, Long::sum));
    }

}
