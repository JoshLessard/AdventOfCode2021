package com.adventofcode2021.dec17;

import com.adventofcode2021.common.Point;

class TargetArea {

    private final int minX;
    private final int maxX;
    private final int minY;
    private final int maxY;

    TargetArea( int minX, int maxX, int minY, int maxY ) {
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
    }

    boolean contains( Point point ) {
        return containsX( point.x() ) && containsY( point.y() );
    }

    boolean containsX( int x ) {
        return x >= minX && x <= maxX;
    }

    boolean containsY( int y ) {
        return y >= minY && y <= maxY;
    }

    boolean isLeftOf( int x ) {
        return maxX < x;
    }

    boolean isAbove( Point point ) {
        return minY > point.y()
            && minX <= point.x() && point.x() <= maxX;
    }

    boolean isBelow( Point point ) {
        return maxY < point.y()
            && minX <= point.x() && point.x() <= maxX;
    }

    int maxX() {
        return maxX;
    }

    int minY() {
        return minY;
    }
}
