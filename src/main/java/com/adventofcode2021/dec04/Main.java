package com.adventofcode2021.dec04;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main( String[] args ) throws Exception {
        List<String> input = parseInput();
        List<Integer> calledNumbers = parseDrawnNumbers( input.remove( 0 ) );
        BingoGame game = parseBingoGame( input );
        calledNumbers.forEach( game::callNumber );

        System.out.println( "Part A: " + game.firstWinner().score() );
        System.out.println( "Part B: " + game.lastWinner().score() );
    }

    private static List<String> parseInput() throws Exception {
        try ( BufferedReader reader = new BufferedReader( new FileReader( "src/main/resources/dec04/input.txt" ) ) ) {
            return reader.lines().collect( toList() );
        }
    }

    private static List<Integer> parseDrawnNumbers(String input ) {
        return Arrays.stream( input.split( "," ) ).map( Integer::parseInt ).collect( toList() );
    }

    private static BingoGame parseBingoGame( List<String> input ) {
        List<BingoCard> bingoCards = new ArrayList<>();
        while ( ! input.isEmpty() ) {
            bingoCards.add( parseBingoCard( input ) );
        }
        return new BingoGame( bingoCards );
    }

    private static BingoCard parseBingoCard( List<String> input ) {
        while ( input.get( 0 ).isEmpty() ) {
            input.remove( 0 );
        }
        int[][] numbers = new int[BingoCard.CARD_WIDTH][BingoCard.CARD_WIDTH];
        for ( int row = 0; row < BingoCard.CARD_WIDTH; ++row ) {
            String line = input.remove( 0 );
            String[] rowNumbers = line.trim().split( "\\s+" );
            for ( int column = 0; column < BingoCard.CARD_WIDTH; ++column ) {
                numbers[row][column] = Integer.parseInt( rowNumbers[column] );
            }
        }

        return new BingoCard( numbers );
    }
}
