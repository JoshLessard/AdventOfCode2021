package com.adventofcode2021.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Point {

    public static final Point ORIGIN = new Point( 0, 0 );

    private final int x;
    private final int y;

    public Point( int x, int y ) {
        this.x = x;
        this.y = y;
    }

    public Point translate( Delta delta ) {
        return new Point( x + delta.x(), y + delta.y() );
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public boolean isHorizontalLineTo( Point to ) {
        return y == to.y;
    }

    public boolean isVerticalLineTo( Point to ) {
        return x == to.x;
    }

    public boolean isDiagonalLineTo( Point to ) {
        return Math.abs( x - to.x ) == Math.abs( y - to.y );
    }

    public List<Point> pointsTo( Point to ) {
        if ( isHorizontalLineTo( to ) ) {
            return horizontalPointsTo( to );
        } else if ( isVerticalLineTo( to ) ) {
            return verticalPointsTo( to );
        } else if ( isDiagonalLineTo( to ) ) {
            return diagonalPointsTo( to );
        } else {
            throw new IllegalArgumentException( "Destination point must be on horizontal, vertical, or diagonal line." );
        }
    }

    private List<Point> horizontalPointsTo( Point to ) {
        List<Point> points = new ArrayList<>();
        for ( int x : coordinatesBetween( x, to.x ) ) {
            points.add( new Point( x, y ) );
        }
        return points;
    }

    private List<Point> verticalPointsTo( Point to ) {
        List<Point> points = new ArrayList<>();
        for ( int y : coordinatesBetween( y, to.y ) ) {
            points.add( new Point( x, y ) );
        }
        return points;
    }

    private List<Point> diagonalPointsTo( Point to ) {
        List<Point> points = new ArrayList<>();
        List<Integer> xCoordinates = coordinatesBetween( x, to.x );
        List<Integer> yCoordinates = coordinatesBetween( y, to.y );
        for ( int i = 0; i < xCoordinates.size(); ++i ) {
            points.add( new Point( xCoordinates.get( i ), yCoordinates.get( i ) ) );
        }
        return points;
    }

    private List<Integer> coordinatesBetween( int from, int to ) {
        List<Integer> coordinates = new ArrayList<>();
        if ( to > from ) {
            for ( int i = from; i <= to; ++i ) {
                coordinates.add( i );
            }
        } else {
            for ( int i = from; i >= to; --i ) {
                coordinates.add( i );
            }
        }

        return coordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public Point translateX( int deltaX ) {
        return new Point( x + deltaX, y );
    }

    public Point translateY( int deltaY ) {
        return new Point( x, y + deltaY );
    }
}
