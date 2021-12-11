package com.adventofcode2021.dec08;


import static java.util.stream.Collectors.toList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

class SignalToSegmentPossibility {

    private Signal signal;
    private List<Set<DigitSegment>> possibilities;

    SignalToSegmentPossibility( Signal signal, List<Set<DigitSegment>> possibilities ) {
        this.signal = new Signal( signal );
        this.possibilities = possibilities.stream().map( HashSet::new ).collect( toList() );
    }

    boolean hasSinglePossibility() {
        return possibilities.size() == 1;
    }

    void remove( SignalToSegmentPossibility possibility ) {
        if ( ! possibility.hasSinglePossibility() ) {
            throw new IllegalArgumentException( "Can only remove single possibilities." );
        }
        Set<DigitSegment> segmentsToRemove = possibility.possibilities.get( 0 );
        if ( signal.contains( possibility.signal ) ) {
            signal.remove( possibility.signal );
            possibilities = possibilities.stream()
                .filter( p -> p.containsAll( segmentsToRemove ) )
                .peek( p -> p.removeAll( segmentsToRemove ) )
                .collect( toList() );
        }
    }

    boolean hasSingleSignalCharacter() {
        return signal.length() == 1;
    }

    SignalCharacter singleSignalCharacter() {
        return signal.first();
    }

    DigitSegment singlePossibilityCharacter() {
        return possibilities.get( 0 ).iterator().next();
    }

    boolean isEmpty() {
        return signal.isEmpty();
    }
}