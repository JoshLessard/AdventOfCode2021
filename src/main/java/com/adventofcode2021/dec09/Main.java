package com.adventofcode2021.dec09;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class Main {

    private static final int GRID_WIDTH = 100;
    private static final int GRID_HEIGHT = 100;

    public static void main( String[] args ) throws Exception {
        Grid grid = parseGrid();

        System.out.println( "Part A: " + grid.riskLevel() );
        System.out.println( "Part B: " + getReduce( grid ) );
    }

    private static int getReduce( Grid grid ) {
        return grid.basins().stream()
            .sorted( Comparator.comparing( Basin::size ).reversed() )
            .limit( 3 )
            .mapToInt( Basin::size )
            .reduce( 1, (a, b) -> a * b );
    }

    private static Grid parseGrid() throws Exception {
        List<String> input = parseInput();
        int[][] grid = new int[GRID_HEIGHT][];
        for ( int row = 0; row < GRID_HEIGHT; ++row ) {
            grid[row] = toGridRow( input.get( row ) );
        }
        return new Grid( grid );
    }

    private static List<String> parseInput() throws IOException {
        try ( BufferedReader reader = new BufferedReader( new FileReader( "src/main/resources/dec09/input.txt" ) ) ) {
            return reader.lines().collect( toList() );
        }
    }

    private static int[] toGridRow( String line ) {
        int[] row = new int[GRID_WIDTH];
        for ( int i = 0; i < GRID_WIDTH; ++i ) {
            int digit = line.charAt( i );
            row[i] = digit - '0';
        }
        return row;
    }
}
