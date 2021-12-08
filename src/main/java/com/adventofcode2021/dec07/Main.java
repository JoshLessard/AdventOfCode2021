package com.adventofcode2021.dec07;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main( String[] args ) throws Exception {
        int[] crabCountsByPosition = parseCrabs();

        System.out.println( "Part A: " + getMinFuel( crabCountsByPosition, d -> d ) );
        System.out.println( "Part B: " + getMinFuel( crabCountsByPosition, d -> d * (d + 1) / 2 ) );
    }

    private static int[] parseCrabs() throws IOException {
        List<Integer> crabs = parseInput();
        int max = crabs.stream().max( Integer::compare ).orElseThrow();
        int[] crabCountsByPosition = new int[max + 1];
        crabs.forEach( c -> ++crabCountsByPosition[c] );
        return crabCountsByPosition;
    }

    private static List<Integer> parseInput() throws IOException {
        try ( BufferedReader reader = new BufferedReader( new FileReader( "src/main/resources/dec07/input.txt" ) ) ) {
            return reader.lines()
                .flatMap( l -> Arrays.stream( l.split( "," ) ) )
                .map( Integer::parseInt )
                .collect( toList() );
        }
    }

    private static int getMinFuel( int[] crabCountsByPosition, FuelConsumptionCalculator fuelConsumptionCalculator ) {
        int minFuel = Integer.MAX_VALUE;
        for ( int position = 0; position < crabCountsByPosition.length; ++position ) {
            int fuel = 0;
            for ( int i = 0; i < crabCountsByPosition.length; ++i ) {
                int distanceToPosition = Math.abs( position - i );
                fuel += crabCountsByPosition[i] * fuelConsumptionCalculator.requiredFuelForDistance( distanceToPosition );
            }
            minFuel = Math.min( minFuel, fuel );
        }
        return minFuel;
    }

    @FunctionalInterface
    private interface FuelConsumptionCalculator {
        int requiredFuelForDistance( int distance );
    }
}
