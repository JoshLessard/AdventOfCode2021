package com.adventofcode2021.dec10;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

class SyntaxValidator {

    Optional<Token> invalidToken( String line ) {
        Stack<Token> tokens = new Stack<>();
        for ( int i = 0; i < line.length(); ++i ) {
            Token token = Token.parse( line.charAt( i ) );
            if ( token.isOpening() ) {
                tokens.push( token );
            } else if ( ! tokens.isEmpty() ) {
                Token openingToken = tokens.pop();
                if ( ! token.complement().equals( openingToken ) ) {
                    return Optional.of( token );
                }
            }
        }

        return Optional.empty();
    }

    boolean isCorrupt( String line ) {
        return invalidToken( line ).isPresent();
    }

    List<Token> getMissingClosingTokens( String line ) {
        Stack<Token> tokens = new Stack<>();
        for ( int i = 0; i < line.length(); ++i ) {
            Token token = Token.parse( line.charAt( i ) );
            if ( token.isOpening() ) {
                tokens.push( token );
            } else {
                tokens.pop();
            }
        }

        List<Token> missingClosingTokens = new ArrayList<>();
        while ( ! tokens.isEmpty() ) {
            missingClosingTokens.add( tokens.pop().complement() );
        }
        return missingClosingTokens;
    }
}
