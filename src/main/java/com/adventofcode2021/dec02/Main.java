package com.adventofcode2021.dec02;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main( String[] args ) throws Exception {
        runPart( "A", new PartASubmarineCommandParser() );
        runPart( "B", new PartBSubmarineCommandParser() );
    }

    private static void runPart( String partName, SubmarineCommandParser commandParser ) throws IOException {
        Submarine submarine = new Submarine();
        parseCommands( commandParser ).forEach( c -> c.applyTo( submarine ) );
        int answer = submarine.currentPosition().x() * submarine.currentPosition().y();

        System.out.println( "Part " + partName + ": " + answer );
    }

    private static List<SubmarineCommand> parseCommands( SubmarineCommandParser commandParser ) throws IOException {
        try ( BufferedReader reader = new BufferedReader( new FileReader( "src/main/resources/dec02/input.txt" ) ) ) {
            return reader.lines()
                .map( commandParser::parse )
                .collect( toList() );
        }
    }
}
