package com.adventofcode2021.dec05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.function.BiPredicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.adventofcode2021.common.Point;

public class Main {

    private static final Pattern VENT_LINE_PATTERN = Pattern.compile( "^(\\d+),(\\d+) -> (\\d+),(\\d+)$" );

    public static void main( String[] args ) throws Exception {
        VentField fieldWithNoDiagonals = parseVentField( (from, to) -> from.isHorizontalLineTo( to ) || from.isVerticalLineTo( to ) );
        System.out.println( "Part A: " + fieldWithNoDiagonals.overlapCount() );

        VentField fieldWithAllLines = parseVentField( (from, to) -> true );
        System.out.println( "Part B: " + fieldWithAllLines.overlapCount() );
    }

    private static VentField parseVentField( BiPredicate<Point, Point> pointFilter ) throws Exception {
        VentField field = new VentField();
        try ( BufferedReader reader = new BufferedReader( new FileReader( "src/main/resources/dec05/input.txt" ) ) ) {
            reader.lines().forEach( line -> {
                Matcher matcher = VENT_LINE_PATTERN.matcher( line );
                if ( matcher.matches() ) {
                    Point from = new Point( Integer.parseInt( matcher.group( 1 ) ), Integer.parseInt( matcher.group( 2 ) ) );
                    Point to = new Point( Integer.parseInt( matcher.group( 3 ) ), Integer.parseInt( matcher.group( 4 ) ) );
                    if ( pointFilter.test( from, to ) ) {
                        field.addVentLine( from, to );
                    }
                }
            } );
        }
        return field;
    }
}
