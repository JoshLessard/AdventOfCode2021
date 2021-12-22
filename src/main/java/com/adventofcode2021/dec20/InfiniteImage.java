package com.adventofcode2021.dec20;

import java.util.HashSet;
import java.util.Set;

import com.adventofcode2021.common.Point;

class InfiniteImage {

    private final Pixel defaultPixel;
    private final Set<Point> lightPixels;

    private int minX;
    private int maxX;
    private int minY;
    private int maxY;
    private int minXLit;
    private int maxXLit;
    private int minYLit;
    private int maxYLit;

    InfiniteImage( Pixel defaultPixel ) {
        this.defaultPixel = defaultPixel;
        this.lightPixels = new HashSet<>();
        this.minX = Integer.MAX_VALUE;
        this.maxX = Integer.MIN_VALUE;
        this.minY = Integer.MAX_VALUE;
        this.maxY = Integer.MIN_VALUE;
        this.minXLit = Integer.MAX_VALUE;
        this.maxXLit = Integer.MIN_VALUE;
        this.minYLit = Integer.MAX_VALUE;
        this.maxYLit = Integer.MIN_VALUE;
    }

    void set( Point point, Pixel pixel ) {
        if ( pixel.equals( Pixel.LIGHT ) ) {
            lightPixels.add( point );
            updateLightPixelBoundaries( point );
        }
        updateImageBoundaries( point );
    }

    private void updateLightPixelBoundaries( Point point ) {
        minXLit = Math.min( minXLit, point.x() );
        maxXLit = Math.max( maxXLit, point.x() );
        minYLit = Math.min( minYLit, point.y() );
        maxYLit = Math.max( maxYLit, point.y() );
    }

    private void updateImageBoundaries( Point point ) {
        minX = Math.min(minX, point.x() );
        maxX = Math.max(maxX, point.x() );
        minY = Math.min(minY, point.y() );
        maxY = Math.max(maxY, point.y() );
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for ( int y = minYLit; y <= maxYLit; ++y ) {
            for ( int x = minXLit; x <= maxXLit; ++x ) {
                Pixel pixel = pixelAt( new Point( x, y ) );
                builder.append( pixel );
            }
            builder.append( '\n' );
        }
        return builder.toString();
    }

    int minX() {
        return minX;
    }

    int maxX() {
        return maxX;
    }

    int minY() {
        return minY;
    }

    int maxY() {
        return maxY;
    }

    Pixel defaultPixel() {
        return defaultPixel;
    }

    Pixel pixelAt( Point point ) {
        if ( ! isInbounds( point ) ) {
            return defaultPixel;
        }
        return lightPixels.contains( point )
            ? Pixel.LIGHT
            : Pixel.DARK;
    }

    private boolean isInbounds( Point point ) {
        return minX <= point.x() && point.x() <= maxX
            && minY <= point.y() && point.y() <= maxY;
    }

    int lightPixelCount() {
        return lightPixels.size();
    }
}
