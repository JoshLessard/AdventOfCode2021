package com.adventofcode2021.dec09;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import com.adventofcode2021.common.Point;

class Basin {

    private final Set<Point> points = new HashSet<>();

    Basin( Point point, Set<Basin> otherBasins ) {
        points.add( point );
        otherBasins.forEach( b -> points.addAll( b.points ) );
    }

    Stream<Point> points() {
        return points.stream();
    }

    int size() {
        return points.size();
    }
}
