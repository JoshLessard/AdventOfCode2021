package com.adventofcode2021.dec21;

import java.util.HashMap;
import java.util.Map;

class QuantumDiracDiceGame {

    private static final Map<Integer, Integer> QUANTUM_DICE_ROLL_COUNT_BY_VALUE = calculateQuantumDiceRolls();

    private static Map<Integer, Integer> calculateQuantumDiceRolls() {
        Map<Integer, Integer> rolls = new HashMap<>();
        for ( int die1 = 1; die1 <= 3; ++die1 ) {
            for ( int die2 = 1; die2 <= 3; ++die2 ) {
                for ( int die3 = 1; die3 <= 3; ++die3 ) {
                    int rollValue = die1 + die2 + die3;
                    int currentCount = rolls.computeIfAbsent( rollValue, v -> 0 ) + 1;
                    rolls.put( rollValue, currentCount );
                }
            }
        }
        return rolls;
    }

    private final Map<QuantumPlayerState, Long> playerDataPerScorePositionPermutation;
    private final PlayerWinCounter playerWinCounter;

    private int currentPlayerId = 1;

    QuantumDiracDiceGame( int player1StartingPosition, int player2StartingPosition ) {
        this.playerDataPerScorePositionPermutation = new HashMap<>();
        this.playerWinCounter = new PlayerWinCounter();
        this.playerDataPerScorePositionPermutation.put(
            new QuantumPlayerState( player1StartingPosition, player2StartingPosition ),
            1L
        );
    }

    void playAllGames() {
        while ( ! playerDataPerScorePositionPermutation.isEmpty() ) {
            Map<QuantumPlayerState, Long> nextRoundPlayerData = new HashMap<>();
            for ( QuantumPlayerState playerState : playerDataPerScorePositionPermutation.keySet() ) {
                QUANTUM_DICE_ROLL_COUNT_BY_VALUE.forEach( (scoreValue, multiplier) -> {
                    QuantumPlayerState updatedPlayerState = playerState.updated( currentPlayerId, scoreValue );
                    long updatedNumberOfGames = playerDataPerScorePositionPermutation.get( playerState ) * multiplier;
                    if ( updatedPlayerState.hasPlayerWon( currentPlayerId ) ) {
                        playerWinCounter.incrementWins( currentPlayerId, updatedNumberOfGames );
                    } else {
                        long totalNumberOfGames = nextRoundPlayerData.getOrDefault( updatedPlayerState, 0L ) + updatedNumberOfGames;
                        nextRoundPlayerData.put( updatedPlayerState, totalNumberOfGames );
                    }
                });
            }
            playerDataPerScorePositionPermutation.clear();
            playerDataPerScorePositionPermutation.putAll( nextRoundPlayerData );
            currentPlayerId = currentPlayerId == 1 ? 2 : 1;
        }
    }

    long winCount( int playerId ) {
        return playerWinCounter.winCount( playerId );
    }
}
