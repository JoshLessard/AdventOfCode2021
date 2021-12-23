package com.adventofcode2021.dec21;

class DiracDiceGame {

    private static final int NUMBER_OF_POSITIONS = 10;

    private final int[] playerPositionsByPlayerIndex;
    private final int[] playerScoresByPlayerIndex;
    private final DieRoller dieRoller;

    private int numberOfRegisteredPlayers;
    private int activePlayerIndex;
    private Integer winningPlayerIndex;

    DiracDiceGame( DieRoller dieRoller ) {
        this.playerPositionsByPlayerIndex = new int[2];
        this.playerScoresByPlayerIndex = new int[2];
        this.dieRoller = dieRoller;
        this.numberOfRegisteredPlayers = 0;
        this.activePlayerIndex = 0;
        this.winningPlayerIndex = null;
    }

    void addPlayer( int playerId, int startingPosition ) {
        int playerIndex = numberOfRegisteredPlayers++;
        setPosition( playerIndex, startingPosition );
    }

    void takeTurn() {
        int numberOfSpacesToMove = dieRoller.rollDie() + dieRoller.rollDie() + dieRoller.rollDie();
        movePlayer( activePlayerIndex, numberOfSpacesToMove );
        updateScore( activePlayerIndex, positionOf( activePlayerIndex ) );
        activePlayerIndex = (activePlayerIndex + 1) % numberOfRegisteredPlayers;
    }

    private void movePlayer( int playerIndex, int numberOfSpacesToMove ) {
        playerPositionsByPlayerIndex[playerIndex] = (playerPositionsByPlayerIndex[playerIndex] + numberOfSpacesToMove) % NUMBER_OF_POSITIONS;
    }

    private void setPosition( int playerIndex, int startingPosition ) {
        playerPositionsByPlayerIndex[playerIndex] = startingPosition - 1;
    }

    private int positionOf( int playerIndex ) {
        return playerPositionsByPlayerIndex[playerIndex] + 1;
    }

    private void updateScore( int playerIndex, int scoreDelta ) {
        playerScoresByPlayerIndex[playerIndex] += scoreDelta;
        if ( playerScoresByPlayerIndex[playerIndex] >= 1000 ) {
            this.winningPlayerIndex = playerIndex;
        }
    }

    boolean gameHasBeenWon() {
        return winningPlayerIndex != null;
    }

    int loserScore() {
        int losingPlayerIndex = winningPlayerIndex == 0 ? 1 : 0;
        return playerScoresByPlayerIndex[losingPlayerIndex];
    }

    int numberOfDieRolls() {
        return dieRoller.numberOfTimesRolled();
    }
}
