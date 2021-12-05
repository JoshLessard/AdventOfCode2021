package com.adventofcode2021.dec03;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;

class DiagnosticReport {

    private final List<Integer> diagnostics;
    private final int bitsPerDiagnostic;

    DiagnosticReport( List<Integer> diagnostics, int bitsPerDiagnostic ) {
        this.diagnostics = List.copyOf( diagnostics );
        this.bitsPerDiagnostic = bitsPerDiagnostic;
    }


    int gammaRate() {
        int gammaRate = 0;
        for ( int i = 0; i < bitsPerDiagnostic; ++i ) {
            gammaRate *= 2;
            gammaRate += mostCommonBit( i, diagnostics );
        }
        return gammaRate;
    }

    private int mostCommonBit( int index, List<Integer> diagnostics ) {
        int bitSum = 0;
        for ( int diagnostic : diagnostics ) {
            bitSum += bitAt( index, diagnostic );
        }
        return bitSum >= ( (double) diagnostics.size() / 2 ) ? 1 : 0;
    }

    private int bitAt( int index, int diagnostic ) {
        for ( int i = index; i < bitsPerDiagnostic - 1; ++i ) {
            diagnostic /= 2;
        }
        return diagnostic & 1;
    }

    int epsilonRate() {
        int epsilonRate = 0;
        for ( int i = 0; i < bitsPerDiagnostic; ++i ) {
            epsilonRate *= 2;
            epsilonRate += leastCommonBit( i, diagnostics );
        }
        return epsilonRate;
    }

    private int leastCommonBit( int index, List<Integer> diagnostics ) {
        int bitSum = 0;
        for ( int diagnostic : diagnostics ) {
            bitSum += bitAt( index, diagnostic );
        }
        return bitSum >= ( (double) diagnostics.size() / 2 ) ? 0 : 1;
    }

    int oxygenGeneratorRating() {
        List<Integer> diagnostics = new ArrayList<>( this.diagnostics );
        for ( int index = 0; ; ++index ) {
            int mostCommonBit = mostCommonBit( index, diagnostics );
            diagnostics = filter( diagnostics, index, mostCommonBit );
            if ( diagnostics.size() == 1 ) {
                return diagnostics.get( 0 );
            }
        }
    }

    private List<Integer> filter( List<Integer> diagnostics, int index, int requiredBit ) {
        return diagnostics.stream()
            .filter( d -> bitAt( index, d ) == requiredBit )
            .collect( toList() );
    }

    int co2ScrubberRating() {
        List<Integer> diagnostics = new ArrayList<>( this.diagnostics );
        for ( int index = 0; ; ++index ) {
            int leastCommonBit = leastCommonBit( index, diagnostics );
            diagnostics = filter( diagnostics, index, leastCommonBit );
            if ( diagnostics.size() == 1 ) {
                return diagnostics.get( 0 );
            }
        }
    }
}
