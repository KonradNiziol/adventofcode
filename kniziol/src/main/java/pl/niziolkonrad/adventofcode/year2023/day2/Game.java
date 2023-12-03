package pl.niziolkonrad.adventofcode.year2023.day2;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class Game {

    public static final String BLUE_REG_EXP = "[0-9]{1,3} blue";
    public static final String RED_REG_EXP = "[0-9]{1,3} red";
    public static final String GREEN_REG_EXP = "[0-9]{1,3} green";
    private final int gameNumber;
    private final int numberOfGreenCubes;
    private final int numberOfRedCubes;
    private final int numberOfBlueCubes;
    private final long multipliedMinSetOfCubes;
    public Game(final int gameNumber, final String line) {
        this.gameNumber = gameNumber;
        this.numberOfBlueCubes = getMaxNumber(line, BLUE_REG_EXP);
        this.numberOfRedCubes = getMaxNumber(line, RED_REG_EXP);
        this.numberOfGreenCubes = getMaxNumber(line, GREEN_REG_EXP);
        multipliedMinSetOfCubes = (long) numberOfBlueCubes * numberOfGreenCubes * numberOfRedCubes;
    }

    private int getMaxNumber(final String line, final String regExp) {
        final Pattern pattern = Pattern.compile(regExp);
        AtomicInteger maxNumber = new AtomicInteger();
        pattern.matcher(line).results()
                .map(MatchResult::group)
                .map(s -> s.trim().split("\\s")[0])
                .forEach(s -> {
                    if (maxNumber.get() < Integer.parseInt(s)) {
                        maxNumber.set(Integer.parseInt(s));
                    }
                });
        return maxNumber.get();
    }

    boolean containedOnly(final int red, final int blue, final int green) {
        return numberOfRedCubes <= red && numberOfBlueCubes <= blue && numberOfGreenCubes <= green;
    }

    public int getGameNumber() {
        return gameNumber;
    }

    public long getMultipliedMinSetOfCubes() {
        return multipliedMinSetOfCubes;
    }
}
