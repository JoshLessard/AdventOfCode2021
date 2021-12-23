package com.adventofcode2021.dec21;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static final Pattern PLAYER_STARTING_POSITION_PATTERN = Pattern.compile( "Player (\\d+) starting position: (\\d+)" );

    public static void main( String[] args ) throws Exception {
        DiracDiceGame game = parseGame();
        while ( ! game.gameHasBeenWon() ) {
            game.takeTurn();
        }
        System.out.println( "Part A: " + (game.loserScore() * game.numberOfDieRolls()) );

        QuantumDiracDiceGame quantumGame = parseQuantumGame();
        quantumGame.playAllGames();
        System.out.println( "Part B: " + Math.max( quantumGame.winCount( 1 ), quantumGame.winCount( 2 ) ) );
    }

    private static DiracDiceGame parseGame() throws Exception {
        DiracDiceGame game = new DiracDiceGame( new SequentialDieRoller( 1 ) );
        for ( String inputLine : parseInput() ) {
            Matcher matcher = PLAYER_STARTING_POSITION_PATTERN.matcher( inputLine );
            if ( matcher.matches() ) {
                int playerId = Integer.parseInt( matcher.group( 1 ) );
                int startingPosition = Integer.parseInt( matcher.group( 2 ) );
                game.addPlayer( playerId, startingPosition );
            }
        }
        return game;
    }

    private static QuantumDiracDiceGame parseQuantumGame() throws Exception {
        Map<Integer, Integer> startingPositionsByPlayerId = new HashMap<>();
        for ( String inputLine : parseInput() ) {
            Matcher matcher = PLAYER_STARTING_POSITION_PATTERN.matcher( inputLine );
            if ( matcher.matches() ) {
                int playerId = Integer.parseInt( matcher.group( 1 ) );
                int startingPosition = Integer.parseInt( matcher.group( 2 ) );
                startingPositionsByPlayerId.put( playerId, startingPosition );
            }
        }
        return new QuantumDiracDiceGame(
            startingPositionsByPlayerId.get( 1 ),
            startingPositionsByPlayerId.get( 2 )
        );
    }

    private static List<String> parseInput() throws IOException {
        try ( BufferedReader reader = new BufferedReader( new FileReader( "src/main/resources/dec21/input.txt" ) ) ) {
            return reader.lines().collect( toList() );
        }
    }
}
