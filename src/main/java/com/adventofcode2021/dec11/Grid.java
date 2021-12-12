package com.adventofcode2021.dec11;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import com.adventofcode2021.common.Point;

class Grid {

    private final int[][] cells;
    private final int width;
    private final int height;

    private int flashCount = 0;

    Grid( int[][] cells ) {
        this.cells = cells;
        this.width = cells[0].length;
        this.height = cells.length;
    }

    boolean step() {
        Set<Point> flashedPoints = new HashSet<>();
        incrementEnergyLevels();
        Queue<Point> pointsToFlash = getPointsToFlash();
        while ( ! pointsToFlash.isEmpty() ) {
            Point pointToFlash = pointsToFlash.poll();
            if ( ! flashedPoints.contains( pointToFlash ) ) {
                for ( Point neighbour : getNeighbours( pointToFlash ) ) {
                    incrementEnergyLevel( neighbour );
                    if ( shouldFlash( neighbour ) ) {
                        pointsToFlash.add( neighbour );
                    }
                }
                flashedPoints.add( pointToFlash );
            }
        }
        flashCount += flashedPoints.size();
        flashedPoints.forEach( this::resetEnergyLevel );
        return flashedPoints.size() == width * height;
    }

    private List<Point> getNeighbours( Point point ) {
        List<Point> neighbours = new ArrayList<>();
        for ( int x = point.x() - 1; x <= point.x() + 1; ++x ) {
            for ( int y = point.y() - 1; y <= point.y() + 1; ++y ) {
                Point neighbour = new Point( x, y );
                if ( ! neighbour.equals( point ) && isInBounds( neighbour ) ) {
                    neighbours.add( neighbour );
                }
            }
        }
        return neighbours;
    }

    private boolean isInBounds( Point point ) {
        return point.x() >= 0
            && point.x() < width
            && point.y() >= 0
            && point.y() < height;
    }

    private Queue<Point> getPointsToFlash() {
        Queue<Point> points = new LinkedList<>();
        for ( int x = 0; x < width; ++x ) {
            for ( int y = 0; y < height; ++y ) {
                Point point = new Point( x, y );
                if ( shouldFlash( point ) ) {
                    points.add( point );
                }
            }
        }
        return points;
    }

    private void incrementEnergyLevels() {
        for ( int x = 0; x < width; ++x ) {
            for ( int y = 0; y < height; ++y ) {
                incrementEnergyLevel( new Point( x, y ) );
            }
        }
    }

    private void incrementEnergyLevel( Point point ) {
        ++cells[point.y()][point.x()];
    }

    private void resetEnergyLevel( Point point ) {
        cells[point.y()][point.x()] = 0;
    }

    private boolean shouldFlash( Point point ) {
        return valueAt( point ) > 9;
    }

    private int valueAt( Point point ) {
        return cells[point.y()][point.x()];
    }

    int flashCount() {
        return flashCount;
    }
}
