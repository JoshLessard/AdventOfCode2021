package com.adventofcode2021.dec17;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.adventofcode2021.common.Point;

public class Main {

    private static final Pattern TARGET_AREA_PATTERN = Pattern.compile( "target area: x=(-?\\d+)..(-?\\d+), y=(-?\\d+)..(-?\\d+)" );

    public static void main( String[] args ) throws Exception {
        ProbeLaunchSolver solver = new ProbeLaunchSolver( parseTargetArea(), Point.ORIGIN );

        System.out.println( "Part A: " + solver.getHighestAttainableHeight() );
        System.out.println( "Part B: " + solver.countSuccessfulVelocities() );
    }

    private static TargetArea parseTargetArea() throws Exception {
        Matcher matcher = TARGET_AREA_PATTERN.matcher( parseInput() );
        if ( matcher.matches() ) {
            int x1 = Integer.parseInt( matcher.group( 1 ) );
            int x2 = Integer.parseInt( matcher.group( 2 ) );
            int y1 = Integer.parseInt( matcher.group( 3 ) );
            int y2 = Integer.parseInt( matcher.group( 4 ) );

            return new TargetArea( x1, x2, y1, y2 );
        } else {
            throw new IllegalArgumentException( "Unable to parse input." );
        }
    }

    private static String parseInput() throws Exception {
        try ( BufferedReader reader = new BufferedReader( new FileReader( "src/main/resources/dec17/input.txt" ) ) ) {
            return reader.lines().findFirst().orElseThrow();
        }
    }
}
