package com.adventofcode2021.dec06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main( String[] args ) throws Exception {
        LanternfishPopulation population = new LanternfishPopulation( parsePopulation() );
        for ( int i = 0; i < 80; ++i ) {
            population.advanceDay();
        }
        System.out.println( "Part A: " + population.size() );

        for ( int i = 0; i < 256 - 80; ++i ) {
            population.advanceDay();
        }
        System.out.println( "Part B: " + population.size() );
    }

    private static long[] parsePopulation() throws IOException {
        long[] population = new long[9];
        try ( BufferedReader reader = new BufferedReader( new FileReader( "src/main/resources/dec06/input.txt" ) ) ) {
            reader.lines()
                .map( l -> l.split( "," ) )
                .flatMap( Arrays::stream )
                .mapToInt( Integer::parseInt )
                .forEach( d -> ++population[d] );
        }
        return population;
    }
}
