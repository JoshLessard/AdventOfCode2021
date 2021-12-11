package com.adventofcode2021.dec08;

import static java.util.stream.Collectors.toSet;

import java.util.List;
import java.util.Map;
import java.util.Set;

class SignalDecoder {

    private final Map<SignalCharacter, DigitSegment> signalToSegmentMap;

    SignalDecoder( Map<SignalCharacter, DigitSegment> signalToSegmentMap ) {
        this.signalToSegmentMap = Map.copyOf( signalToSegmentMap );
    }

    int decode( List<Signal> signals ) {
        int decoded = 0;
        for ( Signal signal : signals ) {
            Set<DigitSegment> mappedSegments = map( signal );
            Digit digit = Digit.digitWithSegments( mappedSegments );
            decoded *= 10;
            decoded += digit.value();
        }
        return decoded;
    }

    private Set<DigitSegment> map( Signal signal ) {
        return signal.characters()
            .map( signalToSegmentMap::get )
            .collect( toSet() );
    }
}
