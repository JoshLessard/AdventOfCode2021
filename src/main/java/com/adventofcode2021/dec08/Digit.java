package com.adventofcode2021.dec08;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

enum Digit {

    ZERO( 0, DigitSegment.A, DigitSegment.B, DigitSegment.C, DigitSegment.E, DigitSegment.F, DigitSegment.G ),
    ONE( 1, DigitSegment.C, DigitSegment.F ),
    TWO( 2, DigitSegment.A, DigitSegment.C, DigitSegment.D, DigitSegment.E, DigitSegment.G ),
    THREE( 3, DigitSegment.A, DigitSegment.C, DigitSegment.D, DigitSegment.F, DigitSegment.G ),
    FOUR( 4, DigitSegment.B, DigitSegment.C, DigitSegment.D, DigitSegment.F ),
    FIVE( 5, DigitSegment.A, DigitSegment.B, DigitSegment.D, DigitSegment.F, DigitSegment.G ),
    SIX( 6, DigitSegment.A, DigitSegment.B, DigitSegment.D, DigitSegment.E, DigitSegment.F, DigitSegment.G ),
    SEVEN( 7, DigitSegment.A, DigitSegment.C, DigitSegment.F ),
    EIGHT( 8, DigitSegment.A, DigitSegment.B, DigitSegment.C, DigitSegment.D, DigitSegment.E, DigitSegment.F, DigitSegment.G ),
    NINE( 9, DigitSegment.A, DigitSegment.B, DigitSegment.C, DigitSegment.D, DigitSegment.F, DigitSegment.G );

    private final int value;
    private final Set<DigitSegment> segments;

    Digit( int value, DigitSegment... segments ) {
        this.value = value;
        this.segments = Set.of( segments );
    }

    static boolean isUniqueSegmentCount( int segmentCount ) {
        Set<Digit> digitsWithSegmentCount = digitsWithSegmentCount( segmentCount );
        return digitsWithSegmentCount.size() == 1L;
    }

    static Set<Digit> digitsWithSegmentCount(int segmentCount) {
        return Arrays.stream( values() )
            .filter( d -> d.segmentCount() == segmentCount )
            .collect( toSet() );
    }

    static Digit digitWithSegments( Set<DigitSegment> segments ) {
        List<Digit> digitsWithSegments = Arrays.stream( values() )
            .filter( d -> d.segments.equals( segments ) )
            .collect( toList() );
        if ( digitsWithSegments.size() != 1 ) {
            throw new IllegalArgumentException( "Provided segments do not correspond to unique digit." );
        } else {
            return digitsWithSegments.get( 0 );
        }
    }

    private int segmentCount() {
        return segments.size();
    }

    Set<DigitSegment> segments() {
        return segments;
    }

    int value() {
        return value;
    }
}
