package com.adventofcode2021.dec05;

import java.util.HashMap;
import java.util.Map;

import com.adventofcode2021.common.Point;

class VentField {

    private final Map<Point, Integer> ventsByPoint = new HashMap<>();

    void addVentLine( Point from, Point to ) {
        from.pointsTo( to ).forEach( this::addVent );
    }

    private void addVent( Point point ) {
        int numberOfVentsAtPoint = ventsByPoint.computeIfAbsent( point, p -> 0 );
        ventsByPoint.put( point, numberOfVentsAtPoint + 1 );
    }

    int overlapCount() {
        return (int) ventsByPoint.values().stream()
            .filter( c -> c >= 2 )
            .mapToInt( c -> c )
            .count();
    }
}
