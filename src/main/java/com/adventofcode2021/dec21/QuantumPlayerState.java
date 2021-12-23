package com.adventofcode2021.dec21;

import java.util.Objects;

class QuantumPlayerState {

    private final int player1Position;
    private final int player1Score;
    private final int player2Position;
    private final int player2Score;

    QuantumPlayerState( int player1StartingPosition, int player2StartingPosition ) {
        this.player1Position = player1StartingPosition;
        this.player1Score = 0;
        this.player2Position = player2StartingPosition;
        this.player2Score = 0;
    }

    private QuantumPlayerState( int player1Position, int player1Score, int player2Position, int player2Score ) {
        this.player1Position = player1Position;
        this.player1Score = player1Score;
        this.player2Position = player2Position;
        this.player2Score = player2Score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuantumPlayerState)) return false;
        QuantumPlayerState that = (QuantumPlayerState) o;
        return player1Position == that.player1Position && player1Score == that.player1Score && player2Position == that.player2Position && player2Score == that.player2Score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(player1Position, player1Score, player2Position, player2Score);
    }

    boolean hasPlayerWon( int playerId ) {
        switch ( playerId ) {
            case 1:
                return player1Score >= 21;
            case 2:
                return player2Score >= 21;
            default:
                throw unrecognizedPlayerId( playerId );
        }
    }

    private IllegalArgumentException unrecognizedPlayerId( int playerId ) {
        return new IllegalArgumentException( "Unrecognized player ID: " + playerId );
    }

    public QuantumPlayerState updated( int playerId, int positionDelta ) {
        switch( playerId ) {
            case 1:
                int newPlayer1Position = updatedPosition( player1Position, positionDelta );
                int newPlayer1Score = updatedScore( player1Score, newPlayer1Position );
                return new QuantumPlayerState( newPlayer1Position, newPlayer1Score, player2Position, player2Score );
            case 2:
                int newPlayer2Position = updatedPosition( player2Position, positionDelta );
                int newPlayer2Score = updatedScore( player2Score, newPlayer2Position );
                return new QuantumPlayerState( player1Position, player1Score, newPlayer2Position, newPlayer2Score );
            default:
                throw unrecognizedPlayerId( playerId );
        }
    }

    private int updatedPosition( int currentPosition, int positionDelta ) {
        int newPosition = currentPosition + positionDelta;
        if ( newPosition > 10 ) {
            newPosition -= 10;
        }
        return newPosition;
    }

    private int updatedScore( int player1Score, int scoreDelta ) {
        return player1Score + scoreDelta;
    }
}
