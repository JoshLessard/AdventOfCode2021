package com.adventofcode2021.dec03;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

public class Main {

    public static void main( String[] args ) throws Exception {
        DiagnosticReport report = buildDiagnosticReport();

        System.out.println( "Part A: " + ( report.gammaRate() * report.epsilonRate() ));
        System.out.println( "Part B: " + ( report.oxygenGeneratorRating() * report.co2ScrubberRating() ) );
    }

    private static DiagnosticReport buildDiagnosticReport() throws Exception {
        List<String> diagnosticsAsString = parseInput();
        int bitsPerDiagnostic = diagnosticsAsString.get( 0 ).length();
        List<Integer> diagnostics = diagnosticsAsString.stream().map( d -> Integer.parseInt( d, 2 ) ).collect( toList() );
        return new DiagnosticReport( diagnostics, bitsPerDiagnostic );
    }

    private static List<String> parseInput() throws Exception {
        try ( BufferedReader reader = new BufferedReader( new FileReader( "src/main/resources/dec03/input.txt" ) ) ) {
            return reader.lines().collect( toList() );
        }
    }
}
