package com.adventofcode2021.dec14;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static final Pattern PAIR_INSERTION_PATTERN = Pattern.compile( "([A-Z]{2}) -> ([A-Z])" );

    public static void main( String[] args ) throws Exception {
        Polymerizer polymerizer = parsePolymerizer();
        for ( int i = 0; i < 10; ++i ) {
            polymerizer.doPairInsertion();
        }
        System.out.println( "Part A: " + (polymerizer.maxQuantity() - polymerizer.minQuantity()) );

        for ( int i = 0; i < 30; ++i ) {
            polymerizer.doPairInsertion();
        }
        System.out.println( "Part B: " + (polymerizer.maxQuantity() - polymerizer.minQuantity()) );
    }

    private static Polymerizer parsePolymerizer() throws Exception {
        List<String> input = parseInput();
        String polymerTemplate = input.get( 0 );
        Map<String, Character> pairInsertionMap = parsePairInsertionMap( input );
        return new Polymerizer( pairInsertionMap, polymerTemplate );
    }

    private static List<String> parseInput() throws Exception {
        try ( BufferedReader reader = new BufferedReader( new FileReader( "src/main/resources/dec14/input.txt" ) ) ) {
            return reader.lines().collect( toList() );
        }
    }

    private static Map<String, Character> parsePairInsertionMap( List<String> input ) {
        return input.stream()
            .map( PAIR_INSERTION_PATTERN::matcher )
            .filter( Matcher::matches )
            .collect( toMap( m -> m.group( 1 ), m -> m.group( 2 ).charAt( 0 ) ) );
    }
}
