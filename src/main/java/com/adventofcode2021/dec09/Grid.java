package com.adventofcode2021.dec09;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import com.adventofcode2021.common.Point;

class Grid {

    private final int[][] cells;
    private final int width;
    private final int height;

    Grid( int[][] cells ) {
        this.cells = cells;
        this.width = cells[0].length;
        this.height = cells.length;
    }

    int riskLevel() {
        int riskLevel = 0;
        for ( int x = 0; x < width; ++x ) {
            for ( int y = 0; y < height; ++y ) {
                Point currentPoint = new Point( x, y );
                int lowestNeighbour = neighboursOf( currentPoint ).stream().mapToInt( this::valueAt ).min().getAsInt();
                if ( valueAt( currentPoint ) < lowestNeighbour ) {
                    riskLevel += 1 + valueAt( currentPoint );
                }
            }
        }
        return riskLevel;
    }

    Set<Basin> basins() {
        Map<Point, Basin> basinsByPoint = new HashMap<>();
        for ( int x = 0; x < width; ++x ) {
            for ( int y = 0; y < height; ++y ) {
                Point currentPoint = new Point( x, y );
                int currentValue = valueAt( currentPoint );
                if ( currentValue != 9 ) {
                    Set<Basin> neighbouringBasins = neighboursOf( currentPoint )
                        .stream()
                        .filter( n -> valueAt( n ) != currentValue )
                        .filter( basinsByPoint::containsKey )
                        .map( basinsByPoint::get )
                        .collect( toSet() );
                    Basin combined = new Basin( currentPoint, neighbouringBasins );
                    combined.points()
                        .forEach( p -> basinsByPoint.put( p, combined ) );
                }
            }
        }

        return new HashSet<>( basinsByPoint.values() );
    }

    private List<Point> neighboursOf( Point point ) {
        List<Point> neighbours = new ArrayList<>();
        if ( point.x() > 0 ) {
            neighbours.add( point.translateX( -1 ) );
        }
        if ( point.y() > 0 ) {
            neighbours.add( point.translateY( -1 ) );
        }
        if ( point.x() < width - 1 ) {
            neighbours.add( point.translateX( 1 ) );
        }
        if ( point.y() < height - 1 ) {
            neighbours.add( point.translateY( 1 ) );
        }
        return neighbours;
    }

    private int valueAt( Point point ) {
        return cells[point.y()][point.x()];
    }
}
