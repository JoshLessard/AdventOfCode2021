package com.adventofcode2021.dec08;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main( String[] args ) throws Exception {
        List<SignalInput> signalInputs = parseSignalInputs();
        System.out.println( "Part A: " + countEasyDigits( signalInputs ) );
        System.out.println( "Part B: " + decode( signalInputs ) );
    }

    private static List<SignalInput> parseSignalInputs() throws IOException {
        try ( BufferedReader reader = new BufferedReader( new FileReader( "src/main/resources/dec08/input.txt" ) ) ) {
            return reader.lines()
                .map( l -> l.split( "\\|" ) )
                .map( l -> new SignalInput( parseSignalPatterns( l[0] ), parseOutputValues( l[1] ) ) )
                .collect( toList() );
        }
    }

    private static List<Signal> parseSignalPatterns(String string ) {
        return Arrays.stream( string.trim().split( "\\s+" ) )
            .map( Main::toSignal )
            .collect( toList() );
    }

    private static Signal toSignal( String signalAsString ) {
        Set<SignalCharacter> characters = signalAsString.chars()
            .mapToObj( SignalCharacter::parse )
            .collect( toSet() );
        return new Signal( characters );
    }

    private static List<Signal> parseOutputValues( String string ) {
        return Arrays.stream( string.trim().split( "\\s+" ) )
            .map( Main::toSignal )
            .map( Signal::new )
            .collect( toList() );
    }

    private static int countEasyDigits( List<SignalInput> signalInputs ) {
        return (int) signalInputs.stream()
            .flatMap( s -> s.outputValues().stream() )
            .filter( s -> Digit.isUniqueSegmentCount( s.length() ) )
            .count();
    }

    private static int decode( List<SignalInput> signalInputs ) {
        int total = 0;
        SignalMapper signalMapper = new SignalMapper();
        for ( SignalInput signalInput : signalInputs ) {
            SignalDecoder decoder = signalMapper.decoderFor( signalInput.signalPatterns() );
            total += decoder.decode( signalInput.outputValues() );
        }
        return total;
    }
}