package com.adventofcode2021.dec12;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static final Pattern EDGE_PATTERN = Pattern.compile( "([a-zA-Z]+)-([a-zA-Z]+)" );

    public static void main( String[] args ) throws Exception {
        CaveGraph graph = parseCaveGraph();
        Cave startCave = Cave.from( "start" );
        Cave endCave = Cave.from( "end" );
        System.out.println( "Part A: " + graph.numberOfPaths( startCave, endCave, new PartACaveVisitTracker() ) );
        System.out.println( "Part B: " + graph.numberOfPaths( startCave, endCave, new PartBCaveVisitTracker( startCave, endCave ) ) );
    }

    private static CaveGraph parseCaveGraph() throws Exception {
        CaveGraph graph = new CaveGraph();
        for ( String line : parseInput() ) {
            Matcher matcher = EDGE_PATTERN.matcher( line );
            if ( matcher.matches() ) {
                Cave from = Cave.from( matcher.group( 1 ) );
                Cave to = Cave.from( matcher.group( 2 ) );
                graph.connectCaves( from, to );
            }
        }
        return graph;
    }

    private static List<String> parseInput() throws Exception {
        try ( BufferedReader reader = new BufferedReader( new FileReader( "src/main/resources/dec12/input.txt" ) ) ) {
            return reader.lines().collect( toList() );
        }
    }
}
