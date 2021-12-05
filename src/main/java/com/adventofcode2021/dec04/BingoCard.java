package com.adventofcode2021.dec04;

import java.util.ArrayList;
import java.util.List;

class BingoCard {

    static final int CARD_WIDTH = 5;

    private final int[][] numbers;
    private final boolean[][] marked;

    private BingoCardStatus status;
    private int winningNumber;

    BingoCard( int[][] numbers ) {
        this.numbers = numbers;
        this.marked = new boolean[CARD_WIDTH][CARD_WIDTH];
        this.status = BingoCardStatus.NOTHING_COMPLETED;
    }

    public void markNumber( int number ) {
        for ( int row = 0; row < CARD_WIDTH; ++row ) {
            for ( int column = 0; column < CARD_WIDTH; ++column ) {
                if ( numbers[row][column] == number ) {
                    marked[row][column] = true;
                    if ( isLineComplete( row, column ) ) {
                        status = BingoCardStatus.COMPLETED_LINE;
                        winningNumber = number;
                    }
                }
            }
        }
    }

    private boolean isLineComplete( int row, int column ) {
        return isRowCompleted( row ) || isColumnCompleted( column );
    }

    private boolean isRowCompleted( int row ) {
        for ( int column = 0; column < CARD_WIDTH; ++column ) {
            if ( ! marked[row][column] ) {
                return false;
            }
        }

        return true;
    }

    private boolean isColumnCompleted( int column ) {
        for ( int row = 0; row < CARD_WIDTH; ++row ) {
            if ( ! marked[row][column] ) {
                return false;
            }
        }

        return true;
    }

    boolean hasCompletedLine() {
        return status == BingoCardStatus.COMPLETED_LINE;
    }

    int score() {
        int sumOfUnmarkedNumbers = unmarkedNumbers().stream().mapToInt( i -> i ).sum();
        return sumOfUnmarkedNumbers * winningNumber;
    }

    private List<Integer> unmarkedNumbers() {
        List<Integer> unmarkedNumbers = new ArrayList<>();
        for ( int row = 0; row < CARD_WIDTH; ++row ) {
            for ( int column = 0; column < CARD_WIDTH; ++column ) {
                if ( ! marked[row][column] ) {
                    unmarkedNumbers.add( numbers[row][column] );
                }
            }
        }

        return unmarkedNumbers;
    }
}
