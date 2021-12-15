package com.adventofcode2021.dec13;

import static java.util.stream.Collectors.toSet;

import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Stream;

import com.adventofcode2021.common.Point;

class Sheet {

    private final SortedMap<Integer, SortedSet<Integer>> dotsByRow = new TreeMap<>();
    private final SortedMap<Integer, SortedSet<Integer>> dotsByColumn = new TreeMap<>();

    void addDot( Point point ) {
        dotsByRow.computeIfAbsent( point.y(), r -> new TreeSet<>() ).add( point.x() );
        dotsByColumn.computeIfAbsent( point.x(), c -> new TreeSet<>() ).add( point.y() );
    }

    void foldLeft( int column ) {
        Set<Point> pointsToFold = dotsByColumn.keySet().stream()
            .filter( c -> c > column )
            .flatMap( this::pointsInColumn )
            .collect( toSet() );
        pointsToFold.forEach( point -> foldLeft( point, column ) );
    }

    private Stream<Point> pointsInColumn( int column ) {
        return dotsByColumn.get( column ).stream()
            .map( row -> new Point( column, row ) );
    }

    private void foldLeft( Point pointToFold, int column ) {
        Point foldedPoint = pointToFold.translateX( -2 * (pointToFold.x() - column) );
        removeDot( pointToFold );
        addDot( foldedPoint );
    }

    private void removeDot( Point point ) {
        dotsByRow.get( point.y() ).remove( point.x() );
        if ( dotsByRow.get( point.y() ).isEmpty() ) {
            dotsByRow.remove( point.y() );
        }
        dotsByColumn.get( point.x() ).remove( point.y() );
        if ( dotsByColumn.get( point.x() ).isEmpty() ) {
            dotsByColumn.remove( point.x() );
        }
    }

    public void foldUp( int row ) {
        Set<Point> pointsToFold = dotsByRow.keySet().stream()
            .filter( r -> r > row )
            .flatMap( this::pointsInRow )
            .collect( toSet() );
        pointsToFold.forEach( point -> foldUp( point, row ) );
    }

    private Stream<Point> pointsInRow( int row ) {
        return dotsByRow.get( row ).stream()
            .map( column -> new Point( column, row ) );
    }

    private void foldUp( Point pointToFold, int row ) {
        Point foldedPoint = pointToFold.translateY( -2 * (pointToFold.y() - row) );
        removeDot( pointToFold );
        addDot( foldedPoint );
    }

    int dotCount() {
        return dotsByRow.values().stream()
            .mapToInt( Set::size )
            .sum();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int currentRow = 0;
        for ( int row : dotsByRow.keySet() ) {
            builder.append( "\n".repeat( row - currentRow ) );
            currentRow = row;
            int currentColumn = 0;
            for ( int column : dotsByRow.get( row ) ) {
                builder.append( " ".repeat( column - currentColumn ) );
                builder.append( '#' );
                currentColumn = column + 1;
            }
        }
        return builder.toString();
    }
}
