package com.adventofcode2021.dec01;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main( String[] args ) throws Exception {
        List<Integer> input = getInput();

        System.out.println( "Part 1: the number of increases is " + getNumberOfIncreases( input, 1 ) );
        System.out.println( "Part 2: the number of increases is " + getNumberOfIncreases( input, 3 ) );
    }

    private static int getNumberOfIncreases( List<Integer> values, int windowSize ) {
        int lastWindowValue = Integer.MAX_VALUE;
        int numberOfIncreases = 0;
        for ( int i = 0; i <= values.size() - windowSize; ++i ) {
            int windowValue = getWindowValue( values, i, windowSize );
            if ( windowValue > lastWindowValue ) {
                ++numberOfIncreases;
            }
            lastWindowValue = windowValue;
        }
        return numberOfIncreases;
    }

    private static List<Integer> getInput() throws IOException {
        try ( BufferedReader reader = new BufferedReader( new FileReader( "src/main/resources/dec01/input.txt" ) ) ) {
            return reader.lines()
                .map( Integer::parseInt )
                .collect( toList() );
        }
    }

    private static int getWindowValue( List<Integer> values, int windowStartIndex, int windowSize ) {
        int windowValue = 0;
        for ( int i = windowStartIndex; i < windowStartIndex + windowSize; ++i ) {
            windowValue += values.get( i );
        }
        return windowValue;
    }
}
