package com.adventofcode2021.dec04;

import java.util.ArrayList;
import java.util.List;

class BingoGame {

    private final List<BingoCard> cards;
    private final List<BingoCard> winningCards;

    BingoGame( List<BingoCard> cards ) {
        this.cards = cards;
        this.winningCards = new ArrayList<>();
    }

    void callNumber( int number ) {
        cards.stream()
            .filter( card -> ! card.hasCompletedLine() )
            .forEach( card -> markNumber( number, card ) );
    }

    private void markNumber( int number, BingoCard card ) {
        card.markNumber( number );
        if ( card.hasCompletedLine() ) {
            winningCards.add( card );
        }
    }

    BingoCard firstWinner() {
        return winningCards.get( 0 );
    }

    BingoCard lastWinner() {
        return winningCards.get( winningCards.size() - 1 );
    }
}
