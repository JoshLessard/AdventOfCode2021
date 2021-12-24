package com.adventofcode2021.dec22;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.adventofcode2021.common.Point3d;

public class Main {

    private static final Pattern COMMAND_PATTERN = Pattern.compile( "(on|off) x=(-?\\d+)..(-?\\d+),y=(-?\\d+)..(-?\\d+),z=(-?\\d+)..(-?\\d+)" );

    public static void main( String[] args ) throws Exception {
        int minCoordinate = -50;
        int maxCoordinate = 50;
        ReactorCore reactorCore = new ReactorCore();
        for ( String inputLine : parseInput() ) {
            Matcher matcher = COMMAND_PATTERN.matcher( inputLine );
            if ( matcher.matches() ) {
                ReactorCommand command = ReactorCommand.parse( matcher.group( 1 ) );
                int minX = Math.max( Integer.parseInt( matcher.group( 2 ) ), minCoordinate );
                int maxX = Math.min( Integer.parseInt( matcher.group( 3 ) ), maxCoordinate );
                int minY = Math.max( Integer.parseInt( matcher.group( 4 ) ), minCoordinate );
                int maxY = Math.min( Integer.parseInt( matcher.group( 5 ) ), maxCoordinate );
                int minZ = Math.max( Integer.parseInt( matcher.group( 6 ) ), minCoordinate );
                int maxZ = Math.min( Integer.parseInt( matcher.group( 7 ) ), maxCoordinate );
                for ( int z = minZ; z <= maxZ; ++z ) {
                    for ( int y = minY; y <= maxY; ++y ) {
                        for ( int x = minX; x <= maxX; ++x ) {
                            Point3d point = new Point3d( x, y, z );
                            command.applyTo( reactorCore, point );
                        }
                    }
                }
            }
        }

        System.out.println( "Part A: " + reactorCore.numberOfCubesOn() );
    }

    private static List<String> parseInput() throws Exception {
        try ( BufferedReader reader = new BufferedReader( new FileReader( "src/main/resources/dec22/input.txt" ) ) ) {
            return reader.lines().collect( toList() );
        }
    }
}
