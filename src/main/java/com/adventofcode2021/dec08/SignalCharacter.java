package com.adventofcode2021.dec08;

import java.util.Arrays;

enum SignalCharacter {

    A( 'a' ),
    B( 'b' ),
    C( 'c' ),
    D( 'd' ),
    E( 'e' ),
    F( 'f' ),
    G( 'g' );

    private final int character;

    SignalCharacter(int character ) {
        this.character = character;
    }

    static SignalCharacter parse(int character ) {
        return Arrays.stream( values() )
            .filter( s -> s.character == character )
            .findFirst()
            .orElseThrow( () -> new IllegalArgumentException( "Unrecognized signal: " + character ) );
    }
}
