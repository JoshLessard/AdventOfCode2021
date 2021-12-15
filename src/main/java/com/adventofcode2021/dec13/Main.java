package com.adventofcode2021.dec13;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.adventofcode2021.common.Point;

public class Main {

    private static final Pattern DOT_PATTERN = Pattern.compile( "(\\d+),(\\d+)" );
    private static final Pattern FOLD_PATTERN = Pattern.compile( "fold along ([xy])=(\\d+)" );

    public static void main( String[] args ) throws Exception {
        List<String> input = parseInput();
        Sheet sheet = parseSheet( input );
        List<FoldCommand> folds = parseFoldCommands( input );

        folds.get( 0 ).fold( sheet );
        System.out.println( "Part A: " + sheet.dotCount() );

        for ( int i = 1; i < folds.size(); ++i ) {
            folds.get( i ).fold( sheet );
        }
        System.out.println( "Part B:\n\n" + sheet );
    }

    private static Sheet parseSheet( List<String> input ) {
        Sheet sheet = new Sheet();
        String inputLine;
        int lineIndex = 0;
        while ( ! ( inputLine = input.get( lineIndex++ ) ).isEmpty() ) {
            Matcher matcher = DOT_PATTERN.matcher( inputLine );
            if ( matcher.matches() ) {
                int x = Integer.parseInt( matcher.group( 1 ) );
                int y = Integer.parseInt( matcher.group( 2 ) );
                sheet.addDot( new Point( x, y ) );
            }
        }

        return sheet;
    }

    private static List<FoldCommand> parseFoldCommands( List<String> input ) {
        int lineIndex = 0;
        while ( ! input.get( lineIndex ).isEmpty() ) {
            ++lineIndex;
        }

        List<FoldCommand> folds = new ArrayList<>();
        while ( lineIndex < input.size() ) {
            String inputLine = input.get( lineIndex++ );
            Matcher matcher = FOLD_PATTERN.matcher( inputLine );
            if ( matcher.matches() ) {
                char direction = matcher.group( 1 ).charAt( 0 );
                int coordinate = Integer.parseInt( matcher.group( 2 ) );
                if ( direction == 'x' ) {
                    folds.add( sheet -> sheet.foldLeft( coordinate ) );
                } else if ( direction == 'y' ) {
                    folds.add( sheet -> sheet.foldUp( coordinate ) );
                }
            }
        }

        return folds;
    }

    private static List<String> parseInput() throws Exception {
        try ( BufferedReader reader = new BufferedReader( new FileReader( "src/main/resources/dec13/input.txt" ) ) ) {
            return reader.lines().collect( toList() );
        }
    }
}
