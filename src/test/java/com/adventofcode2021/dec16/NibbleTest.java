package com.adventofcode2021.dec16;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class NibbleTest {

    @Test
    public void nibbleReturnsAnyHexadecimalCharacterAsByteValue() {
        assertThat( Nibble.of( '0' ).byteValue() ).isEqualTo( (byte) 0 );
        assertThat( Nibble.of( '1' ).byteValue() ).isEqualTo( (byte) 1 );
        assertThat( Nibble.of( '2' ).byteValue() ).isEqualTo( (byte) 2 );
        assertThat( Nibble.of( '3' ).byteValue() ).isEqualTo( (byte) 3 );
        assertThat( Nibble.of( '4' ).byteValue() ).isEqualTo( (byte) 4 );
        assertThat( Nibble.of( '5' ).byteValue() ).isEqualTo( (byte) 5 );
        assertThat( Nibble.of( '6' ).byteValue() ).isEqualTo( (byte) 6 );
        assertThat( Nibble.of( '7' ).byteValue() ).isEqualTo( (byte) 7 );
        assertThat( Nibble.of( '8' ).byteValue() ).isEqualTo( (byte) 8 );
        assertThat( Nibble.of( '9' ).byteValue() ).isEqualTo( (byte) 9 );
        assertThat( Nibble.of( 'A' ).byteValue() ).isEqualTo( (byte) 10 );
        assertThat( Nibble.of( 'B' ).byteValue() ).isEqualTo( (byte) 11 );
        assertThat( Nibble.of( 'C' ).byteValue() ).isEqualTo( (byte) 12 );
        assertThat( Nibble.of( 'D' ).byteValue() ).isEqualTo( (byte) 13 );
        assertThat( Nibble.of( 'E' ).byteValue() ).isEqualTo( (byte) 14 );
        assertThat( Nibble.of( 'F' ).byteValue() ).isEqualTo( (byte) 15 );
    }
}
