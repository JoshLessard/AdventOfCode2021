package com.adventofcode2021.dec18;

class ExplodedMessage {

    private final SnailfishNumber source;
    private final long value;

    ExplodedMessage( SnailfishNumber source, long value ) {
        this.source = source;
        this.value = value;
    }

    SnailfishNumber source() {
        return source;
    }

    long value() {
        return value;
    }

    ExplodedMessage withSource( SnailfishNumber snailfishNumber ) {
        return new ExplodedMessage( snailfishNumber, value );
    }
}
