package com.adventofcode2021.dec20;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.adventofcode2021.common.Point;

public class Main {

    public static void main( String[] args ) throws Exception {
        List<String> input = parseInput();
        ImageEnhancer imageEnhancer = parseImageEnhancer( input );
        InfiniteImage image = parseImage( input.subList( 2, input.size() ) );

        for ( int i = 0; i < 2; ++i ) {
            image = imageEnhancer.enhance( image );
        }
        System.out.println( "Part A: " + image.lightPixelCount() );

        for ( int i = 0; i < 48; ++i ) {
            image = imageEnhancer.enhance( image );
        }
        System.out.println( "Part B: " + image.lightPixelCount() );
    }

    private static List<String> parseInput() throws IOException {
        try ( BufferedReader reader = new BufferedReader( new FileReader( "src/main/resources/dec20/input.txt" ) ) ) {
            return reader.lines().collect( toList() );
        }
    }

    private static ImageEnhancer parseImageEnhancer( List<String> input ) {
        String algorithm = input.get( 0 );
        return new ImageEnhancer( algorithm );
    }

    private static InfiniteImage parseImage( List<String> input ) {
        InfiniteImage image = new InfiniteImage( Pixel.DARK );
        for ( int y = 0; y < input.size(); ++y ) {
            String line = input.get( y );
            for ( int x = 0; x < line.length(); ++x ) {
                Pixel pixel = Pixel.parse( line.charAt( x ) );
                image.set( new Point( x, y ), pixel );
            }
        }
        return image;
    }
}
