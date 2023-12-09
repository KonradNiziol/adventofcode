package pl.niziolkonrad.adventofcode.year2023.day3;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Cell {

    private final Coordinates coordinates;
    private char value;

    public Cell(int x, int y, char value) {
        this.coordinates = new Coordinates(x, y);
        this.value = value;
    }

    public List<Coordinates> getAllAround() {
        final List<Coordinates> result = new ArrayList<>();
        result.add(new Coordinates(getX() - 1, getY()));
        result.add(new Coordinates(getX() + 1, getY()));
        result.add(new Coordinates(getX() + 1, getY() + 1));
        result.add(new Coordinates(getX() - 1, getY() + 1));
        result.add(new Coordinates(getX(), getY() + 1));
        result.add(new Coordinates(getX() + 1, getY() - 1));
        result.add(new Coordinates(getX() - 1, getY() - 1));
        result.add(new Coordinates(getX(), getY() - 1));
        return result;
    }

    public long findNumber(final List<Cell> cells) {
        List<String> result = new ArrayList<>();
        result.add(String.valueOf(value));
        final List<String> leftSide = numberFinder(cells, result, -1, true);
        final List<String> rightSide = numberFinder(cells, new ArrayList<>(), 1, false);

        final String left = leftSide.stream().reduce("", String::concat);
        final String right = rightSide.stream().reduce("", String::concat);

        return Long.parseLong(left + right);
    }
    private List<String> numberFinder(final List<Cell> cells, final List<String> values, int i, boolean left) {
        final int newX = getX() + i;
        final Optional<Cell> first = cells.stream()
                .filter(cell -> cell.coordinates.equals(new Coordinates(newX, getY())))
                .findFirst();

        if (first.isPresent() && Character.isDigit(first.get().value)) {
            if (left) {
                values.add(0, String.valueOf(first.get().value));
                i--;
            } else {
                values.add(String.valueOf(first.get().value));
                i++;
            }
            return numberFinder(cells, values, i, left);
        } else {
            return values;
        }
    }

    public int getX() {
        return coordinates.getX();
    }

    public int getY() {
        return coordinates.getY();
    }

    public char getValue() {
        return value;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cell cell = (Cell) o;
        return Objects.equals(coordinates, cell.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinates);
    }
}
