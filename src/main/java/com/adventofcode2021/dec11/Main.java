package com.adventofcode2021.dec11;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

public class Main {

    public static void main( String[] args ) throws Exception {
        System.out.println( "Part A: " + getFlashCountAfter( 100 ) );
        System.out.println( "Part B: " + numberOfStepsBeforeEverythingFlashes() );
    }

    private static int getFlashCountAfter( int steps ) throws Exception {
        Grid grid = parseGrid();
        for ( int i = 0; i < steps; ++i ) {
            grid.step();
        }
        return grid.flashCount();
    }

    private static int numberOfStepsBeforeEverythingFlashes() throws Exception {
        Grid grid = parseGrid();
        for ( int i = 1; ; ++i ) {
            if ( grid.step() ) {
                return i;
            }
        }
    }

    private static Grid parseGrid() throws Exception {
        List<String> input = parseInput();
        int[][] cells = new int[input.size()][];
        for ( int row = 0; row < input.size(); ++row ) {
            cells[row] = parseRow( input.get( row ) );
        }

        return new Grid( cells );
    }

    private static List<String> parseInput() throws Exception {
        try ( BufferedReader reader = new BufferedReader( new FileReader( "src/main/resources/dec11/input.txt" ) ) ) {
            return reader.lines().collect( toList() );
        }
    }

    private static int[] parseRow( String line ) {
        int[] row = new int[line.length()];
        for ( int i = 0; i < line.length(); ++i ) {
            row[i] = line.charAt( i ) - '0';
        }
        return row;
    }
}
