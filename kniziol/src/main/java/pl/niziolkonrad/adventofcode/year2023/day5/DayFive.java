package pl.niziolkonrad.adventofcode.year2023.day5;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import pl.niziolkonrad.adventofcode.utility.PuzzleInputReader;

public class DayFive {

    public static final String DAY = "day5";

    public static void main(String[] args) throws IOException {
        //System.out.println("First star result: " + firstStar());
        //System.out.println("First star result: " + firstStarTest());
        System.out.println("Second star result: " + secondStar());
    }

    private static Long firstStar() throws IOException {
        final List<String> inputPuzzleseedToSoil = PuzzleInputReader.readFrom("src/main/resources/year2023/" + DAY + "/firstStarSeed-toSoil");
        final List<Range> seedToSoil = inputPuzzleseedToSoil.stream().map(Range::new).collect(Collectors.toList());
        seedToSoil.add(new Range(0L, 0L, Long.MAX_VALUE));

        final List<String> inputPuzzleSoilToFertilizer = PuzzleInputReader.readFrom("src/main/resources/year2023/" + DAY + "/firstStarSoilToFertilizer");
        final List<Range> soilToFertilizer = inputPuzzleSoilToFertilizer.stream().map(Range::new).collect(Collectors.toList());
        soilToFertilizer.add(new Range(0L, 0L, Long.MAX_VALUE));

        final List<String> inputPuzzleFertilizerToWater = PuzzleInputReader.readFrom("src/main/resources/year2023/" + DAY + "/firstStarFertilizerToWater");
        final List<Range> fertilizerToWater = inputPuzzleFertilizerToWater.stream().map(Range::new).collect(Collectors.toList());
        fertilizerToWater.add(new Range(0L, 0L, Long.MAX_VALUE));

        final List<String> inputPuzzleWaterToLight = PuzzleInputReader.readFrom("src/main/resources/year2023/" + DAY + "/firstStarWaterToLight");
        final List<Range> waterToLight = inputPuzzleWaterToLight.stream().map(Range::new).collect(Collectors.toList());
        waterToLight.add(new Range(0L, 0L, Long.MAX_VALUE));

        final List<String> inputPuzzleLightToTemperature = PuzzleInputReader.readFrom("src/main/resources/year2023/" + DAY + "/firstStarLightToTemperature");
        final List<Range> lightToTemperature = inputPuzzleLightToTemperature.stream().map(Range::new).collect(Collectors.toList());
        lightToTemperature.add(new Range(0L, 0L, Long.MAX_VALUE));

        final List<String> inputPuzzleTemperatureToHumidity = PuzzleInputReader.readFrom("src/main/resources/year2023/" + DAY + "/firstStarTemperatureToHumidity");
        final List<Range> temperatureToHumidity = inputPuzzleTemperatureToHumidity.stream().map(Range::new).collect(Collectors.toList());
        temperatureToHumidity.add(new Range(0L, 0L, Long.MAX_VALUE));

        final List<String> inputPuzzleHumidityToLocation = PuzzleInputReader.readFrom("src/main/resources/year2023/" + DAY + "/firstStarHumidityToLocation");
        final List<Range> humidityToLocation = inputPuzzleHumidityToLocation.stream().map(Range::new).collect(Collectors.toList());
        humidityToLocation.add(new Range(0L, 0L, Long.MAX_VALUE));

        final List<String> lineSeeds = PuzzleInputReader.readFrom("src/main/resources/year2023/" + DAY + "/firstStarSeeds");
        final List<Long> locations = new ArrayList<>();
        final String seeds = lineSeeds.get(0);
        for (String seed : seeds.split(" ")) {
            final long numberOfSeeds = Long.parseLong(seed);
            final Long soil = getDestinationNumber(seedToSoil, numberOfSeeds);
            final Long fertilizer = getDestinationNumber(soilToFertilizer, soil);
            final Long water = getDestinationNumber(fertilizerToWater, fertilizer);
            final Long light = getDestinationNumber(waterToLight, water);
            final Long temperature = getDestinationNumber(lightToTemperature, light);
            final Long humidity = getDestinationNumber(temperatureToHumidity, temperature);
            final Long location = getDestinationNumber(humidityToLocation, humidity);
            locations.add(location);
        }

        return locations.stream().min(Long::compare).get();
    }

