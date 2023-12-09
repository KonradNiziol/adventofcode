package pl.niziolkonrad.adventofcode.year2023.day4;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Card {

    public static final String NAME_REGEXP = "(Card\\s+[0-9]+:)";
    private final long cardNumber;
    private List<Long> winningNumbers;
    private List<Long> numbers;
    private long points;

    public Card(final String line, final long cardNumber) {
        this.cardNumber = cardNumber;
        final String[] split = line.replaceFirst(NAME_REGEXP, "").trim().split("\\|");
        winningNumbers = Arrays.stream(split[0].trim().split("\\s+")).map(Long::valueOf).collect(Collectors.toList());
        numbers = Arrays.stream(split[1].trim().split("\\s+")).map(Long::valueOf).collect(Collectors.toList());
        points = numberOfWinning();
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public Integer numberOfWinning() {
        numbers.retainAll(winningNumbers);
        return numbers.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return cardNumber == card.cardNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber);
    }
}
