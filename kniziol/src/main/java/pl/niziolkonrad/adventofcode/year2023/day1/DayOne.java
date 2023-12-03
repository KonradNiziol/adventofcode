package pl.niziolkonrad.adventofcode.year2023.day1;

import java.io.IOException;
import java.util.List;

import pl.niziolkonrad.adventofcode.utility.PuzzleInputReader;

public class DayOne {

    public static void main(String[] args) throws IOException {
        System.out.println("First star result: " + firstStar());
        System.out.println("Second star result: " + secondStar());
    }

    private static String firstStar() throws IOException {
        final List<String> inputPuzzle = PuzzleInputReader.readFrom("src/main/resources/year2023/day1/firstStar");
        final Integer sumOfAllOfTheCalibrationValues = inputPuzzle.stream()
                .map(CalibrationValue::new)
                .map(DayOne::getIntValue)
                .reduce(0, Integer::sum);

        return String.valueOf(sumOfAllOfTheCalibrationValues);
    }

    private static Integer getIntValue(final CalibrationValue calibrationValue) {
        final String s = calibrationValue.getFirstDigit() + calibrationValue.getLastDigit();
        return Integer.valueOf(s);
    }

    private static String secondStar() throws IOException {
        final List<String> inputPuzzle = PuzzleInputReader.readFrom("src/main/resources/year2023/day1/firstStar");
        final Integer sumOfAllOfTheCalibrationValues = inputPuzzle.stream()
                .map(CalibrationValue::new)
                .map(DayOne::getIntValueSecondStar)
                .reduce(0, Integer::sum);

        return String.valueOf(sumOfAllOfTheCalibrationValues);
    }

    private static Integer getIntValueSecondStar(final CalibrationValue calibrationValue) {
        final String s = calibrationValue.getFirstDigitSecondStar() + calibrationValue.getLastDigitSecondStar();
        return Integer.valueOf(s);
    }

}
