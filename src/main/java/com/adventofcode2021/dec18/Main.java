package com.adventofcode2021.dec18;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

public class Main {

    public static void main( String[] args ) throws Exception {
        List<String> input = parseInput();
        System.out.println( "Part A: " + magnitudeOfSum( input ) );
        System.out.println( "Part B: " + largestMagnitudeOfAnyPair( input ) );
    }

    private static List<String> parseInput() throws Exception {
        try ( BufferedReader reader = new BufferedReader( new FileReader( "src/main/resources/dec18/input.txt" ) ) ) {
            return reader.lines().collect( toList() );
        }
    }

    private static long magnitudeOfSum( List<String> input ) {
        SnailfishNumberParser parser = new SnailfishNumberParser();
        return input.stream()
            .map( parser::parse )
            .reduce( SnailfishNumber::plus )
            .map( SnailfishNumber::magnitude )
            .orElseThrow();
    }

    private static long largestMagnitudeOfAnyPair( List<String> input ) {
        long largestMagnitude = Long.MIN_VALUE;
        SnailfishNumberParser parser = new SnailfishNumberParser();
        for ( int i = 0; i < input.size(); ++i ) {
            for ( int j = 0; j < input.size(); ++j ) {
                if ( i != j ) {
                    SnailfishNumber number1 = parser.parse( input.get( i ) );
                    SnailfishNumber number2 = parser.parse( input.get( j ) );
                    long magnitudeOfSum = number1.plus( number2 ).magnitude();
                    largestMagnitude = Math.max( largestMagnitude, magnitudeOfSum );
                }
            }
        }

        return largestMagnitude;
    }
}
