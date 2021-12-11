package com.adventofcode2021.dec08;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

class Signal {

    private final Set<SignalCharacter> signalCharacters;

    Signal( Set<SignalCharacter> signalCharacters ) {
        this.signalCharacters = new HashSet<>( signalCharacters );
    }

    Signal( Signal signal ) {
        this.signalCharacters = new HashSet<>( signal.signalCharacters );
    }

    int length() {
        return signalCharacters.size();
    }

    Stream<SignalCharacter> characters() {
        return signalCharacters.stream();
    }

    boolean contains( Signal signal ) {
        return signalCharacters.containsAll( signal.signalCharacters );
    }

    void remove( Signal signal ) {
        signalCharacters.removeAll( signal.signalCharacters );
    }

    public SignalCharacter first() {
        return signalCharacters.stream().findFirst().orElseThrow();
    }

    boolean isEmpty() {
        return signalCharacters.isEmpty();
    }
}
