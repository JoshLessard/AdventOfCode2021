package com.adventofcode2021.common;

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
}
