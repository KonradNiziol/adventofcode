package pl.niziolkonrad.adventofcode.year2023.day4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import pl.niziolkonrad.adventofcode.utility.PuzzleInputReader;
import pl.niziolkonrad.adventofcode.year2023.day3.Cell;
import pl.niziolkonrad.adventofcode.year2023.day3.Coordinates;
import pl.niziolkonrad.adventofcode.year2023.day3.EngineSchematic;

public class DayFour {

    public static final String DAY = "day4";

    public static void main(String[] args) throws IOException {
        //System.out.println("First star result: " + firstStar());
        System.out.println("Second star result: " + secondStar());
    }

    private static String firstStar() throws IOException {
        final List<String> inputPuzzle = PuzzleInputReader.readFrom("src/main/resources/year2023/" + DAY + "/firstStar");
        final AtomicInteger i = new AtomicInteger(1);
        final List<Integer> list = inputPuzzle.stream()
                .map(s -> new Card(s, i.getAndIncrement()))
                .map(Card::numberOfWinning)
                .toList();
        long score = 0;
        for (Integer integer : list) {
            if (integer == 1) {
                score += 1;
            } else {
                score += Math.pow(2, integer - 1);
            }
        }
        System.out.println(list);
        return score + "";
    }

    private static long secondStar() throws IOException {
        final List<String> inputPuzzle = PuzzleInputReader.readFrom("src/main/resources/year2023/" + DAY + "/firstStar");
        final AtomicInteger i = new AtomicInteger(1);
        final Map<Card, Long> collectedCards = inputPuzzle.stream()
                .map(s -> new Card(s, i.getAndIncrement()))
                .collect(Collectors.toMap(Function.identity(), o -> 1L));

        for (Map.Entry<Card, Long> cardEntry : collectedCards.entrySet()) {
            for (int j = 0; j < cardEntry.getValue(); j++) {
                final Card card = cardEntry.getKey();
                copyCards(collectedCards, card.numberOfWinning(), card.getCardNumber());
            }
        }


        return collectedCards.values()
                .stream().reduce(0L, Long::sum);
    }

    private static void copyCards(final Map<Card, Long> collectedCards, final int numberOfWin, final long cardNumber) {
        for (int i = 1; i <= numberOfWin; i++) {
            long nextCard = cardNumber + i;
            collectedCards.keySet().stream()
                    .filter(card -> card.getCardNumber() == nextCard)
                    .findFirst()
                    .ifPresent(card -> {
                        final Long numberOfCards = collectedCards.get(card);
                        collectedCards.put(card, numberOfCards + 1);
                    });

        }
    }

}
