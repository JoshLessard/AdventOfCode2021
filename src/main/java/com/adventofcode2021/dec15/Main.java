package com.adventofcode2021.dec15;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

import com.adventofcode2021.common.Point;

public class Main {

    public static void main( String[] args ) throws Exception {
        Cavern partACavern = parsePartACavern();
        System.out.println( "Part A: " + partACavern.lowestRisk( new Point( 0, 0 ), new Point( partACavern.width() - 1, partACavern.height() - 1 ) ) );

        Cavern partBCavern = parsePartBCavern();
        System.out.println( "Part B: " + partBCavern.lowestRisk( new Point( 0, 0 ), new Point( partBCavern.width() - 1, partBCavern.height() - 1 ) ) );
    }

    private static Cavern parsePartACavern() throws Exception {
        int[][] risks = parseInputGrid();
        return new Cavern( risks );
    }

    private static int[][] parseInputGrid() throws Exception {
        List<String> input = parseInput();
        int[][] risks = new int[input.size()][];
        for ( int i = 0; i < input.size(); ++i ) {
            risks[i] = parseLine( input.get( i ) );
        }
        return risks;
    }

    private static List<String> parseInput() throws Exception {
        try ( BufferedReader reader = new BufferedReader( new FileReader( "src/main/resources/dec15/input.txt" ) ) ) {
            return reader.lines().collect( toList() );
        }
    }

    private static int[] parseLine( String line ) {
        int[] risks = new int[line.length()];
        for ( int i = 0; i < line.length(); ++i ) {
            risks[i] = line.charAt( i ) - '0';
        }
        return risks;
    }

    private static Cavern parsePartBCavern() throws Exception {
        int[][] inputTile = parseInputGrid();
        int inputTileWidth = inputTile[0].length;
        int inputTileHeight = inputTile.length;
        int[][] risks = new int[inputTileHeight * 5][inputTileWidth * 5];

        for ( int y = 0; y < 5; ++y ) {
            for ( int x = 0; x < 5; ++x ) {
                int[][] currentTile = shiftTile( inputTile, x + y );
                copyTile( currentTile, risks, x * inputTileWidth, y * inputTileHeight );
            }
        }
        return new Cavern( risks );
    }

    private static void copyTile( int[][] source, int[][] destination, int destinationX, int destinationY ) {
        for ( int y = 0; y < source.length; ++y ) {
            System.arraycopy( source[y], 0, destination[destinationY + y], destinationX, source[y].length );
        }
    }

    private static int[][] shiftTile( int[][] tile, int shiftFactor ) {
        if ( shiftFactor == 0 ) {
            return tile;
        }

        int width = tile[0].length;
        int height = tile.length;
        int[][] shiftedTile = new int[height][width];
        for ( int y = 0; y < height; ++y ) {
            for ( int x = 0; x < width; ++x ) {
                shiftedTile[y][x] = shiftValue( tile[y][x], shiftFactor );
            }
        }
        return shiftedTile;
    }

    private static int shiftValue( int value, int shiftFactor ) {
        for ( int i = 0; i < shiftFactor; ++i ) {
            ++value;
            if ( value > 9 ) {
                value = 1;
            }
        }
        return value;
    }
}