    public static Long getDestinationNumber(final List<Range> ranges, final Long number) {
        return ranges.stream()
                .filter(range -> range.isInRange(number))
                .map(range -> range.getDestinationNumber(number))
                .findFirst()
                .orElseThrow();
    }

    private static Long secondStar() throws IOException {
        final List<String> inputPuzzleseedToSoil = PuzzleInputReader.readFrom("src/main/resources/year2023/" + DAY + "/firstStarSeed-toSoil");
        final List<Range> seedToSoil = inputPuzzleseedToSoil.stream().map(Range::new).collect(Collectors.toList());
        seedToSoil.add(new Range(0L, 0L, Long.MAX_VALUE));

        final List<String> inputPuzzleSoilToFertilizer = PuzzleInputReader.readFrom("src/main/resources/year2023/" + DAY + "/firstStarSoilToFertilizer");
        final List<Range> soilToFertilizer = inputPuzzleSoilToFertilizer.stream().map(Range::new).collect(Collectors.toList());
        soilToFertilizer.add(new Range(0L, 0L, Long.MAX_VALUE));

        final List<String> inputPuzzleFertilizerToWater = PuzzleInputReader.readFrom("src/main/resources/year2023/" + DAY + "/firstStarFertilizerToWater");
        final List<Range> fertilizerToWater = inputPuzzleFertilizerToWater.stream().map(Range::new).collect(Collectors.toList());
        fertilizerToWater.add(new Range(0L, 0L, Long.MAX_VALUE));

        final List<String> inputPuzzleWaterToLight = PuzzleInputReader.readFrom("src/main/resources/year2023/" + DAY + "/firstStarWaterToLight");
        final List<Range> waterToLight = inputPuzzleWaterToLight.stream().map(Range::new).collect(Collectors.toList());
        waterToLight.add(new Range(0L, 0L, Long.MAX_VALUE));

        final List<String> inputPuzzleLightToTemperature = PuzzleInputReader.readFrom("src/main/resources/year2023/" + DAY + "/firstStarLightToTemperature");
        final List<Range> lightToTemperature = inputPuzzleLightToTemperature.stream().map(Range::new).collect(Collectors.toList());
        lightToTemperature.add(new Range(0L, 0L, Long.MAX_VALUE));

        final List<String> inputPuzzleTemperatureToHumidity = PuzzleInputReader.readFrom("src/main/resources/year2023/" + DAY + "/firstStarTemperatureToHumidity");
        final List<Range> temperatureToHumidity = inputPuzzleTemperatureToHumidity.stream().map(Range::new).collect(Collectors.toList());
        temperatureToHumidity.add(new Range(0L, 0L, Long.MAX_VALUE));

        final List<String> inputPuzzleHumidityToLocation = PuzzleInputReader.readFrom("src/main/resources/year2023/" + DAY + "/firstStarHumidityToLocation");
        final List<Range> humidityToLocation = inputPuzzleHumidityToLocation.stream().map(Range::new).collect(Collectors.toList());
        humidityToLocation.add(new Range(0L, 0L, Long.MAX_VALUE));

        final List<String> lineSeeds = PuzzleInputReader.readFrom("src/main/resources/year2023/" + DAY + "/firstStarSeeds");
        final String[] split = lineSeeds.get(0).split(" ");
        final List<Range> seeds = new ArrayList<>();
        for (int i = 0; i < split.length; i = i+2) {
            seeds.add(new Range(Long.parseLong(split[i]), Long.parseLong(split[i]), Long.parseLong(split[i+1])));
        }

        for (long location = 0; location < Long.MAX_VALUE; location++) {
            final Long humidity = getSourceNumber(humidityToLocation, location);
            final Long temperature = getSourceNumber(temperatureToHumidity, humidity);
            final Long light = getSourceNumber(lightToTemperature, temperature);
            final Long water = getSourceNumber(waterToLight, light);
            final Long fertilizer = getSourceNumber(fertilizerToWater, water);
            final Long soil = getSourceNumber(soilToFertilizer, fertilizer);
            final Long seed = getSourceNumber(seedToSoil, soil);

            if (seeds.stream().anyMatch(range -> range.isInRange(seed))) {
                return location;
            }
        }
        return -1L;
    }

    public static Long getSourceNumber(final List<Range> ranges, final Long number) {
        return ranges.stream()
                .filter(range -> range.isInDestinationRange(number))
                .map(range -> range.getSourceNumber(number))
                .findFirst()
                .orElseThrow();
    }
}
