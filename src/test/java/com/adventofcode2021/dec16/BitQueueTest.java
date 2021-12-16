package com.adventofcode2021.dec16;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

class BitQueueTest {

    @Test
    public void newBitQueueIsEmpty() {
        BitQueue queue = new BitQueue();

        assertThat( queue.isEmpty() ).isTrue();
    }

    @Test
    public void bitQueueWithSingleNibbleIsNotEmpty() {
        BitQueue queue = new BitQueue();
        queue.add( Nibble.of( '2' ) );

        assertThat( queue.isEmpty() ).isFalse();
    }

    @Test
    public void newBitQueueHasSize0() {
        BitQueue queue = new BitQueue();

        assertThat( queue.size() ).isZero();
    }

    @Test
    public void bitQueueWithSingleNibbleHasSize4() {
        BitQueue queue = new BitQueue();
        queue.add( Nibble.of( '7' ) );

        assertThat( queue.size() ).isEqualTo( 4 );
    }

    @Test
    public void bitQueueWith2NibblesHasSize5AfterRemove3Bits() {
        BitQueue queue = new BitQueue();
        queue.add( Nibble.of( 'C' ) );
        queue.add( Nibble.of( '3' ) );
        queue.nextBits( 3 );

        assertThat( queue.size() ).isEqualTo( 4 + 4 - 3 );
    }

    @Test
    public void cannotRetrieveMoreBitsThanAreInTheQueue() {
        BitQueue queue = new BitQueue();
        queue.add( Nibble.of( 'A' ) ); // adds four bits

        assertThatThrownBy( () -> queue.nextBits( 5 ) ).isInstanceOf( NoSuchElementException.class );
    }

    @Test
    public void canRetrieveFewerBitsThanAreInTheQueue() {
        BitQueue queue = new BitQueue();
        queue.add( Nibble.of( '7' ) ); // 0111

        assertThat( queue.nextBits( 3 ) ).isEqualTo( 3 ); // 011
    }

    @Test
    public void canRetrieveAllBitsThatAreInTheQueue() {
        BitQueue queue = new BitQueue();
        queue.add( Nibble.of( 'A' ) ); // 1010

        assertThat( queue.nextBits( 4 ) ).isEqualTo( 10 );
    }

    @Test
    public void nibblesCanBeAddedInBetweenBitRetrievals() {
        BitQueue queue = new BitQueue();
        queue.add( Nibble.of( 'D' ) ); // left in queue: 1101

        assertThat( queue.nextBits( 2 ) ).isEqualTo( 3 ); // left in queue: 01

        queue.add( Nibble.of( '9' ) ); // left in queue: 011001
        assertThat( queue.nextBits( 3 ) ).isEqualTo( 3 ); // left in queue: 001
        assertThat( queue.nextBits( 2 ) ).isEqualTo( 0 ); // left in queue: 1
        assertThat( queue.nextBits( 1 ) ).isEqualTo( 1 ); // left in queue:
        assertThat( queue.isEmpty() ).isTrue();
    }

    @Test
    public void clearingBitQueueRemovesAllBits() {
        BitQueue queue = new BitQueue();
        queue.add( Nibble.of( '8' ) );
        queue.add( Nibble.of( 'E' ) );

        queue.clear();

        assertThat( queue.isEmpty() ).isTrue();
    }

    @Test
    public void canAddMoreBitsAfterClearingQueue() {
        BitQueue queue = new BitQueue();
        queue.add( Nibble.of( 'B' ) );
        queue.clear();

        queue.add( Nibble.of( '5' ) );

        assertThat( queue.size() ).isEqualTo( 4 );
        assertThat( queue.nextBits( 4 ) ).isEqualTo( 5 );
        assertThat( queue.isEmpty() ).isTrue();
    }
}
