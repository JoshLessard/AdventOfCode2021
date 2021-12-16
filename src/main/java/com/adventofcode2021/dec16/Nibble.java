package com.adventofcode2021.dec16;

import java.util.Objects;

class Nibble {

    private final byte byteValue;

    private Nibble( char hexadecimalCharacter ) {
        this.byteValue = Byte.parseByte( Objects.toString( hexadecimalCharacter ), 16 );
    }

    static Nibble of( char hexadecimalCharacter ) {
        return new Nibble( hexadecimalCharacter );
    }

    byte byteValue() {
        return byteValue;
    }
}
