package com.adventofcode2021.dec20;

enum Pixel {

    LIGHT( '#' ),
    DARK( '.' );

    private final char character;

    Pixel( char character ) {
        this.character = character;
    }

    static Pixel parse( char character ) {
        switch ( character ) {
            case '#': return LIGHT;
            case '.': return DARK;
            default: throw new IllegalArgumentException( "Unrecognized pixel: " + character );
        }
    }

    @Override
    public String toString() {
        return String.valueOf( character );
    }
}
