package com.adventofcode2021.dec16;

import java.io.BufferedReader;
import java.io.FileReader;

public class Main {

    public static void main( String[] args ) throws Exception {
        Packet packet = parsePacket();

        System.out.println( "Part A: " + packet.version() );
        System.out.println( "Part B: " + packet.value() );
    }

    private static Packet parsePacket() throws Exception {
        PacketParser parser = new PacketParser( parseInput() );
        return parser.parse();
    }

    private static String parseInput() throws Exception {
        try ( BufferedReader reader = new BufferedReader( new FileReader( "src/main/resources/dec16/input.txt" ) ) ) {
            return reader.lines().findFirst().orElseThrow();
        }
    }
}
