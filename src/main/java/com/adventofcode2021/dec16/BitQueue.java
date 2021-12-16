package com.adventofcode2021.dec16;

import java.util.NoSuchElementException;

public class BitQueue {

    private Node firstNode;
    private Node lastNode;
    private int size;

    boolean isEmpty() {
        return size == 0;
    }

    void add( Nibble nibble ) {
        byte fourBits = nibble.byteValue();
        add( (fourBits & 8) != 0 );
        add( (fourBits & 4) != 0 );
        add( (fourBits & 2) != 0 );
        add( (fourBits & 1) != 0 );
    }

    private void add( boolean bit ) {
        if ( isEmpty() ) {
            firstNode = new Node( bit, null );
            lastNode = firstNode;
        } else {
            lastNode.next = new Node( bit, null );
            lastNode = lastNode.next;
        }
        ++size;
    }

    int nextBits( int numberOfBits ) {
        int nextBits = 0;
        for ( int i = 0; i < numberOfBits; ++i ) {
            ensureNextBitExists();
            nextBits *= 2;
            nextBits += firstNode.value ? 1 : 0;
            firstNode = firstNode.next;
        }
        size -= numberOfBits;
        return nextBits;
    }

    private void ensureNextBitExists() {
        if ( firstNode == null ) {
            throw new NoSuchElementException();
        }
    }

    int size() {
        return size;
    }

    void clear() {
        firstNode = lastNode = null;
        size = 0;
    }

    private static class Node {

        private final boolean value;
        private Node next;

        Node( boolean value, Node next ) {
            this.value = value;
            this.next = next;
        }
    }
}
