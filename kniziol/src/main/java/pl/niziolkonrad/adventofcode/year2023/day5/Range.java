package pl.niziolkonrad.adventofcode.year2023.day5;

public class Range {

    private final long startDestination;
    private final long starSource;
    private final long rangeLength;

    public Range(final String line) {
        final String[] split = line.trim().split(" ");
        this.startDestination = Long.parseLong(split[0]);
        this.starSource = Long.parseLong(split[1]);
        this.rangeLength = Long.parseLong(split[2]);
    }

    public Range(long startDestination, long starSource, long rangeLength) {
        this.startDestination = startDestination;
        this.starSource = starSource;
        this.rangeLength = rangeLength;
    }

    public boolean isInRange(final long number) {
        return number >= starSource && number < starSource + rangeLength;
    }

    public boolean isInDestinationRange(final long number) {
        return number >= startDestination && number < startDestination + rangeLength;
    }

    public Long getDestinationNumber(final long number) {
        final long range = number - starSource;
        return startDestination + range;
    }

    public Long getSourceNumber(final long number) {
        final long range = number - startDestination;
        return starSource + range;
    }
}
