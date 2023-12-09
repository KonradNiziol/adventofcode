package pl.niziolkonrad.adventofcode.year2023.day3;

public class EngineSchematic {

    private final String schematic;
    private final String leftSideChar;
    private final String rightSideChar;
    private final String topSideChar;
    private final String bottomSideChar;
    private final String allTheSignsAround;

    public EngineSchematic(String schematic, String leftSideChar, String rightSideChar, String topSideChar,
            String bottomSideChar) {
        this.schematic = schematic;
        this.leftSideChar = leftSideChar;
        this.rightSideChar = rightSideChar;
        this.topSideChar = topSideChar;
        this.bottomSideChar = bottomSideChar;
        this.allTheSignsAround = leftSideChar + rightSideChar + topSideChar + bottomSideChar;
    }

    public boolean isSymbol() {
        final boolean sympol = !allTheSignsAround.replaceAll("\\.", "").isEmpty();
        return sympol;
    }

    public long convertSymbol() {
        return Long.parseLong(schematic);
    }

}
