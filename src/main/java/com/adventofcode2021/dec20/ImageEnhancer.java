package com.adventofcode2021.dec20;

import com.adventofcode2021.common.Point;

class ImageEnhancer {

    private final Pixel[] algorithm;

    ImageEnhancer( String algorithm ) {
        this.algorithm = new Pixel[algorithm.length()];
        for ( int i = 0; i < algorithm.length(); ++i ) {
            this.algorithm[i] = Pixel.parse( algorithm.charAt( i ) );
        }
    }

    InfiniteImage enhance( InfiniteImage inputImage ) {
        Pixel defaultPixel = enhancedDefaultPixel( inputImage.defaultPixel() );
        InfiniteImage enhancedImage = new InfiniteImage( defaultPixel );
        for ( int y = inputImage.minY() - 1; y <= inputImage.maxY() + 1; ++y ) {
            for ( int x = inputImage.minX() - 1; x <= inputImage.maxX() + 1; ++x ) {
                Point point = new Point( x, y );
                enhancedImage.set( point, enhancedPixel( inputImage, point ) );
            }
        }
        return enhancedImage;
    }

    private Pixel enhancedDefaultPixel( Pixel currentDefaultPixel ) {
        return currentDefaultPixel == Pixel.LIGHT
            ? algorithm[algorithm.length - 1]
            : algorithm[0];
    }

    private Pixel enhancedPixel( InfiniteImage image, Point point ) {
        int algorithmIndex = 0;
        for ( int y = point.y() - 1; y <= point.y() + 1; ++y ) {
            for ( int x = point.x() - 1; x <= point.x() + 1; ++x ) {
                Point neighbour = new Point( x, y );
                algorithmIndex *= 2;
                algorithmIndex += image.pixelAt( neighbour ).equals( Pixel.LIGHT ) ? 1 : 0;
            }
        }
        return algorithm[algorithmIndex];
    }
}
