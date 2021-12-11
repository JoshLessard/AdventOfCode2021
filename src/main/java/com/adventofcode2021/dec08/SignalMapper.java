package com.adventofcode2021.dec08;

import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class SignalMapper {

    SignalDecoder decoderFor( List<Signal> signalPatterns ) {
        Map<SignalCharacter, DigitSegment> signalToSegmentMap = new HashMap<>();
        List<SignalToSegmentPossibility> possibilities = getPossibilities( signalPatterns );
        while ( ! possibilities.isEmpty() ) {
            SignalToSegmentPossibility possibilityUnderConsideration = possibilities.remove( 0 );
            if ( possibilityUnderConsideration.isEmpty() ) {
                continue;
            }
            if ( possibilityUnderConsideration.hasSinglePossibility() ) {
                for ( SignalToSegmentPossibility possibility : possibilities ) {
                    possibility.remove( possibilityUnderConsideration );
                }
                if ( possibilityUnderConsideration.hasSingleSignalCharacter() ) {
                    signalToSegmentMap.put(
                        possibilityUnderConsideration.singleSignalCharacter(),
                        possibilityUnderConsideration.singlePossibilityCharacter()
                    );
                } else {
                    possibilities.add( possibilityUnderConsideration );
                }
            } else {
                possibilities.add( possibilityUnderConsideration );
            }
        }

        return new SignalDecoder( signalToSegmentMap );
    }

    private List<SignalToSegmentPossibility> getPossibilities( List<Signal> signalPatterns ) {
        return signalPatterns.stream()
            .map( p -> new SignalToSegmentPossibility( p, Digit.digitsWithSegmentCount( p.length() ).stream().map( Digit::segments ).collect( toList() ) ) )
            .collect( toCollection( LinkedList::new ) );
    }
}
