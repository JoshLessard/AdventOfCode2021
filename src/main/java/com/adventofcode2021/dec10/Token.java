package com.adventofcode2021.dec10;

import java.util.Arrays;

enum Token {

    OPENING_PARENTHESIS( '(', true ) {
        @Override
        Token complement() {
            return CLOSING_PARENTHESIS;
        }
    },
    CLOSING_PARENTHESIS( ')', false ) {
        @Override
        Token complement() {
            return OPENING_PARENTHESIS;
        }
    },
    OPENING_SQUARE_BRACKET( '[', true ) {
        @Override
        Token complement() {
            return CLOSING_SQUARE_BRACKET;
        }
    },
    CLOSING_SQUARE_BRACKET( ']', false ) {
        @Override
        Token complement() {
            return OPENING_SQUARE_BRACKET;
        }
    },
    OPENING_CURLY_BRACE( '{', true ) {
        @Override
        Token complement() {
            return CLOSING_CURLY_BRACE;
        }
    },
    CLOSING_CURLY_BRACE( '}', false ) {
        @Override
        Token complement() {
            return OPENING_CURLY_BRACE;
        }
    },
    OPENING_ANGLE_BRACKET( '<', true ) {
        @Override
        Token complement() {
            return CLOSING_ANGLE_BRACKET;
        }
    },
    CLOSING_ANGLE_BRACKET( '>', false ) {
        @Override
        Token complement() {
            return OPENING_ANGLE_BRACKET;
        }
    };

    private final int character;
    private final boolean isOpening;

    Token( int character, boolean isOpening ) {
        this.character = character;
        this.isOpening = isOpening;
    }

    abstract Token complement();

    static Token parse( int character ) {
        return Arrays.stream( values() )
            .filter( t -> t.character == character )
            .findFirst()
            .orElseThrow( () -> new IllegalArgumentException( "Unrecognized token: " + character ) );
    }

    boolean isOpening() {
        return isOpening;
    }
}
