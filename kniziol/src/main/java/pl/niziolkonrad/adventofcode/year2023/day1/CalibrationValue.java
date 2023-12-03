package pl.niziolkonrad.adventofcode.year2023.day1;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

class CalibrationValue {

    private final static List<String> DIGITS = Arrays.asList("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine");
    private final String calibrationValue;

    CalibrationValue(final String calibrationValue) {
        this.calibrationValue = calibrationValue.toLowerCase();
    }

    CalibrationValue replaceDigitsSpelledOutWithLettersToDigitsFromTheBeginning() {
        String result = "";
        for (char c : calibrationValue.toCharArray()) {
            result += String.valueOf(c);
            for (int i = 0; i < DIGITS.size(); i++) {
                result = result.replaceAll(DIGITS.get(i), String.valueOf(i));
            }
        }
        return new CalibrationValue(result);
    }

    CalibrationValue replaceDigitsSpelledOutWithLettersToDigitsFromTheEnd() {
        String result = "";
        final char[] charArray = calibrationValue.toCharArray();
        for (int i = charArray.length - 1; i >= 0; i--) {
            final char c = charArray[i];
            result = c + result;
            for (int j = 0; j < DIGITS.size(); j++) {
                result = result.replaceAll(DIGITS.get(j), String.valueOf(j));
            }
        }
        return new CalibrationValue(result);
    }

    String getFirstDigit() {
        for (char c : calibrationValue.toCharArray()) {
            if (Character.isDigit(c)) {
                return String.valueOf(c);
            }
        }
        return "";
    }

    String getLastDigit() {
        final char[] charArray = calibrationValue.toCharArray();
        for (int i = charArray.length - 1; i >= 0; i--) {
            final char c = charArray[i];
            if (Character.isDigit(c)) {
                return String.valueOf(c);
            }
        }
        return "";
    }

    String getFirstDigitSecondStar() {
        return replaceDigitsSpelledOutWithLettersToDigitsFromTheBeginning().getFirstDigit();
    }

    String getLastDigitSecondStar() {
        return replaceDigitsSpelledOutWithLettersToDigitsFromTheEnd().getLastDigit();
    }

    @Override
    public String toString() {
        return calibrationValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CalibrationValue that = (CalibrationValue) o;
        return Objects.equals(calibrationValue, that.calibrationValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(calibrationValue);
    }
}
