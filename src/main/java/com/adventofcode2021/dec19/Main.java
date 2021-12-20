package com.adventofcode2021.dec19;

import static java.util.stream.Collectors.toCollection;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.adventofcode2021.common.Point3d;

public class Main {

    private static final Pattern SCANNER_PATTERN = Pattern.compile( "--- scanner (\\d+) ---" );
    private static final Pattern BEACON_PATTERN = Pattern.compile( "(-?\\d+),(-?\\d+),(-?\\d+)" );

    public static void main( String[] args ) throws Exception {
        List<BeaconMap> maps = parseMaps();
        while ( maps.size() > 1 ) {
            BeaconMap absorbingMap = maps.remove( 0 );
            for ( int i = maps.size() - 1; i >= 0; --i ) {
                BeaconMap candidateForAbsorption = maps.get( i );
                if ( candidateForAbsorption.sharesBeaconsWith( absorbingMap, 12 ) && absorbingMap.absorb( candidateForAbsorption ) ) {
                    maps.remove( i );
                }
            }
            maps.add( absorbingMap );
        }

        BeaconMap completeMap = maps.remove( 0 );
        System.out.println( "Part A: " + completeMap.numberOfBeacons() );
        System.out.println( "Part B: " + completeMap.largestManhattanDistanceBetweenScanners() );
    }

    private static List<BeaconMap> parseMaps() throws Exception {
        List<BeaconMap> maps = new ArrayList<>();
        Queue<String> input = parseInput();
        while ( ! input.isEmpty() ) {
            Matcher matcher = SCANNER_PATTERN.matcher( input.poll() );
            if ( matcher.matches() ) {
                int scannerId = Integer.parseInt( matcher.group( 1 ) );
                maps.add( parseMap( scannerId, input ) );
            }
        }

        return maps;
    }

    private static Queue<String> parseInput() throws Exception {
        try ( BufferedReader reader = new BufferedReader( new FileReader( "src/main/resources/dec19/input.txt" ) ) ) {
            return reader.lines().collect( toCollection( LinkedList::new ) );
        }
    }

    private static BeaconMap parseMap( int scannerId, Queue<String> input ) {
        Set<Point3d> beaconLocations = new HashSet<>();
        while ( ! input.isEmpty() ) {
            Matcher matcher = BEACON_PATTERN.matcher( input.poll() );
            if ( matcher.matches() ) {
                beaconLocations.add(
                    new Point3d(
                        Integer.parseInt( matcher.group( 1 ) ),
                        Integer.parseInt( matcher.group( 2 ) ),
                        Integer.parseInt( matcher.group( 3 ) )
                    )
                );
            } else {
                break;
            }
        }

        return new BeaconMap( scannerId, beaconLocations );
    }
}
