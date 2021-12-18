package com.adventofcode2021.dec18;

class CharacterStream {

    private final byte[] characters;
    private int currentIndex;

    CharacterStream( byte[] characters ) {
        this.characters = characters;
        this.currentIndex = 0;
    }

    char peek() {
        return (char) characters[currentIndex];
    }

    char next() {
        return (char) characters[currentIndex++];
    }
}
