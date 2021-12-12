package com.adventofcode2021.dec10;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Main {

    private static final SyntaxValidator VALIDATOR = new SyntaxValidator();

    private static final Map<Token, Integer> SCORES_BY_CORRUPTED_TOKEN = Map.of(
        Token.CLOSING_PARENTHESIS, 3,
        Token.CLOSING_SQUARE_BRACKET, 57,
        Token.CLOSING_CURLY_BRACE, 1197,
        Token.CLOSING_ANGLE_BRACKET, 25137
    );

    private static final Map<Token, Integer> SCORES_BY_INCOMPLETE_TOKEN = Map.of(
        Token.CLOSING_PARENTHESIS, 1,
        Token.CLOSING_SQUARE_BRACKET, 2,
        Token.CLOSING_CURLY_BRACE, 3,
        Token.CLOSING_ANGLE_BRACKET, 4
    );

    public static void main( String[] args ) throws Exception {
        List<String> input = parseInput();

        System.out.println( "Part A: " + getCorruptedScore( input ) );
        System.out.println( "Part B: " + getMiddleIncompleteScore( input ) );
    }

    private static List<String> parseInput() throws IOException {
        try ( BufferedReader reader = new BufferedReader( new FileReader( "src/main/resources/dec10/input.txt" ) ) ) {
            return reader.lines().collect( toList() );
        }
    }

    private static int getCorruptedScore( List<String> input ) {
        return input.stream()
            .map( VALIDATOR::invalidToken )
            .flatMap( Optional::stream )
            .mapToInt( SCORES_BY_CORRUPTED_TOKEN::get )
            .sum();
    }

    private static long getMiddleIncompleteScore( List<String> input ) {
        List<Long> incompleteScores = input.stream()
            .filter( l -> ! VALIDATOR.isCorrupt( l ) )
            .map( Main::getIncompleteScore )
            .sorted()
            .collect( toList() );
        int middleIndex = incompleteScores.size() / 2;
        return incompleteScores.get( middleIndex );
    }

    private static long getIncompleteScore( String line ) {
        long score = 0;
        for ( Token token : VALIDATOR.getMissingClosingTokens( line ) ) {
            score *= 5;
            score += SCORES_BY_INCOMPLETE_TOKEN.get( token );
        }
        return score;
    }
}
