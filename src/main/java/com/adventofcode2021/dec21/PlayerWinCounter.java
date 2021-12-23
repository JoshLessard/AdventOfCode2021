package com.adventofcode2021.dec21;

class PlayerWinCounter {

    private long player1WinCount;
    private long player2WinCount;

    PlayerWinCounter() {
        this.player1WinCount = this.player2WinCount = 0L;
    }

    void incrementWins( int playerId, long numberOfWinsDelta ) {
        switch ( playerId ) {
            case 1:
                player1WinCount += numberOfWinsDelta;
                break;
            case 2:
                player2WinCount += numberOfWinsDelta;
                break;
            default:
                throw unrecognizedPlayerId( playerId );
        }
    }

    long winCount( int playerId ) {
        switch( playerId ) {
            case 1:
                return player1WinCount;
            case 2:
                return player2WinCount;
            default:
                throw unrecognizedPlayerId( playerId );

        }
    }

    private IllegalArgumentException unrecognizedPlayerId( int playerId ) {
        return new IllegalArgumentException( "Unrecognized player ID: " + playerId );
    }
}
